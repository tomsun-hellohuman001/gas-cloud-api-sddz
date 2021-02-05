package com.sddz.gasstation.entity.PC;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <Description> 油号 <br>
 *
 * @author zhaopengwei<br>
 */
@EntityListeners(AuditingEntityListener.class)
@Table(name = "gas_type")
@Entity
@Getter
@Setter
public class GasType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     **/
    private String name;

    private String number;

    private String type;

    private String standard;

    @Column(name="market_price")
    private BigDecimal marketPrice;

    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @LastModifiedDate
    private LocalDateTime modifyTime;
    /**
     * 创建人
     */
    @CreatedBy
    private Long creator;
    /**
     * 更新人
     */
    @LastModifiedBy
    private Long modifier;
}
