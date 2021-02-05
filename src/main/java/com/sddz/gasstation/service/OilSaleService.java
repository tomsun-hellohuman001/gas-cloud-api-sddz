package com.sddz.gasstation.service;

import com.google.common.collect.Lists;
import com.sddz.gasstation.dao.OilProductRepository;
import com.sddz.gasstation.dao.OilSaleRepository;
import com.sddz.gasstation.dto.OilProductDto;
import com.sddz.gasstation.dto.OilSaleDto;
import com.sddz.gasstation.entity.PC.OilSale;
import com.sddz.gasstation.enums.OilSaleConfirmStatusEnum;
import com.sddz.gasstation.enums.OilSaleStatusEnum;
import com.sddz.gasstation.param.OilSaleAddParam;
import com.sddz.gasstation.param.OilSaleEditParam;
import com.sddz.gasstation.param.OilSaleQueryParam;
import com.sddz.gasstation.utils.BeanConverter;
//import com.shufei.gasstation.remote.card.CardService;
//import com.shufei.gasstation.socket.staff.StaffMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <Description> 油品上架 <br>
 *
 * @author zhaopengwei<br>
 */
@Service
public class OilSaleService {

    @Autowired
    private OilSaleRepository oilSaleRepository;

    @Autowired
    private OilCompanyService oilCompanyService;

    @Autowired
    private GasStationService gasStationService;

//    @Autowired
//    private UserGroupService userGroupService;

    @Autowired
    private OilProductService oilProductService;

//    @Autowired
//    private StaffMessageService staffMessageService;

//    @Autowired
//    private CardService cardService;

    @Autowired
    private OilProductRepository oilProductRepository;

