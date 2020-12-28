package com.boxuegu.basis.pulsar.qimoor.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 七陌会话原始消息
 **/
@Data
public class QiMoorWebChat implements Serializable {
    private String _id;

    /**
     * 访客id
     */
    private String sid;
    /**
     * 类型，默认webchat 在线客服
     */
    private String type;
    /**
     * 状态
     * undeal待领取
     * deal 已领取
     * finish 已关闭
     * changePeer 已流转
     */
    private String status;

    /**
     * 账户编号
     */
    private String account;
    /**
     * 会话创建时间
     */
    private String createTime;
    /**
     * 客户植入七陌代码中的accessId的值
     */
    private String accessId;
    /**
     * 平台来源
     * pc 网站咨询
     * wap wap咨询
     * sdk app咨询
     * weixin 微信咨询
     */
    private String platform;
    /**
     * 用户名称
     */
    private String sName;
    /**
     * 客户Id(没有关联客户返回“NA”)
     */
    private String customerId;
    /**
     * 搜索来源
     */
    private String seoSource;
    /**
     * 关键字
     */
    private String seoKeywords;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 推荐人
     */
    private String referrer;
    /**
     * 咨询页面url值
     */
    private String fromUrl;
    /**
     * 登录页面URL
     */
    private String landingPageUrl;
    /**
     * 咨询页面title
     */
    private String urlTitle;
    /**
     * 地域
     */
    private String area;
    /**
     * 所属技能组
     */
    private String toPeer;
    /**
     * 人工开始时间 时间戳
     */
    private Date manualTime;
    /**
     * 所属坐席
     */
    private String user;
    /**
     * 最后领取时间 时间戳
     */
    private Date lastClaimTime;
    /**
     * 坐席领取时间 格式 yyyy-MM-dd hh:mm:ss
     */
    private String beginTime;
    /**
     * 坐席领取时间 时间戳
     */
    private Date agentClaimTime;
    /**
     * 坐席最后一下回复的时间
     */
    private Date lastAgentMsgTimeStamp;
    /**
     * 第一次回复的时间
     */
    private Date firstReplyTime;
    /**
     * 客服回复消息数
     */
    private Integer replyMsgCount;
    /**
     * 消息总数
     */
    private Integer totalMsgCount;
    /**
     * 客户发送消息数
     */
    private Integer msgCount;
    /**
     * 客户最后一条消息的时间
     */
    private Date lastCustomerMsgTimeStamp;
    /**
     * 流转前坐席
     */
    private String fromUser;
    /**
     * 上次转接|抢接|转技能组时间
     */
    private Date lastRedirectTime;
    /**
     * 流转前技能组
     */
    private String previousPeer;
    /**
     * 所属坐席名称
     */
    private String username;
    /**
     * 直接转坐席的时间
     */
    private Date lastRedirectUserTime;
    /**
     * 客户开始等待的时间戳
     */
    private Date startCustomerWaitTimeStamp;
    /**
     * 备注
     */
    private String remark;
    /**
     * 结束类型
     */
    private String finishReason;
    /**
     * 结束坐席
     */
    private String finishUser;
    /**
     * 会话结束时间
     */
    private String endTime;
    /**
     * 获取用户操作系统等信息 可能为空
     */
    private Map<String, String> ubaInfo;

    /**
     * 访客平台信息
     */
    private String platformDescription;
    /**
     * 访客浏览器名称
     */
    private String browserName;
    /**
     * 访客系统信息
     */
    private String osInfo;
    /**
     * 最后一条信息来源
     */
    private String lastMessageFrom;
    /**
     * 消息质量
     */
    private String quality;

    /**
     * 扩展字段中数据
     */
    private JsonObject otherParams;

    /**
     * 拓展字段字符串格式
     */
    private String otherParamsStr;

    /**
     * 历史记录
     */
    private JsonArray history;

    /**
     * 历史记录字符串
     */
    private String historyStr;

    /**
     * 冗余会话id
     */
    private Long id;

}