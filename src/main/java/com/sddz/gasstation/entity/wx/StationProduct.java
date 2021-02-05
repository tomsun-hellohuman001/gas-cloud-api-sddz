package com.sddz.gasstation.entity.wx;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StationProduct {
    private Long gasStationId;
    private BigDecimal listingPrice;
    private BigDecimal memberPrice;
    private String name;
    private String type;
    private String standard;
}
