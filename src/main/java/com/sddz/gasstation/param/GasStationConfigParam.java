package com.sddz.gasstation.param;

import lombok.Data;

import java.util.Map;

@Data
public class GasStationConfigParam {

    private Long stationId;

    private String group;

    private Map<String, String> configMap;
}
