package com.sddz.gasstation.controller;

//import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
//@Api(tags = "Test")
public class TestController {
//    @Autowired
//    private ThirdMerchantWithdrawJob thirdMerchantWithdrawJob;
//
//    @ApiOperation("提现")
//    @GetMapping("withdraw")
//    public void testWithdraw(){
//        thirdMerchantWithdrawJob.withdraw();
//    }
    @ResponseBody
    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello";
    }

}
