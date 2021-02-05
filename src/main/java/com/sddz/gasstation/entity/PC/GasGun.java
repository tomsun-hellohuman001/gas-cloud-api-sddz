package com.sddz.gasstation.entity.PC;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "gas_gun")
@Getter
@Setter
public class GasGun extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "machine_id")
    private Long machineId;

    @Column(name = "oil_type")
    private Long oilType;

    @Column(name = "station_id")
    private Long stationId;

    @Column(name = "company_id")
    private Long companyId;

    @ManyToOne
    @JoinColumn(name = "station_id", insertable = false, updatable = false)
    private GasStation station;

//    @ManyToMany
//    @JoinTable(
//            name = "gas_screen_gun_relation",
//            joinColumns = @JoinColumn(name = "gun_id"),
//            inverseJoinColumns = @JoinColumn(name = "screen_id"))
//    private List<GasScreen> screens;

    @ManyToOne
    @JoinColumn(name = "oil_type", insertable = false, updatable = false)
    @NotFound(action= NotFoundAction.IGNORE)
    private OilProduct gasGoods;
}
