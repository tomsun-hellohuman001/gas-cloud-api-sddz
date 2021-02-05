package com.sddz.gasstation.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <Description> 油号 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class OilProductAddParam {

    @NotNull(message = "油企为空")
    private Long oilCompanyId;

    @NotNull(message = "油站为空")
    private Long stationId;

    @NotNull(message = "油号为空")
    private Long gasTypeId;

    @NotNull(message = "单位为空")
    private Integer unit;

    @NotNull(message = "油品编号不能为空")
    private Long oilCode;
}
