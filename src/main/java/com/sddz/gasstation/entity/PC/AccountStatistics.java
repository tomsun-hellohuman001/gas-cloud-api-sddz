package com.sddz.gasstation.entity.PC;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountStatistics {
    private String type;
    private BigDecimal payAmount;
    private BigDecimal payQuantity;
    private Integer payCount;
}
