package com.sddz.gasstation.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <Description> 新增油品 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class GasTypeAddParam {

    @NotBlank(message = "油品名称不得为空")
    private String name;

    private String number;

    private String type;

    private String standard;
}
