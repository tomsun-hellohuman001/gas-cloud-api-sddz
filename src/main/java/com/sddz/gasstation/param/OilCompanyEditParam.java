package com.sddz.gasstation.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <Description> 编辑油企 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class OilCompanyEditParam {

    @NotNull(message = "数据不存在")
    private Long id;

    @NotBlank(message = "油企名称不得为空")
    public String name;

    private Long mpId;
}
