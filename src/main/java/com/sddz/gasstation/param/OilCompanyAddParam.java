package com.sddz.gasstation.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <Description> 新增油企 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class OilCompanyAddParam {

    @NotBlank(message = "油企名称不得为空")
    public String name;

    private Long mpId;
}
