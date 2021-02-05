package com.sddz.gasstation.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("单id post请求参数")
public class IdPostParam {

    @ApiModelProperty(value = "id",required = true)
    private Long Id;
}
