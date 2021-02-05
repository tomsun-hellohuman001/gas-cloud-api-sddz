package com.sddz.gasstation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StationNavitionSortEnum {

    HIGHTPRICE("HIGHTPRICE", "最高价格","DESC"),
    LOWPRICE("LOWPRICE", "最低价格","ASC"),
    NEAREST("NEAREST", "最近距离","");
    private String code;
    private String name;
    private String sort;
}