    public Page<OilSaleDto> queryByPage(OilSaleQueryParam param) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(param.getPageNum() - 1, param.getPageSize(), sort);
        Page<OilSale> oilSales = oilSaleRepository.findAll((root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(param.getOilProductId())) {
                predicates.add(cb.equal(root.get("oilProductId"), param.getOilProductId()));
            }
            if (Objects.nonNull(param.getOilCompanyId())) {
                predicates.add(cb.equal(root.get("oilCompanyId"), param.getOilCompanyId()));
            }
            if (Objects.nonNull(param.getGasStationId())) {
                predicates.add(cb.equal(root.get("gasStationId"), param.getGasStationId()));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
        Map<Long, String> oilCompanyMap = oilCompanyService.getMap();
        Map<Long, String> oilProductMap = oilProductService.all()
                .stream()
                .collect(Collectors.toMap(OilProductDto::getId, OilProductDto::getGasType));
        return oilSales.map(oilSale -> {
            OilSaleDto dto = BeanConverter.convertBean(oilSale, OilSaleDto.class);
            dto.setOilCompany(oilCompanyMap.get(dto.getOilCompanyId()));
            dto.setOilProduct(oilProductMap.get(dto.getOilProductId()));
            dto.setGasStation(gasStationService.getGasStationById(oilSale.getGasStationId()).getName());
//            if (!CollectionUtils.isEmpty(oilSale.getParticularPrice())) {
//                StringBuilder sb = new StringBuilder();
//                List<OilSaleParticularPrice> prices = oilSale.getParticularPrice();
//                for (OilSaleParticularPrice price : prices) {
//                    userGroupService.getById(price.getUserGroupId())
//                            .ifPresent(group -> sb.append(group.getName()).append(":").append(price.getPrice()).append(","));
//                }
//                dto.setParticularPrices(sb.toString());
//            }
            return dto;
        });
    }

    public List<OilSaleDto> queryWaitOnSale(Long gasStationId){
        List<OilSale> oilSales = oilSaleRepository.findByGasStationIdAndStatusInAndConfirmStatus(gasStationId, Lists.newArrayList(OilSaleStatusEnum.WAIT_ON_SALE.getValue(), OilSaleStatusEnum.ON_SALE.getValue()), OilSaleConfirmStatusEnum.SENT.getValue());
        Map<Long, String> oilCompanyMap = oilCompanyService.getMap();
        Map<Long, OilProductDto> oilProductMap = oilProductService.all()
                .stream()
                .collect(Collectors.toMap(OilProductDto::getId, oilProductDto -> oilProductDto));
        return oilSales.stream().map(oilSale -> {
            OilSaleDto dto = BeanConverter.convertBean(oilSale, OilSaleDto.class);
            dto.setOilCompany(oilCompanyMap.get(dto.getOilCompanyId()));
            dto.setOilProduct(oilProductMap.get(dto.getOilProductId()).getGasType());
            dto.setOilCode(oilProductMap.get(dto.getOilProductId()).getOilCode());
            dto.setGasStation(gasStationService.getGasStationById(oilSale.getGasStationId()).getName());
//            if (!CollectionUtils.isEmpty(oilSale.getParticularPrice())) {
//                StringBuilder sb = new StringBuilder();
//                List<OilSaleParticularPrice> prices = oilSale.getParticularPrice();
//                for (OilSaleParticularPrice price : prices) {
//                    userGroupService.getById(price.getUserGroupId())
//                            .ifPresent(group -> sb.append(group.getName()).append(":").append(price.getPrice()).append(","));
//                }
//                dto.setParticularPrices(sb.toString());
//            }
            OilSaleDto previousSaleRule = getPreviousSaleRule(oilSale.getId());
            dto.setPreviousPrice(previousSaleRule.getListingPrice());
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public OilSaleDto addOilSale(OilSaleAddParam param) {
//        List<OilSale> list = oilSaleRepository.findSameTimeSaleByCompanyAndStationAndProduct(param.getOilCompanyId(),
//                param.getGasStationId(), param.getOilProductId(), param.getSaleTime(), param.getSoldOutTime());
//        if (!CollectionUtils.isEmpty(list)) {
//            throw new BusinessException(ErrorCode.OIL_SALE_TIME_EXIST);
//        }
        OilSale oilSale = BeanConverter.convertBean(param, OilSale.class);
        oilSale.setStatus(OilSaleStatusEnum.WAIT_ON_SALE.getValue());
        oilSale.setConfirmStatus(OilSaleConfirmStatusEnum.NOT_CONFIRM.getValue());
        oilSaleRepository.save(oilSale);
        return BeanConverter.convertBean(oilSale, OilSaleDto.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateOilSale(OilSaleEditParam param) {
//        boolean match = oilSaleRepository.findSameTimeSaleByCompanyAndStationAndProduct(param.getOilCompanyId(), param.getGasStationId(),
//                param.getOilProductId(), param.getSaleTime(), param.getSoldOutTime())
//                .stream()
//                .anyMatch(oilSale -> !oilSale.getId().equals(param.getId()));
//        if (match) {
//            throw new BusinessException(ErrorCode.OIL_SALE_TIME_EXIST);
//        }
        oilSaleRepository.findById(param.getId())
                .ifPresent(oilSale -> {
                    BeanUtils.copyProperties(param, oilSale);
                    oilSaleRepository.save(oilSale);
                });
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeOilSale(Long id) {
        oilSaleRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(OilSaleEditParam param) {
        oilSaleRepository.findById(param.getId())
                .ifPresent(oilSale -> {
                    oilSale.setStatus(param.getStatus());
                    oilSaleRepository.save(oilSale);
                });
    }

    /**
     * 根据油站、油品id获取油品上架方案
     *
     * @param gasGoodsId
     * @return
     */
    public Optional<OilSaleDto> getGasSaleRule(Long stationId, Long gasGoodsId) {
        List<OilSale> sales = oilSaleRepository.findByGasStationIdAndOilProductIdAndStatus(stationId, gasGoodsId, OilSaleStatusEnum.ON_SALE.getValue());
        Optional<OilSale> sale = sales.stream().max(Comparator.comparing(OilSale::getCreateTime));
        return sale.map(oilSale -> BeanConverter.convertBean(oilSale, OilSaleDto.class));
    }

//    /**
//     * 收银台调价接口
//     * @param gasGoodsId
//     * @param price
//     */
//    @Deprecated
//    public BigDecimal modifyPrice(Long gasGoodsId, BigDecimal price){
//        BigDecimal originalPrice;
//        List<OilSale> sale = oilSaleRepository.findByOilProductId(gasGoodsId);
//        if (CollectionUtils.isEmpty(sale)) {
//            throw new BusinessException(ErrorCode.NO_GAS_SALE_RULE);
//        }
//        Optional<OilSale> currentSale = sale.stream().filter(oilSale -> oilSale.getStatus() == OilSaleStatusEnum.ON_SALE.getValue()).findFirst();
//        OilSale oilSale = currentSale.get();
//        originalPrice = oilSale.getListingPrice();
//        oilSale.setListingPrice(price);
//        oilSale.setMemberPrice(price);
//        oilSaleRepository.save(oilSale);
//        return originalPrice;
//    }

//    /**
//     * 发送调价消息
//     * @param gasStationId
//     * @param oilSaleId
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public void sendPriceMessage(Long gasStationId,Long oilSaleId){
//        staffMessageService.sendPriceMessage(gasStationId);
//        oilSaleRepository.findById(oilSaleId).ifPresent(oilSale -> {
//            oilSale.setConfirmStatus(OilSaleConfirmStatusEnum.SENT.getValue());
//            oilSaleRepository.save(oilSale);
//        });
//    }
//
//    /**
//     * 确认调价
//     * @param param
//     */
//    public void confirmPrice(OilSaleConfirmParam param){
//        Optional<OilSale> oilSaleOptional = oilSaleRepository.findById(param.getOilSaleId());
//        oilSaleOptional.ifPresent(oilSale -> {
//            oilSale.setConfirmStatus(OilSaleConfirmStatusEnum.CONFIRMED.getValue());
//            oilSaleRepository.save(oilSale);
//            Optional<OilProduct> oilProductOptional = oilProductRepository.findById(oilSale.getOilProductId());
//            cardService.changePrice(oilSale.getGasStationId(),oilProductOptional.get().getOilCode(),oilSale.getListingPrice());
//        });
//    }

    /**
     * 获取油品上架方案
     * @param saleId
     * @return
     */
    public OilSaleDto getOilSale(Long saleId){
        OilSale sale = oilSaleRepository.getOne(saleId);
        OilSaleDto oilSaleDto = BeanConverter.convertBean(sale, OilSaleDto.class);
        OilSaleDto previousSaleRule = getPreviousSaleRule(saleId);
        if (previousSaleRule != null) {
            oilSaleDto.setPreviousPrice(previousSaleRule.getListingPrice());
        }
        OilProductDto oilProductDto = oilProductService.getById(sale.getOilProductId());
        oilSaleDto.setOilCode(oilProductDto.getOilCode());
        return oilSaleDto;
    }

    /**
     * 查询上一个油品上架方案
     * @param saleId
     * @return
     */
    public OilSaleDto getPreviousSaleRule(Long saleId){
        OilSale oilSale = oilSaleRepository.getOne(saleId);
        List<OilSale> sales = oilSaleRepository.findByGasStationIdAndOilProductIdOrderBySaleTimeDesc(oilSale.getGasStationId(), oilSale.getOilProductId());
        int index = -1;
        for(int i = 0; i < sales.size(); i++){
            OilSale sale = sales.get(i);
            if (sale.getId().equals(saleId) && i != sales.size() - 1) {
                index = i + 1;
            }
        }
        if (index == -1) {
            return null;
        }
        OilSale previousSale = sales.get(index);
        return BeanConverter.convertBean(previousSale, OilSaleDto.class);
    }

}
