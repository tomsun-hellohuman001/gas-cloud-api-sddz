package com.sddz.gasstation.dao;

import com.sddz.gasstation.entity.PC.OilCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <Description> 油企 <br>
 *
 * @author zhaopengwei<br>
 */
public interface OilCompanyRepository extends JpaRepository<OilCompany, Long>, JpaSpecificationExecutor<OilCompany> {

}
