package com.sddz.gasstation.dao.mapper;

import com.sddz.gasstation.dto.GasOrdersDto;
import com.sddz.gasstation.entity.PC.GasOrders;
import com.sddz.gasstation.param.GasOrderQueryParam;

import java.util.List;

public interface GasOrdersMapper {

    int deleteByPrimaryKey(Long id);

    int insert(GasOrders record);

    int insertSelective(GasOrders record);

    GasOrders selectByPrimaryKey(Long id);
    List<GasOrdersDto> selectDailyReportByCondition(GasOrderQueryParam param);

    int updateByPrimaryKeySelective(GasOrders record);

    int updateByPrimaryKey(GasOrders record);

    List<GasOrdersDto>  selectGasOrderByCondition(GasOrderQueryParam param);

    Long selectCountGasOrderByCondition(GasOrderQueryParam param);
}
