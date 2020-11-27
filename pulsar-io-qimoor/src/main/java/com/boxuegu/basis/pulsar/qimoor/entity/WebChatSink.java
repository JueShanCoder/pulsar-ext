package com.boxuegu.basis.pulsar.qimoor.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : Liuzl
 * @description: sink - 7moor session info
 */
@Getter
@Setter
public class WebChatSink {

    private Long id;
    /**
     * 会话id
     */
    private String sessionId;
    /**
     * 访客id
     */
    private String sid;
    /**
     * 数据记录时间
     */
    private String recordTime;
    /**
     * 会话创建时间
     */
    private String createTime;
    /**
     * 学科ID
     */
    private Long menuId;
    /**
     * 广告系列名称
     */
    private String utmCampaign;
    /**
     * 广告系列来源
     */
    private String utmSource;
    /**
     * 广告系列字词
     */
    private String utmTerm;
    /**
     * 广告系列媒介
     */
    private String utmMedium;
    /**
     * 广告系列内容
     */
    private String utmContent;
    /**
     * 咨询平台
     */
    private String platform;
    /**
     * 接待网咨
     */
    private String user;
    /**
     * 所在国家
     */
    private String country;
    /**
     * 所在省份
     */
    private String province;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 状态（undeal待领取 deal 已领取 finish 已关闭 changePeer 已流转）
     */
    private String status;
    /**
     * 客服回复消息数
     */
    private Integer replyMsgCount;
    /**
     * 客户发送消息数
     */
    private Integer msgCount;
    /**
     * 所有字段集合
     */
    private String session;

}