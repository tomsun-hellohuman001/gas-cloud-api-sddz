package com.sddz.gasstation.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PayTypeSumDto {
    private Integer payType;
    private BigDecimal payAmount;
    private BigDecimal fee;  //手续费
    private BigDecimal entryAmount;
}
