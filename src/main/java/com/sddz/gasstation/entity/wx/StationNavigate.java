package com.sddz.gasstation.entity.wx;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class StationNavigate {
    private Long id;
    private String name;
    private String address;
    private String creator;
    private LocalDateTime createTime;
    private String modifier;
    private LocalDateTime modifyTime;
    private String deleted;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String citycode;
    private String contact;
    private String contactPhone;
    private String oilCompanyId;
    private String localIp;
    private String mpId;
    private String province;
    private String city;
    private String cityName;
    private String area;
    private String areaName;
    private String serve;
    private String discount;
    private List<StationProduct> stationProductList;
}
