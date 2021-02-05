package com.sddz.gasstation.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GasGunDto {

    private Long id;

    private String name;

    private Long oilType;

    private String oilTypeName;

    private Long machineId;

    private Long stationId;

    private Long companyId;

    private String stationName;

    private List<Long> screenIds;

    private List<String> screenSerialNos;

    private BigDecimal price;

    private Long oilCode;
}
