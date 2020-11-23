package com.boxuegu.basis.pulsar.qimoor.entity.remote;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RemoteArticle {

    private Long id;

    /**
     * 学科
     */
    private Integer menuId;

    /**
     * 学科名字
     */
    private String menuName;
}
