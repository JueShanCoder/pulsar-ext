import com.boxuegu.basis.pulsar.qimoor.entity.remote.RemoteArticle;
import com.boxuegu.basis.pulsar.qimoor.entity.remote.RemoteCourse;
import com.boxuegu.basis.pulsar.qimoor.entity.remote.RemoteUrlSubjectMapping;
import com.boxuegu.basis.pulsar.qimoor.service.GetObjectService;
import com.boxuegu.basis.pulsar.qimoor.service.JdbcService;
import com.boxuegu.basis.pulsar.qimoor.service.impl.GetArticleServiceImpl;
import com.boxuegu.basis.pulsar.qimoor.service.impl.GetCourseServiceImpl;
import com.boxuegu.basis.pulsar.qimoor.service.impl.GetUrlSubjectMapping;
import com.boxuegu.basis.pulsar.qimoor.service.impl.JdbcServiceImpl;
import org.testng.annotations.Test;

import java.sql.Connection;

import static com.boxuegu.basis.pulsar.qimoor.service.impl.GetArticleServiceImpl.getArticleSQL;
import static com.boxuegu.basis.pulsar.qimoor.service.impl.GetCourseServiceImpl.getRemoteCourseSQL;
import static com.boxuegu.basis.pulsar.qimoor.service.impl.GetUrlSubjectMapping.getUrlSubjectMapper;


@Test
public class TestJdbc {

    public void test() throws Exception {

        JdbcService jdbcService = new JdbcServiceImpl();
        Connection connection = jdbcService.getConnection("jdbc:mysql://am-bp1kl330q98skjot8131910o.ads.aliyuncs.com/d_bxg_dvb", "bxg_test", "82#eyB4!JGKP(*&V5UD");
        GetObjectService getObjectService = new GetCourseServiceImpl();
        RemoteCourse remoteCourse = (RemoteCourse) getObjectService.getObject(connection, getRemoteCourseSQL(2731,"d_bxg"));
        System.out.println(remoteCourse.getMenuId());


//        GetObjectService getObjectService = new GetUrlSubjectMapping();
//        RemoteUrlSubjectMapping remoteUrlSubjectMapping = (RemoteUrlSubjectMapping) getObjectService.getObject(connection, getUrlSubjectMapper("https://zt.boxuegu.com/data/","d_bxg_crm"));
//        System.out.println(remoteUrlSubjectMapping.getId());
    }
}