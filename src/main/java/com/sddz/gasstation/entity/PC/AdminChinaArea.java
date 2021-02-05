package com.sddz.gasstation.entity.PC;

import lombok.Data;

import javax.persistence.*;

@Data
public class AdminChinaArea extends AuditBaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long value;

    private String name;

    private String label;

    private String pid;

    private Integer sort;

    private Integer level;

    private String longcode;

    private String code;

    private boolean leaf;
}
