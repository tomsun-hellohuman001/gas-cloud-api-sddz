package com.sddz.gasstation.dao;

import com.sddz.gasstation.entity.PC.OilProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * <Description> 油品 <br>
 *
 * @author zhaopengwei<br> <br>
 */
public interface OilProductRepository extends JpaRepository<OilProduct, Long>, JpaSpecificationExecutor<OilProduct> {

    List<OilProduct> findByOilCompanyId(Long companyId);

    List<OilProduct> findByStationId(Long stationId);

    Optional<OilProduct> findByStationIdAndOilCode(Long stationId, Long oilCode);
}
