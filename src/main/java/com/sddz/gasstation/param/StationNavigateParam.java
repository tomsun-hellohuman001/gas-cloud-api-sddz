package com.sddz.gasstation.param;

import lombok.Data;

@Data
public class StationNavigateParam {
    private Double targetLat;
    private Double targetLng;
    private Integer distance;
    private Integer city;
    private String sort;
    private String name;
    private Long companyId;
}
