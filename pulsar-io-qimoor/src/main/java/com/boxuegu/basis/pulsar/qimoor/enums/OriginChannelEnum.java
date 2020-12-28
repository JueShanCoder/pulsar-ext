package com.boxuegu.basis.pulsar.qimoor.enums;

/**
 * 投放渠道
 */
public enum OriginChannelEnum {
    sogou("搜狗推广", "?sogou-"),
    toutiao("今日头条", "?toutiao"),
    wangyi("网易渠道", "?wangyi"),
    tg360("360推广", "?360-"),
    wangmeng("百度网盟", "?wangmeng"),
    jingjia("百度竞价", "?jingjia-"),
    shoubai("百度信息流", "?shoubai"),
    uc("神马", "?uc-"),
    bdtbttcz("传智贴吧头图", "?bdtbttcz"),
    bdtbtthm("黑马贴吧头图", "?bdtbtthm"),
    shoubai_cz("传智百度信息流", "?shoubai_cz"),
    jingjiapp("百度品牌词", "?jingjiapp"),
    jingjiaqt("百度其他词", "?jingjiaqt"),
    jingjiaczpz("百度传智品专", "?jingjiaczpz"),
    jingjiahmpz("百度黑马品专", "?jingjiahmpz"),
    pp360("360品牌词", "?360pp"),
    qt360("360其他词", "?360qt"),
    sogoupp("搜狗品牌词", "?sogoupp"),
    sogouqt("搜狗其他词", "?sogouqt"),
    ucpp("神马品牌词", "?ucpp"),
    ucqt("神马其他词", "?ucqt"),
    unknown("未知", "未知"),
    ;
    private String name;
    private String key;


    OriginChannelEnum(String name, String key) {
        this.key = key;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
