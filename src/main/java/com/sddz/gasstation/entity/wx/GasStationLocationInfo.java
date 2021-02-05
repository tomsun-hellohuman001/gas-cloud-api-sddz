package com.sddz.gasstation.entity.wx;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sdd
 * @description
 * @creat 2020/7/2 19:30
 */
@Getter
@Setter
public class GasStationLocationInfo {
    private Long id;
    private String name;
    private String latitude;
    private String longitude;
    private Long distance;
}
