package com.sddz.gasstation.service.wx.impl;

import com.sddz.gasstation.dao.mapper.GasWxMpMapper;
import com.sddz.gasstation.dao.mapper.WxGasApiMapper;
import com.sddz.gasstation.entity.PC.GasWxMp;
import com.sddz.gasstation.entity.wx.StationNavigate;
import com.sddz.gasstation.enums.StationNavitionSortEnum;
import com.sddz.gasstation.param.StationNavigateParam;
import com.sddz.gasstation.param.WxMpParam;
import com.sddz.gasstation.service.WxCommonService;
import com.sddz.gasstation.service.wx.WxGasStationApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Slf4j
public class WxGasStationApiServiceImpl implements WxGasStationApiService {

    @Autowired
    WxGasApiMapper wxGasApiMapper;

    @Autowired
    GasWxMpMapper gasWxMpMapper;

    @Autowired
    WxCommonService wxCommonService;

    @Override
    public List<StationNavigate> getStationNavigateInfo(StationNavigateParam param) {

        List<StationNavigate> stationLists = null;
        Map<String,Object> queryParam = new HashMap<String,Object>();
        if(param.getCity()!=null){
            queryParam.put("city",param.getCity());
        }
        if(StringUtils.isNotEmpty(param.getName())){
            queryParam.put("stationName",param.getName());
        }
        if(param.getDistance()!=null){
            queryParam.put("distance",param.getDistance());
        }
        if(param.getTargetLng()!=null){
            queryParam.put("lng",param.getTargetLng());
        }
        if(param.getTargetLat()!=null){
            queryParam.put("lat",param.getTargetLat());
        }
        if(param.getCompanyId() != null) {
            queryParam.put("companyId", param.getCompanyId());
        }
        if(StringUtils.isNotEmpty(param.getSort())){
            String sortField = null;
            //高价格筛选/低价格筛选
            if(StationNavitionSortEnum.HIGHTPRICE.getCode().equalsIgnoreCase(param.getSort()) ||StationNavitionSortEnum.LOWPRICE.getCode().equalsIgnoreCase(param.getSort())){
                if(StationNavitionSortEnum.HIGHTPRICE.getCode().equalsIgnoreCase(param.getSort()) ){
                    queryParam.put("sort",StationNavitionSortEnum.HIGHTPRICE.getSort());
                }else if(StationNavitionSortEnum.LOWPRICE.getCode().equalsIgnoreCase(param.getSort())){
                    queryParam.put("sort",StationNavitionSortEnum.LOWPRICE.getSort());
                }
                stationLists = wxGasApiMapper.getStationNavigateByPrice(queryParam);
            //近距离筛选
            }else if(StationNavitionSortEnum.NEAREST.getCode().equalsIgnoreCase(param.getSort())){
                //通过下面
                queryParam.put("sort","ASC");
                stationLists = wxGasApiMapper.getStationNavigate(queryParam);
            }
        }else {
            stationLists = wxGasApiMapper.getStationNavigate(queryParam);
        }
        return stationLists;
    }

    @Override
    public Map<String, String> getWxMpInfo(WxMpParam param) {
        Optional<GasWxMp> gasWxMpOptional = Optional.ofNullable(gasWxMpMapper.findByMpName(param.getMpName()));
        GasWxMp gasWxMp = null;
        Map<String, String> jsSdkSign = null;
        if(gasWxMpOptional.isPresent()){
            gasWxMp =  gasWxMpOptional.get();
            if(StringUtils.isNotEmpty(gasWxMp.getAppId())){
                String accessToken = wxCommonService.getAccessTokenCache(gasWxMp.getAppId(), gasWxMp.getAppSecret());
                String signUrl = param.getUrl();
                String jsTicket = wxCommonService.getJsTicket(accessToken);
                if (StringUtils.contains(signUrl, "#")) {
                    signUrl = signUrl.substring(0, signUrl.indexOf("#"));
                }
                jsSdkSign =  wxCommonService.jssdkSign(gasWxMp.getAppId(),signUrl, jsTicket);
            }
        }
        return jsSdkSign;
    }
}
