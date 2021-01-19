package com.boxuegu.basis.pulsar.qimoor.entity;

import lombok.Data;

/**
 * @author : Liuzl
 * @Date: 2020/8/14  2:30 下午
 * @description:
 */
@Data
public class WebChatMsgSink {

    /**
     * id
     */
    private Long id;

    /**
     * sessionId
     */
    private String sessionId;
    /**
     * 访客id
     */
    private String sid;

    /**
     * 消息时间
     */
    private String dateTime;

    /**
     * 所有字段集合
     */
    private String webChat;

}