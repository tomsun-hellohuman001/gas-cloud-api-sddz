package com.sddz.gasstation.dao;

import com.sddz.gasstation.entity.PC.OilSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <Description> 油品上架 <br>
 *
 * @author zhaopengwei<br>
 */
public interface OilSaleRepository extends JpaRepository<OilSale, Long>, JpaSpecificationExecutor<OilSale> {

    List<OilSale> findByGasStationIdAndOilProductIdAndStatus(Long stationId, Long gasGoodsId, Integer status);

    List<OilSale> findByStatusIn(List<Integer> needStatus);

    /**
     * 查询销售时间重叠的同油企，油站，油品的上架方案
     *
     * @param companyId
     * @param stationId
     * @param productId
     * @param saleTime
     * @param soldOutTime
     * @return
     */
    @Query(value = "select * from oil_sale  os where " +
            "os.oil_company_id = ?1 " +
            "and os.gas_station_id = ?2 " +
            "and os.oil_product_id = ?3 " +
            "and not (os.sale_time >= ?4 or os.sold_out_time <= ?5)",
            nativeQuery = true)
    List<OilSale> findSameTimeSaleByCompanyAndStationAndProduct(Long companyId, Long stationId, Long productId, LocalDateTime saleTime, LocalDateTime soldOutTime);

    List<OilSale> findAllByGasStationIdAndOilProductIdAndStatusAndSaleTimeBeforeAndSoldOutTimeAfter(Long gasStationId, Long productId, Integer status, LocalDateTime saleTime, LocalDateTime soldOutTime);

    List<OilSale> findByOilProductId(Long oilProductId);

    List<OilSale> findAllByGasStationIdAndStatus(Long gasStationId, Integer status);

    List<OilSale> findByGasStationIdAndStatusInAndConfirmStatus(Long gasStationId, List<Integer> status, Integer confirmStatus);

    List<OilSale> findByGasStationIdAndOilProductIdOrderBySaleTimeDesc(Long stationId, Long gasGoodsId);
}
