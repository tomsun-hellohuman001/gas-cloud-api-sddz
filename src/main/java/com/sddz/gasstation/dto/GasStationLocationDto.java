package com.sddz.gasstation.dto;

import com.sddz.gasstation.entity.PC.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author sdd
 * @description
 * @creat 2020/7/2 20:12
 */
@Getter
@Setter
@ToString
public class GasStationLocationDto extends BaseEntity {
    private Long id;
    private String name;
    private String latitude;
    private String longitude;
    private Long distance;
}
