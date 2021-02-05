package com.sddz.gasstation.controller;

import com.sddz.gasstation.dto.GasGunDto;
import com.sddz.gasstation.param.GasGunAddParam;
import com.sddz.gasstation.param.GasGunEditParam;
import com.sddz.gasstation.param.GasGunQueryParam;
import com.sddz.gasstation.param.IdPostParam;
import com.sddz.gasstation.service.GasStationService;
import com.ycy.common.web.PageableRestResult;
import com.ycy.common.web.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/gasGun")
@Api(tags = "加油枪接口")
public class GasGunController {

    @Autowired
    private GasStationService gasStationService;

    @PostMapping("add")
    @ApiOperation("添加油枪")
    public RestResult add(@RequestBody GasGunAddParam param){
        GasGunDto gasGunDto = gasStationService.addGasGun(param);
        return RestResult.success(gasGunDto);
    }

    @PostMapping("edit")
    @ApiOperation("修改油枪")
    public RestResult edit(@RequestBody GasGunEditParam param){
        GasGunDto gasGunDto = gasStationService.updateGasGun(param);
        return RestResult.success(gasGunDto);
    }

    @PostMapping("delete")
    @ApiOperation("删除油枪")
    public RestResult delete(@RequestBody IdPostParam param){
        gasStationService.deleteGasGun(param.getId());
        return RestResult.success();
    }

//    @GetMapping("list")
//    @ApiOperation("查询油枪列表")
//    public RestResult list(@RequestParam Long machineId){
//        List<GasGunDto> gasGuns = gasStationService.getGasGuns(machineId);
//        return RestResult.success(gasGuns);
//    }

    @GetMapping("getList")
    @ApiOperation("查询油枪列表")
    public PageableRestResult<List<GasGunDto>> getList(GasGunQueryParam param){
        Page<GasGunDto> gasGuns = gasStationService.getGasGuns(param);
        return PageableRestResult.success(gasGuns.getContent(), gasGuns.getTotalPages(), gasGuns.getTotalElements());
    }

//    @GetMapping("/guns")
//    @ApiOperation("油枪号")
//    public RestResult<List<GasGunDto>> getGuns(Long stationId) {
//        List<GasGunDto> guns = gasStationService.getGasGunsByStationId(stationId);
//        return RestResult.success(guns);
//    }
}
