package com.sddz.gasstation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sdd
 * @description
 * @creat 2020/8/24 14:59
 */
@AllArgsConstructor
@Getter
public enum  OilSaleConfirmStatusEnum {
    NOT_CONFIRM(0,"未确认"),
    CONFIRMED(1,"已确认"),
    SENT(2,"已下发")
    ;
    private Integer value;
    private String desc;
}
