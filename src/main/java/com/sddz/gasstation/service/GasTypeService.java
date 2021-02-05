package com.sddz.gasstation.service;

import com.sddz.gasstation.dao.GasTypeRepository;
import com.sddz.gasstation.dto.GasTypeDto;
import com.sddz.gasstation.entity.PC.GasType;
import com.sddz.gasstation.param.GasTypeAddParam;
import com.sddz.gasstation.param.GasTypeEditParam;
import com.sddz.gasstation.param.GasTypeQueryParam;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <Description> 油号 <br>
 *
 * @author zhaopengwei<br>
 */
@Service
public class GasTypeService {

    @Autowired
    private GasTypeRepository gasTypeRepository;

    public Page<GasTypeDto> queryByPage(GasTypeQueryParam param) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(param.getPageNum() - 1, param.getPageSize(), sort);
        Page<GasType> page = gasTypeRepository.findAll((root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(param.getName())) {
                predicates.add(cb.like(root.get("name"), "%" + param.getName() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
        return page.map(gasType -> BeanConverter.convertBean(gasType, GasTypeDto.class));
    }

    @Transactional(rollbackFor = Exception.class)
    public GasTypeDto addGasType(GasTypeAddParam param) {
        GasType gasType = BeanConverter.convertBean(param, GasType.class);
        gasTypeRepository.save(gasType);
        return BeanConverter.convertBean(gasType, GasTypeDto.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateGasType(GasTypeEditParam param) {
        gasTypeRepository.findById(param.getId())
                .ifPresent(gasType -> {
                    BeanUtils.copyProperties(param, gasType);
                    gasTypeRepository.save(gasType);
                });
    }

    public List<GasTypeDto> all() {
        List<GasType> list = gasTypeRepository.findAll();
        return BeanConverter.convertList(list, GasTypeDto.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeGasType(Long id) {
        gasTypeRepository.deleteById(id);
    }

    public GasTypeDto getById(Long id) {
        return gasTypeRepository.findById(id)
                .map(gasType -> BeanConverter.convertBean(gasType, GasTypeDto.class))
                .orElse(null);
    }

    public Map<Long, String> getMap() {
        return all().stream()
                .collect(Collectors.toMap(GasTypeDto::getId, GasTypeDto::getName));
    }
}
