package com.sddz.gasstation.service;

import com.sddz.gasstation.dao.mapper.AdminChinaAreaMapper;
import com.sddz.gasstation.entity.PC.AdminChinaArea;
import com.sddz.gasstation.param.ChinaAreaQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminChinaAreaService {

	@Autowired
	private AdminChinaAreaMapper adminChinaAreaMapper;

	public List<AdminChinaArea> getRegionByPid(ChinaAreaQueryParam param) {
		return adminChinaAreaMapper.getRegionByPid(param);
	}
}
