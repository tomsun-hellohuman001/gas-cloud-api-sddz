package com.sddz.gasstation.entity.PC;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "gas_station_config_definition")
@Data
public class GasStationConfigDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`group`")
    private String group;

    @Column(name = "`key`")
    private String key;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name="`desc`")
    private String desc;
}
