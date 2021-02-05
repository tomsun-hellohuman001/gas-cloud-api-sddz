package com.sddz.gasstation.controller.wxapi;

import com.sddz.gasstation.dto.GasStationLocationDto;
import com.sddz.gasstation.entity.wx.StationNavigate;
import com.sddz.gasstation.param.StationNavigateParam;
import com.sddz.gasstation.param.WxMpParam;
import com.sddz.gasstation.service.wx.WxGasStationApiService;
import com.sddz.gasstation.service.wx.WxGasApiLocationService;
import com.ycy.common.web.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/wxGasApi")
@Api(tags = "wxGasApi")
@Slf4j
public class WxGasStationApiController {
    @Autowired
    WxGasApiLocationService wxGasApiLocationServiceImpl;
    @Autowired
    WxGasStationApiService wxGasStationApiServiceImpl;

    @GetMapping(value = "getGasStation", name = "加油站定位")
    @ResponseBody
    public RestResult<List<GasStationLocationDto>> getGasStation(@RequestParam(value = "longitude", defaultValue = "0", required = true) String longitude,
                                                                 @RequestParam(value = "latitude", defaultValue = "0", required = true) String latitude, @Param("gasmc") String gasmc, @Param("citycode") String citycode) {
        HashMap<String, Object> paramap = new HashMap<String, Object>();
        log.info("根据位置获取加油站，longitude:" + longitude + ",latitude：" + latitude);
        return RestResult.success(wxGasApiLocationServiceImpl.getGasStation(longitude, latitude, gasmc, citycode));
    }


    @PostMapping("/stationNnavigate")
    @ApiOperation("公众号站点导航")
    public RestResult<List<StationNavigate>> stationNavigate(@RequestBody @Validated StationNavigateParam param){
        return RestResult.success(wxGasStationApiServiceImpl.getStationNavigateInfo(param));
    }

    @PostMapping("/getWxMpInfo")
    @ApiOperation("获得公众号信息")
    public RestResult<Map<String,String>> getWxMpInfo(@RequestBody @Validated WxMpParam param){
        return RestResult.success(wxGasStationApiServiceImpl.getWxMpInfo(param));
    }
}
