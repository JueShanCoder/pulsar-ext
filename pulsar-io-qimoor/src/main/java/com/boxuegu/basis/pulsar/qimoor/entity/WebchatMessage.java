package com.boxuegu.basis.pulsar.qimoor.entity;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WebchatMessage implements Serializable {

    /**
     * id
     */
    private Long id;

    @SerializedName("_id")
    private String _id;
    /**
     * 创建时间 该条记录插入数据库的时间
     */
    private LocalDateTime createDateTime;
    /**
     * sessionId
     */
    private String sessionId;
    /**
     * 访客id
     */
    private String sid;

    /**
     * 会话id
     */
    private Integer webChatId;
    /**
     * 消息类型
     */
    private String contentType;
    /**
     * 消息类型
     * in 客户发送消息
     * out 坐席回复消息
     */
    private String type;
    /**
     * 只有当type为out时才有此字段
     * system 系统发送的消息
     * robot 机器人回复的消息
     * uuid 坐席回复的消息 ，坐席编号uuid，如："a81f78f0-3618-11e8-b149-99f8357b7d75"
     */
    private String user;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 平台来源
     */
    private String platform;
    /**
     * 消息时间
     */
    private String dateTime;
    /**
     * 状态
     */
    private String status;
    /**
     * 客户植入七陌代码中的accessId的值
     */
    private String accessId;
    /**
     * 账户编号
     */
    private String account;

    /**
     * 是否显示为html形式
     */
    private Boolean showHtml;
    /**
     * 是否显示给客户看，wap接入才有
     */
    private Boolean notShow;
    /**
     * 坐席工号
     */
    private String exten;
    /**
     * 坐席名称
     */
    private String displayName;
    /**
     * 坐席头像
     */
    @SerializedName("im_icon")
    private String imIcon;
    /**
     * 是否api接口发送
     */
    private Boolean docking;
    /**
     * 是否api接口contentList发送
     */
    private Boolean dockingMsgList;
    //唯一标识
    private Integer hashCode;

}