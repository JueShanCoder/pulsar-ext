package com.boxuegu.basis.pulsar.qimoor.entity.remote;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RemoteCourse {

    /**
     * 课程ID
     */
    private Long id;

    /**
     * 课程名称
     */
    private String gradeName;

    /**
     * 学科
     */
    private Integer menuId;

    /**
     * 现价
     */
    private Double currentPrice;

    /**
     * 课程类型：0职业课;1微课;2直播课
     */
    private Integer courseType;

    /**
     * 状态：已下架0、已启用1、未启用2
     */
    private String status;

}
