package com.sddz.gasstation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GasStationDto {

    private Long id;

    private String name;

    private String address;

    private Long creator;

    private Date createTime;

    private Long modifier;

    private Date modifyTime;

    private Boolean deleted;

    private String longitude;

    private String latitude;

    private String citycode;

    private String contact;

    private String contactPhone;

    private Long oilCompanyId;

    private String oilCompany;

    private String localIp;

    private Integer mpId;

    private String mpName;

    private Long province;

    private Long city;

    private Long area;

    private String serve;

    private String discount;

}
