package com.sddz.gasstation.controller;

import com.sddz.gasstation.dao.mapper.GasWxMpMapper;
import com.sddz.gasstation.entity.PC.GasWxMp;
import com.ycy.common.web.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("WxMp")
public class GasWxMpController {
    @Autowired
    private GasWxMpMapper gasWxMpMapper;

    @GetMapping(value = "/selectWxMpByCondition")
    public RestResult<List<GasWxMp>> selectWxMpByCondition(Long id){
        return RestResult.success(gasWxMpMapper.selectWxMpByCondition(id));
    }
}
