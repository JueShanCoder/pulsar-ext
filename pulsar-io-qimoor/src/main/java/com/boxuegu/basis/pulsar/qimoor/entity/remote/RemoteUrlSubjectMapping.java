package com.boxuegu.basis.pulsar.qimoor.entity.remote;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@ToString
public class RemoteUrlSubjectMapping {

    private Long id;

    /**
     * url
     */
    private String url;

    /**
     * 学科id
     */
    private Integer subjectId;

    /**
     * 学科名字
     */
    private String subjectName;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 更新时间
     */
    private Long updated;
}