package com.sddz.gasstation.entity.PC;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <Description> 油品上架 <br>
 *
 * @author zhaopengwei<br>
 */
@EntityListeners(AuditingEntityListener.class)
@Table(name = "oil_sale")
@Entity
@Getter
@Setter
@ToString
public class OilSale extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long oilCompanyId;

    private Long gasStationId;

    private Long oilProductId;

    private BigDecimal listingPrice;

    private BigDecimal memberPrice;

//    @Convert(converter = OilSaleParticularPriceConverter.class)
//    private List<OilSaleParticularPrice> particularPrice;

    private Integer status;

    private Integer simultaneousUse;

    private LocalDateTime saleTime;

    private LocalDateTime soldOutTime;

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime modifyTime;

    @CreatedBy
    private Long creator;

    @LastModifiedBy
    private Long modifier;

    private Integer confirmStatus;

}
