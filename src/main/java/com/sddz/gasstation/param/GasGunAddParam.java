package com.sddz.gasstation.param;

import lombok.Data;

import java.util.List;

@Data
public class GasGunAddParam {

    private String name;

    private Long oilType;

    private Long machineId;

    private Long stationId;

    private List<Long> screenIds;
}
