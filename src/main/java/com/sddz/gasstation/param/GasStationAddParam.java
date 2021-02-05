package com.sddz.gasstation.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class GasStationAddParam {

    @NotBlank(message = "油站名称为空")
    private String name;

    @NotBlank(message = "油站行政区划为空")
    private List<Integer> region;

    @NotBlank(message = "油站地址为空")
    private String address;

    @NotNull(message = "油企为空")
    private Long oilCompanyId;

    @NotNull(message = "公众号为空")
    private Integer mpId;

    @NotBlank(message = "联系人为空")
    private String contact;

    @NotBlank(message = "联系人电话为空")
    private String contactPhone;

    @NotNull
    private String longitude;

    @NotNull
    private String latitude;

    @NotNull
    private String localIp;

    private String serve;

    private String discount;

    private Long province;

    private Long city;

    private Long area;
}
