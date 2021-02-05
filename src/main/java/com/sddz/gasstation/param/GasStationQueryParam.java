package com.sddz.gasstation.param;

import com.ycy.common.web.QueryParam;
import lombok.Data;

@Data
public class GasStationQueryParam extends QueryParam {

    private String name;

    private Long oilCompanyId;

//    @Override
//    public void setPageNum(int pageNum) {
//        if (pageNum > 0){
//            super.setPageNum(pageNum - 1);
//        }else{
//            super.setPageNum(pageNum);
//        }
//    }
}
