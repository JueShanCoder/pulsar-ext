package com.boxuegu.basis.pulsar.qimoor.function;

import com.boxuegu.basis.pulsar.qimoor.entity.QiMoorWebChat;
import com.boxuegu.basis.pulsar.qimoor.entity.WebChatSink;
import com.boxuegu.basis.pulsar.qimoor.entity.remote.RemoteArticle;
import com.boxuegu.basis.pulsar.qimoor.entity.remote.RemoteCourse;
import com.boxuegu.basis.pulsar.qimoor.entity.remote.RemoteUrlSubjectMapping;
import com.boxuegu.basis.pulsar.qimoor.function.config.QiMoorSourceFunctionConfig;
import com.boxuegu.basis.pulsar.qimoor.service.GetObjectService;
import com.boxuegu.basis.pulsar.qimoor.service.JdbcService;
import com.boxuegu.basis.pulsar.qimoor.service.impl.GetArticleServiceImpl;
import com.boxuegu.basis.pulsar.qimoor.service.impl.GetCourseServiceImpl;
import com.boxuegu.basis.pulsar.qimoor.service.impl.GetUrlSubjectMapping;
import com.boxuegu.basis.pulsar.qimoor.service.impl.JdbcServiceImpl;
import com.boxuegu.basis.pulsar.qimoor.utils.StringUtils;
import com.boxuegu.basis.pulsar.qimoor.utils.gson.GsonBuilderUtil;
import com.google.gson.Gson;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.functions.api.Context;
import org.apache.pulsar.functions.api.Function;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static com.boxuegu.basis.pulsar.qimoor.service.impl.GetArticleServiceImpl.getArticleSQL;
import static com.boxuegu.basis.pulsar.qimoor.service.impl.GetCourseServiceImpl.getRemoteCourseSQL;
import static com.boxuegu.basis.pulsar.qimoor.service.impl.GetUrlSubjectMapping.getUrlSubjectMapper;
import static com.boxuegu.basis.pulsar.qimoor.utils.StringUtils.isBlank;
import static com.boxuegu.basis.pulsar.qimoor.utils.TimeUtil.getISO8601TimeByStr;
import static com.boxuegu.basis.pulsar.qimoor.utils.UrlParsingUtils.*;

/**
 * @author : Liuzl
 * @description: 7moor source function
 */
@Slf4j
public class QiMoorSourceFunction implements Function<byte[], Void> {

    final Gson gson = GsonBuilderUtil.create(false);
    HikariDataSource dataSource = null;
    @Override
    public Void process(byte[] input, Context context) {
        QiMoorSourceFunctionConfig qiMoorSourceFunctionConfig = QiMoorSourceFunctionConfig.load(context.getUserConfigMap());
        if (qiMoorSourceFunctionConfig.getCloseSessionTopicName() == null || qiMoorSourceFunctionConfig.getCourseTypes() == null ||
                qiMoorSourceFunctionConfig.getJdbcUrl() == null || qiMoorSourceFunctionConfig.getPassword() == null ||
                qiMoorSourceFunctionConfig.getTableName() == null || qiMoorSourceFunctionConfig.getUnCloseSessionTopicName() == null ||
                qiMoorSourceFunctionConfig.getUserName() == null || qiMoorSourceFunctionConfig.getCrmDatabaseName() == null ||
                qiMoorSourceFunctionConfig.getBxgDatabaseName() == null) {
            throw new IllegalArgumentException(" Required parameters are not set... Please check the startup script !!! ");
        }
        QiMoorWebChat qiMoorWebChat = gson.fromJson(new String(input), QiMoorWebChat.class);
//        log.info("[QimoorSource ] sessionId is {}, status is {}",qiMoorWebChat.get_id(),qiMoorWebChat.getStatus());
        Map<String, String> properties = new HashMap<>();
        properties.put("ACTION", "INSERT");
        properties.put("TARGET", qiMoorSourceFunctionConfig.getTableName());
        properties.put("SQLMODE", "INSERT_IGNORE_INVALID");
        if (dataSource == null) {
            log.info("[QimoorSourceFunction ] ????????? DataSource ... ");
            dataSource = new HikariDataSource();
            dataSource.setJdbcUrl(qiMoorSourceFunctionConfig.getJdbcUrl());
            dataSource.setUsername(qiMoorSourceFunctionConfig.getUserName());
            dataSource.setPassword(qiMoorSourceFunctionConfig.getPassword());
        }

        if (!qiMoorWebChat.getStatus().equalsIgnoreCase("finish") && !qiMoorWebChat.getStatus().equalsIgnoreCase("invalid")) {
            // unclose session delayed 3Min send
            try {
                context.newOutputMessage(qiMoorSourceFunctionConfig.getUnCloseSessionTopicName(), Schema.BYTES).value(gson.toJson(qiMoorWebChat).getBytes(StandardCharsets.UTF_8)).deliverAfter(3L, TimeUnit.MINUTES).send();
                context.getCurrentRecord().ack();
                log.info("[QiMoorSourceFunction] ??????????????????????????? {} ?????? ... [7moor ??????] SessionId {} Id {}] ", qiMoorSourceFunctionConfig.getUnCloseSessionTopicName(), qiMoorWebChat.get_id(), qiMoorWebChat.getId());
            } catch (PulsarClientException e) {
                log.error("[QiMoorSourceFunction] Got PulsarClientException, fail??????????????????????????????????????? ...]", e);
                context.getCurrentRecord().fail();
            }
        } else if (qiMoorWebChat.getStatus().equalsIgnoreCase("finish") || qiMoorWebChat.getStatus().equalsIgnoreCase("invalid")) {
            // close session
            try {
                Gson webGson = GsonBuilderUtil.create(true);
                WebChatSink webChatSink = parseSession(qiMoorWebChat, gson, qiMoorSourceFunctionConfig.getCourseTypes(), dataSource,qiMoorSourceFunctionConfig.getCrmDatabaseName(),qiMoorSourceFunctionConfig.getBxgDatabaseName());
                context.newOutputMessage(qiMoorSourceFunctionConfig.getCloseSessionTopicName(), Schema.BYTES).value(webGson.toJson(webChatSink).getBytes(StandardCharsets.UTF_8)).properties(properties).send();
                context.getCurrentRecord().ack();
                log.info("[QiMoorSourceFunction] ????????????????????? {} ?????? [7moor ??????] SessionId {} Id {}", qiMoorSourceFunctionConfig.getCloseSessionTopicName(), qiMoorWebChat.get_id(), qiMoorWebChat.getId());
            } catch (Exception e) {
                log.error("[QiMoorSourceFunction] Got exception, fail ??????????????????????????????????????? ...]", e);
                context.getCurrentRecord().fail();
            }
        }
        return null;
    }

