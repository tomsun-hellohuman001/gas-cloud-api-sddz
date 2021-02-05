package com.sddz.gasstation.entity.converter;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <Description> 用户组金额 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class OilSaleParticularPrice {

    private Long userGroupId;

    private BigDecimal price;
}
