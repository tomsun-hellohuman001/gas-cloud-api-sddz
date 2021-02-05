package com.sddz.gasstation.dao;

import com.sddz.gasstation.entity.PC.GasGun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GasGunRepository extends JpaRepository<GasGun, Long>, JpaSpecificationExecutor<GasGun> {

//    List<GasGun> findByMachineId(Long machineId);

    GasGun findByName(String name);
    List<GasGun> findByStationId(Long stationId);
}
