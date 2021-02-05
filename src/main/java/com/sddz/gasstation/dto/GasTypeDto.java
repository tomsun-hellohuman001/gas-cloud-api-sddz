package com.sddz.gasstation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <Description> 油品 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class GasTypeDto {

    private Long id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    private LocalDateTime modifyTime;

    private String number;

    private BigDecimal marketPrice;

    private String type;

    private String standard;
}
