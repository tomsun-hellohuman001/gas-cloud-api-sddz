package com.sddz.gasstation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <Description> 油品上架 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class OilSaleDto {

    private Long id;

    private Long oilCompanyId;

    private String oilCompany;

    private Long oilProductId;

    private String oilProduct;

    private Long oilCode;

    private Long gasStationId;

    private String gasStation;

    private BigDecimal listingPrice;

    private BigDecimal memberPrice;

//    private List<OilSaleParticularPrice> particularPrice;

    private String particularPrices;

    private Integer status;

    private Integer simultaneousUse;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    private LocalDateTime saleTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    private LocalDateTime soldOutTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    private LocalDateTime createTime;

    private Integer confirmStatus;

    /**
     * 上一个价格
     */
    private BigDecimal previousPrice;
}
