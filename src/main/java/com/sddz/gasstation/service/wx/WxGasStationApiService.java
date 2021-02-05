package com.sddz.gasstation.service.wx;

import com.sddz.gasstation.entity.wx.StationNavigate;
import com.sddz.gasstation.param.StationNavigateParam;
import com.sddz.gasstation.param.WxMpParam;

import java.util.List;
import java.util.Map;

public interface WxGasStationApiService {

    /**
     * 获得油站导航信息
     * @param param
     * @return
     */
    List<StationNavigate> getStationNavigateInfo(StationNavigateParam param);

    Map<String,String> getWxMpInfo(WxMpParam param);
}
