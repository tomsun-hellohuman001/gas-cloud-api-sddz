package com.sddz.gasstation.param;

import com.ycy.common.web.QueryParam;
import lombok.Data;

/**
 * <Description> 油品 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class OilProductQueryParam extends QueryParam {

    private Long gasTypeId;

    private Long oilCompanyId;

    private Long stationId;

}
