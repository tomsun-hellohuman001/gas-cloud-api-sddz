package com.sddz.gasstation.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <Description> 油品 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class OilProductEditParam extends OilProductAddParam {

    @NotNull(message = "数据为空")
    private Long id;
}
