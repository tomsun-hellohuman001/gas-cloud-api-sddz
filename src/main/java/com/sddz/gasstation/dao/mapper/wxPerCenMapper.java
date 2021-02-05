package com.sddz.gasstation.dao.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface wxPerCenMapper {

	public List<Map<String,String>> getOrderNumsBystatus(Long userId);
}
