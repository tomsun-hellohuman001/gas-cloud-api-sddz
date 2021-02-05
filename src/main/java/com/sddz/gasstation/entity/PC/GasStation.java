package com.sddz.gasstation.entity.PC;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "gas_station")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update gas_station set deleted=1 where id=?")
@Where(clause = "deleted=0")
public class GasStation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mp_id")
    private Integer mpId;

    private String name;

    private String address;

    @CreatedBy
    private Long creator;

    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @LastModifiedBy
    private Long modifier;

    @LastModifiedDate
    @Column(name = "modify_time")
    private LocalDateTime modifyTime;

    private Boolean deleted;

    @OneToMany(mappedBy = "station")
    private List<GasGun> guns;

//    @ManyToMany
//    @JoinTable(
//            name = "gas_station_wx_partner",
//            joinColumns = @JoinColumn(name = "station_id"),
//            inverseJoinColumns = @JoinColumn(name = "partner_id"))
//    @WhereJoinTable(clause = "deleted=0")
//    private List<WxPayPartner> wxPayPartners;

//    @ManyToMany
//    @JoinTable(
//            name = "gas_station_alipay_partner",
//            joinColumns = @JoinColumn(name = "station_id"),
//            inverseJoinColumns = @JoinColumn(name = "partner_id"))
//    @WhereJoinTable(clause = "deleted=0")
//    private List<AlipayPayPartner> alipayPayPartners;

//    @ManyToMany
//    @JoinTable(
//            name = "gas_station_third_partner",
//            joinColumns = @JoinColumn(name = "station_id"),
//            inverseJoinColumns = @JoinColumn(name = "partner_id"))
//    @WhereJoinTable(clause = "deleted=0")
//    private List<ThirdMerchant> thirdPayPartners;

    @Column(name = "oil_company_id")
    private Long oilCompanyId;

    private String contact;

    @Column(name = "local_ip")
    private String localIp;

    @Column(name = "contact_phone")
    private String contactPhone;

    private String longitude;

    private String latitude;

    private String citycode;

//    @OneToMany
//    @JoinColumn(name = "station_id")
//    private List<GasCamera> cameras;

//    @OneToMany
//    @JoinColumn(name = "station_id")
//    private List<GasScreen> screens;

    @OneToMany
    @JoinColumn(name = "station_id")
    private List<OilProduct> gasGoods;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "mp_id", insertable = false, updatable = false)
//    private GasWxMp wxMp;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "oil_company_id", insertable = false, updatable = false)
//    private OilCompany company;

    private Long province;

    private Long city;

    private Long area;

    private String serve;

    private String discount;

    public GasStation(Long id, String name, String address, Long creator, LocalDateTime createTime, Long modifier, LocalDateTime modifyTime, Boolean deleted, String longitude, String latitude, String citycode, String contact, String contactPhone, Long oilCompanyId, String localIp, Integer mpId, Long province, Long city, Long area, String serve) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.creator = creator;
        this.createTime = createTime;
        this.modifier = modifier;
        this.modifyTime = modifyTime;
        this.deleted = deleted;
        this.longitude = longitude;
        this.latitude = latitude;
        this.citycode = citycode;
        this.contact = contact;
        this.contactPhone = contactPhone;
        this.oilCompanyId = oilCompanyId;
        this.localIp = localIp;
        this.mpId = mpId;
        this.province = province;
        this.city = city;
        this.area = area;
        this.serve = serve;
    }

    public GasStation(Long id, String name, String address, Long creator, LocalDateTime createTime, Long modifier, LocalDateTime modifyTime, Boolean deleted, String longitude, String latitude, String citycode, String contact, String contactPhone, Long oilCompanyId, String localIp, Integer mpId, Long province, Long city, Long area, String serve, String discount) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.creator = creator;
        this.createTime = createTime;
        this.modifier = modifier;
        this.modifyTime = modifyTime;
        this.deleted = deleted;
        this.longitude = longitude;
        this.latitude = latitude;
        this.citycode = citycode;
        this.contact = contact;
        this.contactPhone = contactPhone;
        this.oilCompanyId = oilCompanyId;
        this.localIp = localIp;
        this.mpId = mpId;
        this.province = province;
        this.city = city;
        this.area = area;
        this.serve = serve;
        this.discount = discount;
    }

    public GasStation() {
        super();
    }

}
