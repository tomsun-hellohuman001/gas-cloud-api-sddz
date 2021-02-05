package com.sddz.gasstation.entity.PC;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <Description> 油企 <br>
 *
 * @author zhaopengwei<br>
 */
@EntityListeners(AuditingEntityListener.class)
@Table(name = "oil_company")
@Entity
@Getter
@Setter
public class OilCompany extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     **/
    private String name;
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "modify_time")
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

    @OneToMany
    @JoinColumn(name = "oil_company_id")
    private List<GasStation> stations;

//    @OneToMany
//    @JoinTable(
//            name = "gas_company_recharge_partner",
//            joinColumns = @JoinColumn(name = "company_id"),
//            inverseJoinColumns = @JoinColumn(name = "partner_id"))
//    private List<ThirdMerchant> rechargePartners;

    @Column(name = "mp_id")
    private Long mpId;

    //临时屏蔽掉。sunyt
//    @OneToOne
//    @JoinColumn(name = "mp_id", insertable = false, updatable = false)
//    private GasWxMp gasWxMp;

    @Column(name = "has_ele_card")
    private Boolean hasEleCard;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "app_secret")
    private String appSecret;

    public OilCompany(Long id, String name, LocalDateTime createTime, LocalDateTime modifyTime, Long creator, Long modifier, Long mpId, Boolean hasEleCard, String appId, String appSecret) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.creator = creator;
        this.modifier = modifier;
        this.mpId = mpId;
        this.hasEleCard = hasEleCard;
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public OilCompany() {
        super();
    }
}
