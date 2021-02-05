package com.sddz.gasstation.param;

import com.ycy.common.web.QueryParam;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class GasOrderQueryParam extends QueryParam {

    private Long companyId;

    private Long stationId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private String month;

    private Long userId;

//    @Override
//    public void setPageNum(int pageNum) {
//        if (pageNum > 0){
//            super.setPageNum(pageNum - 1);
//        }else{
//            super.setPageNum(pageNum);
//        }
//    }
}
