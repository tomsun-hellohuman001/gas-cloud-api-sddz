package com.sddz.gasstation.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <Description> 编辑油品上架 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class OilSaleEditParam extends OilSaleAddParam {

    @NotNull(message = "数据为空")
    private Long id;

    private Integer status;
}
