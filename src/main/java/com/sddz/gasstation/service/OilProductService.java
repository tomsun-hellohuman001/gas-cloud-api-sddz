package com.sddz.gasstation.service;

import com.sddz.gasstation.dao.OilProductRepository;
import com.sddz.gasstation.dto.OilProductDto;
import com.sddz.gasstation.entity.PC.OilProduct;
import com.sddz.gasstation.param.OilProductAddParam;
import com.sddz.gasstation.param.OilProductEditParam;
import com.sddz.gasstation.param.OilProductQueryParam;
import com.sddz.gasstation.utils.BeanConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <Description> 油品 <br>
 *
 * @author zhaopengwei<br>
 */
@Service
public class OilProductService {

    @Autowired
    private OilProductRepository oilProductRepository;

    @Autowired
    private OilCompanyService oilCompanyService;

    @Autowired
    private GasTypeService gasTypeService;

    @Autowired
    private GasStationService gasStationService;

    public Page<OilProductDto> queryByPage(OilProductQueryParam param) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(param.getPageNum() - 1, param.getPageSize(), sort);
        Page<OilProduct> oilProducts = oilProductRepository.findAll((root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(param.getGasTypeId())) {
                predicates.add(cb.equal(root.get("gasTypeId"), param.getGasTypeId()));
            }
            if (Objects.nonNull(param.getOilCompanyId())) {
                predicates.add(cb.equal(root.get("oilCompanyId"), param.getOilCompanyId()));
            }
            if (Objects.nonNull(param.getStationId())) {
                predicates.add(cb.equal(root.get("stationId"), param.getStationId()));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
        Map<Long, String> oilCompanyMap = oilCompanyService.getMap();
        Map<Long, String> gasTypeMap = gasTypeService.getMap();
        return oilProducts.map(oilProduct -> {
            OilProductDto dto = BeanConverter.convertBean(oilProduct, OilProductDto.class);
            dto.setOilCompany(oilCompanyMap.get(dto.getOilCompanyId()));
            dto.setGasType(gasTypeMap.get(dto.getGasTypeId()));
            // 这里service的方法做了修改 sunyt
            dto.setStationName(gasStationService.getGasStationById(oilProduct.getStationId()).getName());
            return dto;
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public OilProductDto addProduct(OilProductAddParam param) {
        OilProduct product = BeanConverter.convertBean(param, OilProduct.class);
        product.setOilCompanyId(param.getOilCompanyId());
        product.setGasTypeId(param.getGasTypeId());
        product.setCreateTime(LocalDateTime.now());
        oilProductRepository.save(product);
        return BeanConverter.convertBean(product, OilProductDto.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(OilProductEditParam param) {
        oilProductRepository.findById(param.getId())
                .ifPresent(oilProduct -> {
                    BeanUtils.copyProperties(param, oilProduct);
                    oilProduct.setModifyTime(LocalDateTime.now());
                    oilProductRepository.save(oilProduct);
                });
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeProduct(Long id) {
        oilProductRepository.deleteById(id);
    }

    public List<OilProductDto> all() {
        Map<Long, String> gasTypeMap = gasTypeService.getMap();
        return oilProductRepository.findAll()
                .stream()
                .map(oilProduct -> {
                    OilProductDto dto = BeanConverter.convertBean(oilProduct, OilProductDto.class);
                    dto.setGasType(gasTypeMap.getOrDefault(oilProduct.getGasTypeId(), StringUtils.EMPTY));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * 根据油企获取油品
     * @param companyId
     * @return
     */
    @Deprecated
    public List<OilProductDto> getByCompany(Long companyId) {
        Map<Long, String> gasTypeMap = gasTypeService.getMap();
        List<OilProduct> products = oilProductRepository.findByOilCompanyId(companyId);
        List<OilProductDto> result = products.stream().map(oilProduct -> {
            OilProductDto oilProductDto = BeanConverter.convertBean(oilProduct, OilProductDto.class);
            oilProductDto.setGasType(gasTypeMap.getOrDefault(oilProduct.getGasTypeId(), StringUtils.EMPTY));
            return oilProductDto;
        }).collect(Collectors.toList());
        return result;
    }

    /**
     * 根据油站获取油品
     * @param stationId
     * @return
     */
    public List<OilProductDto> getByStation(Long stationId) {
        Map<Long, String> gasTypeMap = gasTypeService.getMap();
        List<OilProduct> products = oilProductRepository.findByStationId(stationId);
        List<OilProductDto> result = products.stream().map(oilProduct -> {
            OilProductDto oilProductDto = BeanConverter.convertBean(oilProduct, OilProductDto.class);
            oilProductDto.setGasType(gasTypeMap.getOrDefault(oilProduct.getGasTypeId(), StringUtils.EMPTY));
            return oilProductDto;
        }).collect(Collectors.toList());
        return result;
    }

    public OilProductDto getById(Long id) {
        OilProduct product = oilProductRepository.findById(id).orElse(null);
        if (Objects.isNull(product)) {
            return null;
        }
        Map<Long, String> gasTypeMap = gasTypeService.getMap();
        OilProductDto dto = BeanConverter.convertBean(product, OilProductDto.class);
        dto.setGasType(gasTypeMap.getOrDefault(product.getGasTypeId(), StringUtils.EMPTY));
        return dto;
    }
}
