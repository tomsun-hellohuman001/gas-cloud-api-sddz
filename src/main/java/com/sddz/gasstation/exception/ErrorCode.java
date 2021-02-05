package com.sddz.gasstation.exception;

public enum ErrorCode {
    SYSTEM_ERROR(-1, "系统异常"),
    PARAMETER_ERROR(-2, "参数异常"),
    WX_LOGIN_ERROR(-1000, "微信登陆异常"),
    WX_USER_NOT_LOGIN(-1001, "用户没有登陆"),
    BIND_CAR_NUMBER_EXCEED_ERROR(-1002, "最多绑定十张车牌"),
    USER_ALREADY_SIGNED(-1003, "用户已经签约"),
    INVALID_CONTRACT_CODE(-1004, "无效的签约协议号"),
    BREAK_CONTRACT_ERROR(-1005, "当前未签约，无法解约"),
    BREAK_CONTRACT_FAIL(-1006, "解约失败"),
    CAR_NUMBER_ALREADY_EXIST(-1007, "车牌号已被绑定"),
    GUN_ALREADY_BIND(-1008, "此油枪已被其他车牌关联"),
    ORDER_NOT_BIND_USER(-1009, "订单没有绑定用户"),
    USER_NOT_CONTRACT(-1010, "用户未签约，不能代扣"),
    ORDER_ALREADY_PAID(-1011, "订单正在被支付或已经支付"),
    WX_PAY_ERROR(-1012, "微信支付异常"),
    ORDER_STATUS_ERROR(-1013, "订单已支付"),
    WX_UPLOAD_MEDIA_ERROR(-1014, "微信上传图片失败"),
    WX_SUBMIT_APPLY_ERROR(-1015, "微信提交申请失败"),
    WX_PAY_PARTNER_ALREADY_EXIST(-1016, "微信支付合作伙伴已经存在"),
    WX_PAY_PARTNER_ALREADY_BIND(-1017, "合作伙伴已经被绑定"),
    POINTS_NOT_ENOUGH(-1018, "积分不足"),
    WX_CAR_PLATFORM_QUERY_STATE_ERROR(-1019, "车主平台查询用户状态失败"),
    WX_CAR_PLATFORM_APPLY_PAY_ERROR(-1020, "车主平台申请扣款失败"),
    IMPORT_DATA_IS_EMPTY(-1021, "导入数据为空"),
    OIL_COMPANY_IS_NULL(-1022, "油企不存在"),
    GAS_TYPE_NOT_EXIST(-1023, "油号不存在"),
    VERIFICATION_CODE_ERROR(-1024, "验证码错误"),
    DEVICE_ALREADY_EXISTS(-1025, "设备号已存在"),
    DEVICE_HAVE_RELATED_DEVICE(-1026, "设备不能删除，请先解除和其他设备的绑定"),
    USER_GROUP_NOT_EXIST(-1027, "用户组不存在"),
    OIL_PRODUCT_NOT_EXIST(-1028, "油品不存在"),
    INTEGRAL_EXCHANGE_PRODUCT_NOT_EXIST(-1029, "兑换商品不存在"),
    COUPON_INVALID(-1030, "优惠券无效"),
    OIL_SALE_TIME_EXIST(-1031, "该方案中油品已存在于其他相同时间的方案"),
    DISCOUNT_TIME_EXIST(-1032, "满减活动中油品已在其他活动时间存在"),
    ADMIN_USER_EXIST(-1033, "用户名已存在"),
    ADMIN_ROLE_EXIST(-1034, "角色已存在"),
    RESOURCE_URL_EXIST(-1035, "资源url已经存在"),
    ADMIN_USERNAME_INVALID(-1036, "没有此用户"),
    ADMIN_PASSWORD_INVALID(-1037, "密码错误"),

    INTEGRAL_EXCHANGE_PRODUCT_OFF(-2029, "兑换商品已经下架"),
    OVER_AVAILABLE_STOCK(-2030,"超出库存余量，无法兑换"),
    SAVE_EX(-2000,"插入数据异常"),
    FIND_EX(-2001,"查找数据异常"),
    INVOICE_EX(-2003,"存在不符合开发票情况的订单，请检查您选中的订单"),
    SHIFT_EX(-2004,"交接班过于频繁"),

