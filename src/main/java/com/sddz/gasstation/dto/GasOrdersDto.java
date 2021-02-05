package com.sddz.gasstation.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class GasOrdersDto {

        private Long id;

        private String orderNo;

        private Long userId;

        private Long gasGoodsId;

        private String plateNumber;

        private Long stationId;

        private Long gunId;

        private BigDecimal quantity;

        private BigDecimal price;

        private BigDecimal totalAmount;

        private BigDecimal payAmount;

        private Byte payType;

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;

        private String cardNo;

        private String orderDay;

        private String orderCount;

        private String companyName;

        private String stationName;

}
