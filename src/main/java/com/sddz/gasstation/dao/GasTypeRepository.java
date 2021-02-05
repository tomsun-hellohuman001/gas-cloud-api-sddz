package com.sddz.gasstation.dao;

import com.sddz.gasstation.entity.PC.GasType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <Description> 油号 <br>
 *
 * @author zhaopengwei<br>
 */
public interface GasTypeRepository extends JpaRepository<GasType, Long>, JpaSpecificationExecutor<GasType> {

}
