package com.sddz.gasstation.controller;

import com.sddz.gasstation.dao.mapper.OilCompanyMapper;
import com.sddz.gasstation.dto.OilCompanyDto;
import com.sddz.gasstation.entity.PC.OilCompany;
import com.sddz.gasstation.param.IdPostParam;
import com.sddz.gasstation.param.OilCompanyAddParam;
import com.sddz.gasstation.param.OilCompanyEditParam;
import com.sddz.gasstation.param.OilCompanyQueryParam;
import com.sddz.gasstation.service.OilCompanyService;
import com.ycy.common.web.PageableRestResult;
import com.ycy.common.web.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oilCompany")
public class OilCompanyController {
    @Autowired
    private OilCompanyMapper oilCompanyMapper;

    @Autowired
    private OilCompanyService oilCompanyService;

    @GetMapping(value = "/showOilCompany/{id}")
    @ResponseBody
    public OilCompany select(@PathVariable("id") Long id){
        return oilCompanyMapper.selectByPrimaryKey(id);
    }

    @GetMapping(value = "/showOilCompanybyCondition")
    @ResponseBody
    public RestResult<List<OilCompany>> selectEx(Long id){
        return RestResult.success(oilCompanyMapper.selectByCondition(id));
    }

    @GetMapping("/list")
    @ApiOperation("油企列表")
    public PageableRestResult<List<OilCompanyDto>> queryByPage(OilCompanyQueryParam param) {
        Page<OilCompanyDto> page = oilCompanyService.queryByPage(param);
        return PageableRestResult.success(page.getContent(), page.getTotalPages(), page.getTotalElements());
    }

    @PostMapping("/add")
    @ApiOperation("新增油企")
    public RestResult addCompany(@RequestBody @Validated OilCompanyAddParam param) {
        oilCompanyService.addCompany(param);
        return RestResult.success();
    }

    @PostMapping("/edit")
    @ApiOperation("编辑油企")
    public RestResult editCompany(@RequestBody @Validated OilCompanyEditParam param) {
        oilCompanyService.updateCompany(param);
        return RestResult.success();
    }

    @PostMapping("/delete")
    @ApiOperation("删除油企")
    public RestResult deleteCompany(@RequestBody IdPostParam param) {
        oilCompanyService.deleteCompany(param.getId());
        return RestResult.success();
    }
}
