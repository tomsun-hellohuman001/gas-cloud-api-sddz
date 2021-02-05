package com.sddz.gasstation.controller;

import com.sddz.gasstation.dao.mapper.AdminSysDictionaryMapper;
import com.sddz.gasstation.entity.PC.AdminSysDictionary;
import com.sddz.gasstation.param.SysDictionaryParam;
import com.ycy.common.web.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dictionary")
public class AdminSysDictionaryController {
    @Autowired
    private AdminSysDictionaryMapper adminSysDictionaryMapper;

    @GetMapping(value = "/getSysDictionarybyCondition")
    @ResponseBody
    public RestResult<List<AdminSysDictionary>> getSysDictionarybyCondition(@RequestParam("type") String dicType){
        return RestResult.success(adminSysDictionaryMapper.selectByDicType(dicType));
    }

    @ApiOperation("根据字典类型获得字典")
    @PostMapping("getDictionaryByType")
    public RestResult<List<AdminSysDictionary>> getDictionaryByType(@RequestBody SysDictionaryParam param){
        return RestResult.success(adminSysDictionaryMapper.selectByDicTypeParam(param));
    }
}
