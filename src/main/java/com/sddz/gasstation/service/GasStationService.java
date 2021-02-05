package com.sddz.gasstation.service;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.sddz.gasstation.dao.GasGunRepository;
import com.sddz.gasstation.dao.mapper.GasStationMapper;
import com.sddz.gasstation.dto.GasGunDto;
import com.sddz.gasstation.entity.PC.GasGun;
import com.sddz.gasstation.entity.PC.GasStation;
import com.sddz.gasstation.exception.BusinessException;
import com.sddz.gasstation.param.GasGunAddParam;
import com.sddz.gasstation.param.GasGunEditParam;
import com.sddz.gasstation.param.GasGunQueryParam;
import com.sddz.gasstation.param.GasStationAddParam;
import com.sddz.gasstation.utils.BeanConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GasStationService {

    @Autowired
    private GasStationMapper gasStationMapper;

    @Autowired
    private GasGunRepository gasGunRepository;

    /**
     * 添加加油站
     *
     * @param param
     * @return
     */
    public int addGasStation(GasStationAddParam param) {
        GasStation gasStation = BeanConverter.convertBean(param, GasStation.class);
//        GasStation savedGasStation = gasStationRepository.save(gasStation);
//        return BeanConverter.convertBean(savedGasStation, GasStationDto.class);

//        gasStation.setLatitude(String.valueOf(param.getLatitude()));
        return gasStationMapper.insert(gasStation);
    }

    /**
     * 修改加油站
     *
     * @param param
     * @return
     */
    public int updateGasStation(GasStationAddParam param) {
        GasStation gasStation = BeanConverter.convertBean(param, GasStation.class);
//        GasStation savedGasStation = gasStationRepository.save(gasStation);
//        return BeanConverter.convertBean(savedGasStation, GasStationDto.class);

//        gasStation.setLatitude(String.valueOf(param.getLatitude()));
        return gasStationMapper.updateByPrimaryKeyWithBLOBs(gasStation);
    }

    // 删除油枪
    public void deleteGasGun(Long id) {
        gasGunRepository.deleteById(id);
    }

//    public List<GasGunDto> getGasGuns(Long machineId) {
//        List<GasGun> gasGuns = gasGunRepository.findByMachineId(machineId);
//        return BeanConverter.convertList(gasGuns, GasGunDto.class);
//    }

    /**
     * 分页查询油枪列表
     *
     * @param param
     * @return
     */
    public Page<GasGunDto> getGasGuns(GasGunQueryParam param) {
        Pageable pageable = PageRequest.of(param.getPageNum() - 1, param.getPageSize());

        Page<GasGun> gasGuns = gasGunRepository.findAll((root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (param.getStationId() != null) {
                predicates.add(cb.equal(root.get("stationId"), param.getStationId()));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
        return gasGuns.map(gasGun -> {
            GasGunDto gasGunDto = BeanConverter.convertBean(gasGun, GasGunDto.class);
            gasGunDto.setStationName(gasGun.getStation().getName());
//            if (!CollectionUtils.isEmpty(gasGun.getScreens())) {
//                gasGunDto.setScreenIds(gasGun.getScreens().stream().map(GasScreen::getId).collect(Collectors.toList()));
//                gasGunDto.setScreenSerialNos(gasGun.getScreens().stream().map(GasScreen::getSerialNo).collect(Collectors.toList()));
//            }
            gasGunDto.setOilTypeName(gasGun.getGasGoods().getGasType().getName());
            return gasGunDto;
        });
    }

    /**
     * 获取加油站基础数据
     *
     * @param stationId
     * @return
     */
    public GasStation getGasStationById(Long stationId) {
//        Optional<GasStation> gasStation = gasStationRepository.findById(stationId);
        //这里可能会有问题。sunyt
        Optional<GasStation> gasStation = Optional.ofNullable(gasStationMapper.selectByPrimaryKey(stationId));
        return gasStation.orElse(null);
    }

    /**
     * 通过stationId获取油枪集合
     * @param stationId
     * @return
     */
//    public List<GasGunDto> getGasGunsByStationId(Long stationId) {
//        if (stationId!=null){
//            Optional<GasStation> byId = gasStationRepository.findById(stationId);
//            GasStation gasStation = byId.orElseGet(() -> {
//                throw new BusinessException(ErrorCode.FIND_EX);
//            });
//            return BeanConverter.convertList(gasStation.getGuns(), GasGunDto.class);
//        }
//        else return null;
//    }

    public GasGunDto addGasGun(GasGunAddParam param) {
        GasGun gasGun = BeanConverter.convertBean(param, GasGun.class);
//        if (!CollectionUtils.isEmpty(param.getScreenIds())) {
//            gasGun.setScreens(param.getScreenIds().stream().map(screenId -> gasScreenRepository.getOne(screenId)).collect(Collectors.toList()));
//        }
        return BeanConverter.convertBean(gasGunRepository.save(gasGun), GasGunDto.class);
    }

    public GasGunDto updateGasGun(GasGunEditParam param) {
        Optional<GasGun> gasGunOptional = gasGunRepository.findById(param.getId());
        if (gasGunOptional.isPresent()) {
            GasGun gasGun = gasGunOptional.get();
            BeanUtils.copyProperties(param, gasGun);
//            gasGun.setScreens(param.getScreenIds().stream().map(screenId -> gasScreenRepository.getOne(screenId)).collect(Collectors.toList()));
            return BeanConverter.convertBean(gasGunRepository.save(gasGun), GasGunDto.class);
        }
        return null;
    }
}