    ADMIN_USER_NOT_LOGIN(-1038, "用户没有登陆"),
    USER_LOCATION_IS_TOO_FAR(-1039, "附近没有加油站"),
    USER_WX_NOT_LOGIN(-1040, "无法获取用户openid"),
    GUN_NO_ERROR(-1041, "请选择正确的油枪编号"),
    COMPANY_CANNOT_DELELTE_FOR_STATION(-1042, "油企不能删除，有关联的油站"),
    COMPANY_CANNOT_DELELTE_FOR_GOODS(-1043, "油企不能删除，有关联的油品"),
    STATION_CANNOT_DELETE(-1044, "油站不能删除，有关联的数据"),
    ORDER_NOT_EXIST(-1045, "订单不存在"),
    PHONE_ALREADY_EXIST(-1046, "手机号已经存在"),
    PHONE_EX(-10461, "手机号有误"),
    ALIPAY_PHONE_ERROR(-1047, "未能获取手机号，登录失败"),
    PLATE_NUMBER_BIND_ONLINE(-1048, "车牌号已被微信/支付宝绑定"),
    PERMISSION_DENY(-1049, "没有相应油站的数据权限，无法查看"),
    PHONE_IS_NOT_OFFLINE(-1050, "手机号不是线下会员"),
    NO_AVAILABLE_MERCHANT(-1051, "没有可用收款商户"),
    REFUND_AMOUNT_INVALID(-1052, "退款金额无效"),
    USER_INCOMMING_FAIL(-1053, "进件失败"),
    WITHDRAW_FAIL(-1054, "提现失败，无可用余额"),
    GET_BANK_ACCOUNT_FAIL(-1055, "无法获取银行卡号信息"),
    BANK_ACCOUNT_EXIST(-1056, "已登记过该卡"),
    USER_NOT_REGISTER(-1057, "用户未入驻"),

    WX_USER_ALREADY_EXISTS(-1051,"用户信息已存在"),
    WX_USER_PHONE_NOT_ISNULL(-1052,"手机号不能为空"),
    SINGLE_LIMIT(-1060,"单笔交易超过限定金额"),
    DEDUCTION_FREQUENCY(-1061,"单日交易量超过上限"),
    ASSOCIATION_EXIST(-1062,"关联已存在，请勿重复添加"),
    NO_GAS_SALE_RULE(-1063, "没有对应的油品上架方案，无法获取单价"),

    ORDER_LOCKED(-1064, "订单已被锁定"),
    POINTS_BE_FROZEN(-1065,"积分被冻结"),
    RECEIVER_POINTS_BE_FROZEN(-1066,"对方积分被冻结"),
    POINTS_GOODS_EXIST(-1067,"积分商品已存在"),
    POINTS_GOODS_ON_SALE(-1068,"积分商品已被上架，请先下架商品"),
    ARREARAGE_ACCOUNT_EXIST(-1069,"欠款账户已存在"),
    BIND_CARD_LIMIT_ERROR(-1070,"最多十张可以绑定电子卡"),
    BIND_CARD_EXTIST_ERROR(-1071,"加油卡已经被绑定，不可以重复绑定"),
    BIND_CARD_PASSWORD_ERROR(-1072,"加油卡密码错误"),
    BIND_CARD_PAY_ERROR(-1074,"加油卡支付失败"),
    BIND_CARD_OPEN_ERROR(-1073,"电子卡开通出现异常，请联系管理员"),
    BIND_CARD_OPEN_LMINT(-1074,"加油卡已被其他用户绑定并达到绑卡上限，请联系管理员"),
    LOGIN_NAME_OR_PWD_ERROR(-2020,"用户名或密码错误"),
    LOGIN_NAME_EXIST(-2222,"设备已存在登录用户"),
    LOGIN_NAME_OR_PWD_EXIST(-2220,"用户已在其他设备登录"),
    LOGIN_INCONSISTENT_LOGIN_NAME(-2221,"用户名不一致"),
    PHONE_NOT_MEMBER(-1021, "手机号非会员"),
    QRCODE_ERROR(-2021, "获取支付二维码失败"),

    SPLIT_AMOUNT_INVALID(-2022, "拆单金额不合法"),
    SUPPLY_AMOUNT_INVALID(-2023, "此订单不需要补款"),
    SPLIT_ORDER_EXIST(-2024, "此订单已拆单"),
    SUPPLY_ORDER_EXIST(-2025, "此订单已补单"),

    UN_REPLY(-2026,"未回复用户投诉，不能删除"),
    OPEN_LOCATION(-2027, "无法获取当前加油站，请联系加油员"),
    NOT_ARREARAGE_CAR(-2028,"未隶属于任何可欠款单位,请选择其他支付方式"),
    NOT_RECEIPT_USER(-2029,"非累计开票用户"),

    ORDER_PAY_LOCKED(-2030, "订单已被锁定"),
    REPEAT_TOP_ACTIVITY(-2031, "已存在相同油品时间段内的置顶活动，不允许置顶"),
    NO_POS_MERCHANT(-2032, "没有配置pos商户，无法支付"),
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
