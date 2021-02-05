package com.sddz.gasstation.service;

import com.sddz.gasstation.dao.OilCompanyRepository;
import com.sddz.gasstation.dto.OilCompanyDto;
import com.sddz.gasstation.entity.PC.GasStation;
import com.sddz.gasstation.entity.PC.OilCompany;
import com.sddz.gasstation.exception.BusinessException;
import com.sddz.gasstation.exception.ErrorCode;
import com.sddz.gasstation.param.OilCompanyAddParam;
import com.sddz.gasstation.param.OilCompanyEditParam;
import com.sddz.gasstation.param.OilCompanyQueryParam;
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
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <Description> 油企 <br>
 *
 * @author zhaopengwei<br>
 */
@Service
public class OilCompanyService {

    @Autowired
    private OilCompanyRepository oilCompanyRepository;

//    @Autowired
//    private AdminUserService adminUserService;

    public Page<OilCompanyDto> queryByPage(OilCompanyQueryParam param) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(param.getPageNum() - 1, param.getPageSize(), sort);
        Page<OilCompany> oilCompanies = oilCompanyRepository.findAll((root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(param.getName())) {
                predicates.add(cb.like(root.get("name"), "%" + param.getName() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
        return oilCompanies.map(oilCompany -> BeanConverter.convertBean(oilCompany, OilCompanyDto.class));
    }

    @Transactional(rollbackFor = Exception.class)
    public OilCompanyDto addCompany(OilCompanyAddParam param) {
        OilCompany oilCompany = BeanConverter.convertBean(param, OilCompany.class);
        oilCompanyRepository.save(oilCompany);
        return BeanConverter.convertBean(oilCompany, OilCompanyDto.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateCompany(OilCompanyEditParam param) {
        oilCompanyRepository.findById(param.getId())
                .ifPresent(oilCompany -> {
                    BeanUtils.copyProperties(param, oilCompany);
                    oilCompanyRepository.save(oilCompany);
                });
    }

    /**
     * FIXME !!!!!! 定时任务
     * @return
     */
    public List<OilCompanyDto> all() {
        List<OilCompany> list = oilCompanyRepository.findAll();
        // 不做权限判断。sunyt
//        Optional<Long> userId = AdminUserIdHolder.findUserId();
//        if (userId.isPresent()) {
//            AdminUserInfoDto adminUserInfo = adminUserService.getAdminUserInfo(userId.get());
//            if (!adminUserInfo.getSuperAdmin()) {
//                Optional<OilCompany> company = list.stream().filter(oilCompany -> oilCompany.getId().equals(adminUserInfo.getCompanyId())).findFirst();
//                if (company.isPresent()) {
//                    return Lists.newArrayList(BeanConverter.convertBean(company.get(), OilCompanyDto.class));
//                }
//            }
//        }
        return BeanConverter.convertList(list, OilCompanyDto.class);
    }

    public Map<Long, String> getMap() {
        return all().stream()
                .collect(Collectors.toMap(OilCompanyDto::getId, OilCompanyDto::getName));
    }

    public void deleteCompany(Long id) {
        Optional<OilCompany> company = oilCompanyRepository.findById(id);
        if (company.isPresent()) {
            List<GasStation> stations = company.get().getStations();
            if (!CollectionUtils.isEmpty(stations)) {
                throw new BusinessException(ErrorCode.COMPANY_CANNOT_DELELTE_FOR_STATION);
            }
            oilCompanyRepository.delete(company.get());
        }
    }

    public OilCompany getById(Long companyId) {
        Optional<OilCompany> company = oilCompanyRepository.findById(companyId);
        return company.get();
    }

    public List<OilCompanyDto> findAll(String hasEleCard) {
        List<OilCompany> list  = oilCompanyRepository.findAll((root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(hasEleCard)) {
                predicates.add(cb.equal(root.get("hasEleCard"), hasEleCard));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        List<OilCompanyDto> results = list.stream().map(UserCardBind -> {
            OilCompanyDto oilCompanyDto = BeanConverter.convertBean(UserCardBind, OilCompanyDto.class);
            return oilCompanyDto;
        }).collect(Collectors.toList());
        return results;
    }
}
