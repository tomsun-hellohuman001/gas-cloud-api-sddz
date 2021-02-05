package com.sddz.gasstation.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 油品销售统计Dto
 */
@Getter
@Setter
public class OilSaleCountDto {
    private Long stationId;
    private String oilName;
    private BigDecimal quantity;
    private BigDecimal totalAmount;
    private BigDecimal payAmount;
    private Integer count;
}
