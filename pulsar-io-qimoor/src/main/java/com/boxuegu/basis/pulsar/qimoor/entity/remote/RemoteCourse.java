package com.boxuegu.basis.pulsar.qimoor.entity.remote;

import com.google.common.collect.Maps;
import lombok.*;

import java.util.Arrays;
import java.util.Map;

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
     * 学科名字
     */
    private String menuName;

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
    private Integer status;

    @AllArgsConstructor
    public enum Status {

        /**
         * 已下架0、已启用1、未启用2
         */
        DOWN(0, "已下架"),
        ENABLE(1, "已启用"),
        DISABLED(2, "未启用"),
        ;

        @Getter
        private final int status;
        @Getter
        private final String desc;

        private static final Map<Integer, Status> MAP = Maps.uniqueIndex(
                Arrays.asList(Status.values()),
                Status::getStatus
        );

        public static Status get(int type) {
            return MAP.get(type);
        }
    }
}
