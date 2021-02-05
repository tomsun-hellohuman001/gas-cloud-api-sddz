package com.sddz.gasstation.param;

import lombok.Data;

@Data
public class ChinaAreaQueryParam {

    private String pid;

    //查询级别
    private String levelLimit;
}
