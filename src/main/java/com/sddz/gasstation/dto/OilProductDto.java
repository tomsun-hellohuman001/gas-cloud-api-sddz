package com.sddz.gasstation.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <Description> 油品 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class OilProductDto {

    private Long id;

    private Long gasTypeId;

    private String gasType;

    private Long oilCompanyId;

    private String oilCompany;

    private Long stationId;

    private String stationName;

    private Integer unit;

    private LocalDateTime createTime;

    private Long oilCode;
}
