package com.sddz.gasstation.dao;

import com.sddz.gasstation.entity.PC.GasStationConfigDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GasStationConfigDefinitionRepository extends JpaRepository<GasStationConfigDefinition, Long>, JpaSpecificationExecutor<GasStationConfigDefinition> {

    List<GasStationConfigDefinition> findByGroup(String group);

    GasStationConfigDefinition findByGroupAndKey(String group, String key);
}
