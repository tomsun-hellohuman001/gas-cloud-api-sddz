package com.sddz.gasstation.service.wx.impl;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.sddz.gasstation.dao.mapper.GasStationMapper;
import com.sddz.gasstation.dao.mapper.WxGasApiMapper;
import com.sddz.gasstation.dto.GasStationLocationDto;
import com.sddz.gasstation.entity.PC.GasStation;
import com.sddz.gasstation.entity.wx.GasStationExample;
import com.sddz.gasstation.entity.wx.GasStationLocationInfo;
import com.sddz.gasstation.service.wx.WxGasApiLocationService;
import com.sddz.gasstation.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.PI;

/**
 * @author sdd
 * @description
 * @creat 2020/7/2 20:08
 */
@Service
@Slf4j
public class WxGasApiLocationServiceImpl implements WxGasApiLocationService {

    private static final double EARTH_RADIUS = 6378.137;
    @Autowired
    WxGasApiMapper wxGasApiMapper;
    @Autowired
    GasStationMapper gasStationMapper;

    @Override
    public GasStationLocationDto getAdminGasStation(HashMap<String, Object> paraMap) {
        GasStationLocationInfo gasStationLocationInfo =wxGasApiMapper.getAdminGasStation(paraMap);
        GasStationLocationDto gasStationLocationDto =new GasStationLocationDto();
        if(ObjectUtils.isEmpty(gasStationLocationInfo)){
            return gasStationLocationDto;
        }
        BeanUtils.copyProperties(gasStationLocationInfo,gasStationLocationDto);
        return gasStationLocationDto;
    }
    @Override
    public List<GasStationLocationDto> getGasStation(String longitude,String latitude,String gasmc,String citycode) {
        GasStationExample gasStationExample=new GasStationExample();
        GasStationExample.Criteria criteria = gasStationExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andNameIsNotNull();
        if (!StringUtil.isEmpty(gasmc)){
            criteria.andNameLike("%"+gasmc+"%");
        }
        if (!StringUtil.isEmpty(citycode)){
            criteria.andCitycodeEqualTo(citycode);
        }
        List<GasStation> gasStations = gasStationMapper.selectByExample(gasStationExample);
        log.info("加油站列表为：{}", JSONArray.toJSONString(gasStations));
        if(CollectionUtils.isEmpty(gasStations)){
            gasStations= Lists.newArrayList();
        }
        List<GasStationLocationDto> gasStationLocationDtos= new ArrayList<>();
        //计算距离，并封装DTO
        gasStationLocationDtos = gasStations.stream().map(gasStation -> {
            GasStationLocationDto gasStationLocationDto = new GasStationLocationDto();
            BeanUtils.copyProperties(gasStation,gasStationLocationDto);
            gasStationLocationDto.setDistance(
                    (long)calcDistance(
                            Double.valueOf(longitude),
                            Double.valueOf(latitude),
                            Double.valueOf(gasStation.getLongitude()),
                            Double.valueOf(gasStation.getLatitude())));
            return gasStationLocationDto;
        }).collect(Collectors.toList());
        //排序
        gasStationLocationDtos.sort((o1, o2) -> ((int)(o1.getDistance()-o2.getDistance())));
        log.info("按距离排序后的加油站列表：{}",JSONArray.toJSONString(gasStationLocationDtos));
        return gasStationLocationDtos;
    }
    private double Rad(double dDegree){
        return dDegree * PI / 180.0;
    }
    private double round(double d) {
        return Math.floor(d + 0.5);
    }
    //计算地球表面两点距离
    public double calcDistance(double Longitude1, double Latitude1, double Longitude2, double Latitude2){
        double dRadLat1 = Rad(Latitude1);
        double dRadLat2 = Rad(Latitude2);
        double a = dRadLat1 - dRadLat2;
        double b = Rad(Longitude1) - Rad(Longitude2);

        double dRad = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(dRadLat1) * Math.cos(dRadLat2) * Math.pow(Math.sin(b / 2), 2)));
        double dDistance = dRad * EARTH_RADIUS;
        dDistance = round(dDistance * 10000) / 10000;
        return Math.abs(dDistance);
    }

}
