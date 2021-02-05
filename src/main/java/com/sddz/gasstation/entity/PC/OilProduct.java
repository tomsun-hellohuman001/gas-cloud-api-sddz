package com.sddz.gasstation.entity.PC;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * <Description> 油品 <br>
 *
 * @author zhaopengwei<br>
 */
@EntityListeners(AuditingEntityListener.class)
@Table(name = "oil_product")
@Entity
@Getter
@Setter
public class OilProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gas_type_id")
    private Long gasTypeId;

    @Column(name = "oil_company_id")
    private Long oilCompanyId;

    @Column(name = "station_id")
    private Long stationId;

    private Integer unit;

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime modifyTime;

    @CreatedBy
    private Long creator;

    @LastModifiedBy
    private Long modifier;

    @ManyToOne
    @JoinColumn(name = "gas_type_id", insertable = false, updatable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private GasType gasType;

    private Long oilCode;
}
