package com.sddz.gasstation.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author sdd
 * @description
 * @creat 2020/8/24 15:07
 */
@Data
public class OilSaleConfirmParam {
    @NotNull(message = "加油站id不能为空")
    private Long gasStationId;
    @NotNull(message = "油品方案id不能为空")
    private Long oilSaleId;
}
