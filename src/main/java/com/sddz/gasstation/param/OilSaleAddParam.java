package com.sddz.gasstation.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

//import com.shufei.gasstation.entity.converter.OilSaleParticularPrice;

/**
 * <Description> 新增油品上架 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class OilSaleAddParam {

    @NotNull(message = "油企为空")
    private Long oilCompanyId;

    @NotNull(message = "油站为空")
    private Long gasStationId;

    @NotNull(message = "油品为空")
    private Long oilProductId;

    @NotNull(message = "挂牌价为空")
    private BigDecimal listingPrice;

    private BigDecimal memberPrice;

//    private List<OilSaleParticularPrice> particularPrice;

    private Integer simultaneousUse;

    @NotNull(message = "上架时间为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime soldOutTime;

    public LocalDateTime getSoldOutTime(){
        if(soldOutTime != null){
            return soldOutTime;
        }
        return LocalDateTime.of(2099, 12, 31, 23, 59, 59);
    }

}
