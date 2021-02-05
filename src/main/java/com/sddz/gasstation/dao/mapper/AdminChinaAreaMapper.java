package com.sddz.gasstation.dao.mapper;


import com.sddz.gasstation.entity.PC.AdminChinaArea;
import com.sddz.gasstation.param.ChinaAreaQueryParam;

import java.util.List;

/**
 * @author yangdb
 * @description
 * @creat 2020/12/18 10:57
 */
public interface AdminChinaAreaMapper {

    List<AdminChinaArea> getRegionByPid(ChinaAreaQueryParam param);
}
