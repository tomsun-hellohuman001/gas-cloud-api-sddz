package com.sddz.gasstation.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GasOrderAddParam {

    private String orderNo;

    @NotBlank(message = "用户ID或手机号不可为空")
    private Long userId;

    private Long gasGoodsId;

    private String plateNumber;

    @NotBlank(message = "加油站ID不可为空")
    private Long stationId;

    @NotBlank(message = "枪号不可为空")
    private Long gunId;

    private BigDecimal quantity;

    private BigDecimal price;

    private BigDecimal totalAmount;

    @NotBlank(message = "支付金额不可为空")
    private BigDecimal payAmount;

    private Byte payType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    private String cardNo;
}