    /**
     * 7moor source data cleaning
     *
     * @param qiMoorWebChat qimoor source data
     */
    public static WebChatSink parseSession(QiMoorWebChat qiMoorWebChat, Gson gson, String courseTypes,
                                           HikariDataSource dataSource, String crmDatabaseName, String bxgDatabaseName) throws Exception {
        WebChatSink webChat = new WebChatSink();
        BeanCopier beanCopier = BeanCopier.create(QiMoorWebChat.class, WebChatSink.class, false);
        beanCopier.copy(qiMoorWebChat, webChat, null);
        webChat.setSessionId(qiMoorWebChat.get_id());
        webChat.setId(qiMoorWebChat.getId());
        if (!(qiMoorWebChat.getArea() == null || "".equals(qiMoorWebChat.getArea()))) {
            String area = qiMoorWebChat.getArea();// ?????? ?????? ??????
            if (StringUtils.hasText(area)) {
                String[] as = area.split(" ");
                if (as.length >= 1) {
                    webChat.setCountry(as[0]);
                }
                if (as.length > 1) {
                    webChat.setProvince(as[1]);
                }
                if (as.length > 2) {
                    webChat.setCity(as[2]);
                }
            }
        }
        if (qiMoorWebChat.getCreateTime() != null)
            webChat.setCreateTime(getISO8601TimeByStr(qiMoorWebChat.getCreateTime()));
        webChat.setSession(gson.toJson(qiMoorWebChat));
        // Parsing url -> UTM??? url -> subjectId
        conversion(webChat, qiMoorWebChat.getFromUrl(), courseTypes,crmDatabaseName,bxgDatabaseName,dataSource);
        return webChat;
    }

    public static void conversion(WebChatSink webChat, String fromUrl, String courseTypes, String crmDatabaseName,String bxgDatabaseName,HikariDataSource dataSource) throws Exception {
        if (isBlank(fromUrl)) {
            webChat.setMenuId(0L);
            return;
        }
        GetObjectService getObjectService;
        // utm ??????
        Utm utm = parseUrl(fromUrl);
        webChat.setUtmCampaign(utm.getUtmCampaign());
        webChat.setUtmContent(utm.getUtmContent());
        webChat.setUtmMedium(utm.getUtmMedium());
        webChat.setUtmTerm(utm.getUtmTerm());
        webChat.setUtmSource(utm.getUtmSource());

        // ????????????
        String url = fromUrl.split("\\?")[0];
        String[] params = url.split("/");
        List<String> types = Arrays.asList(courseTypes.split(","));
        String courseType = Stream.of(params)
                .filter(types::contains)
                .findFirst()
                .orElse(null);
        if (courseType != null) {
            int courseId = courseId(url);
            if (courseId == 0) {
                // To be determined
                webChat.setMenuId(0L);
                return;
            }
            getObjectService = new GetCourseServiceImpl();
            RemoteCourse remoteCourse = (RemoteCourse) getObjectService.getObject(dataSource.getConnection(), getRemoteCourseSQL(courseId,bxgDatabaseName));
            if (remoteCourse == null) {
                // To be determined
                webChat.setMenuId(0L);
                return;
            }
            webChat.setMenuId(remoteCourse.getMenuId().longValue());
        }
        String news = Stream.of(params)
                .filter(NEWS::contains)
                .findFirst()
                .orElse(null);
        if (news != null) {
            //??????id
            int articleId = articleId(url);
            if (articleId == 0) {
                // To be determined
                webChat.setMenuId(0L);
                return;
            }
            getObjectService = new GetArticleServiceImpl();
            RemoteArticle remoteArticle = (RemoteArticle) getObjectService.getObject(dataSource.getConnection(), getArticleSQL(articleId,bxgDatabaseName));
            if (remoteArticle == null) {
                // To be determined
                webChat.setMenuId(0L);
                return;
            }
            webChat.setMenuId(remoteArticle.getMenuId().longValue());
        }
        getObjectService = new GetUrlSubjectMapping();
        RemoteUrlSubjectMapping remoteUrlSubjectMapping = (RemoteUrlSubjectMapping) getObjectService.getObject(dataSource.getConnection(), getUrlSubjectMapper(url,crmDatabaseName));
        if (remoteUrlSubjectMapping == null) {
            webChat.setMenuId(0L);
            return;
        }
        webChat.setMenuId(remoteUrlSubjectMapping.getSubjectId().longValue());
    }
}