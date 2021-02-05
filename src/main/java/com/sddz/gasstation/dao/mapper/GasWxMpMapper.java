package com.sddz.gasstation.dao.mapper;

import com.sddz.gasstation.entity.PC.GasWxMp;

import java.util.List;

public interface GasWxMpMapper {

    int deleteByPrimaryKey(Long id);

    int insert(GasWxMp record);

    int insertSelective(GasWxMp record);

    GasWxMp selectByPrimaryKey(Long id);
    List<GasWxMp> selectWxMpByCondition(Long id);
    GasWxMp findByMpName(String mpName);

    int updateByPrimaryKeySelective(GasWxMp record);

    int updateByPrimaryKey(GasWxMp record);
}
