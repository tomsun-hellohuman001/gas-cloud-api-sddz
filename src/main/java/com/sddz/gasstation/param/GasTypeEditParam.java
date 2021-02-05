package com.sddz.gasstation.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * <Description> 编辑油品 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class GasTypeEditParam {

    @NotNull(message = "数据不存在")
    private Long id;

    @NotBlank(message = "油品名称不得为空")
    public String name;

    private String number;

    private String type;

    private String standard;

    private BigDecimal marketPrice;
}
