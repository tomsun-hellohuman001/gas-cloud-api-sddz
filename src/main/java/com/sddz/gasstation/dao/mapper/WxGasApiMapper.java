package com.sddz.gasstation.dao.mapper;

import com.sddz.gasstation.entity.wx.GasStationLocationInfo;
import com.sddz.gasstation.entity.wx.StationNavigate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface WxGasApiMapper {

    GasStationLocationInfo getAdminGasStation(HashMap<String, Object> paraMap);
    List<StationNavigate> getStationNavigateByPrice(Map<String, Object> queryParam);

    List<StationNavigate> getStationNavigate(Map<String, Object> queryParam);
}
