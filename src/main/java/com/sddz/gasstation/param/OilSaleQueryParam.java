package com.sddz.gasstation.param;

import com.ycy.common.web.QueryParam;
import lombok.Data;

/**
 * <Description> 油品上架 <br>
 *
 * @author zhaopengwei<br>
 */
@Data
public class OilSaleQueryParam extends QueryParam {

    private Long oilCompanyId;

    private Long gasStationId;

    private Long oilProductId;
}
