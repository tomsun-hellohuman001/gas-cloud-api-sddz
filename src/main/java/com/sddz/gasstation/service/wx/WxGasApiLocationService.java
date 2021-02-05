package com.sddz.gasstation.service.wx;

import com.sddz.gasstation.dto.GasStationLocationDto;

import java.util.HashMap;
import java.util.List;

/**
 * @author sdd
 * @description
 * @creat 2020/7/2 20:08
 */
public interface WxGasApiLocationService {
    /**
     * 加油站定位服务
     * @param paraMap
     * @return
     */
    GasStationLocationDto getAdminGasStation(HashMap<String, Object> paraMap);
    /**
     * 获取加油站信息
     * @return
     */
    List<GasStationLocationDto> getGasStation(String longitude, String latitude, String gasmc, String citycode);
}
