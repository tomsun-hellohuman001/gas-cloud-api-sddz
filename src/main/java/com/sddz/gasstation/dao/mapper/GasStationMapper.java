package com.sddz.gasstation.dao.mapper;

import com.sddz.gasstation.dto.GasStationDto;
import com.sddz.gasstation.entity.PC.GasStation;
import com.sddz.gasstation.entity.wx.GasStationExample;
import com.sddz.gasstation.param.GasStationQueryParam;

import java.util.List;

public interface GasStationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(GasStation record);

    int insertSelective(GasStation record);

    List<GasStation> selectByExample(GasStationExample example);

    GasStation selectByPrimaryKey(Long id);
    List<GasStation> selectByPrimaryKeyEx(Long id);
    List<GasStationDto> selectByCondition(GasStationQueryParam param);
    Long selectCountByCondition(GasStationQueryParam param);

    int updateByPrimaryKeySelective(GasStation record);

    int updateByPrimaryKeyWithBLOBs(GasStation record);

    int updateByPrimaryKey(GasStation record);
}
