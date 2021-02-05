package com.sddz.gasstation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OilSaleStatusEnum {

    WAIT_ON_SALE(0, "待上架"),
    ON_SALE(1, "已上架"),
    OFF_SALE(2, "已下架"),
    ;

    private int value;
    private String desc;
}
