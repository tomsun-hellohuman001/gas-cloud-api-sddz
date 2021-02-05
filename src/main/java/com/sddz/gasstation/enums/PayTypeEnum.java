package com.sddz.gasstation.enums;

import com.google.common.collect.Lists;
import com.sddz.gasstation.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum PayTypeEnum {

    WX_WITHOUT_PWD_PAY(1, "微信无感支付"),
    WX_JSAPI(2, "微信小程序支付"),
    WX_NATIVE(3, "微信正扫"),
    WX_PAY_CODE(4, "微信反扫"),
    ALIPAY_H5(5, "支付宝h5支付"),
    ALIPAY_MP(6, "支付宝小程序支付"),
    UNION_AUTO_PAY(7, "银联无感支付"),
    ALIPAY_NATIVE(8, "支付宝正扫"),
    ALIPAY_PAY_CODE(9, "支付宝反扫"),
    POS(10, "pos机支付"),
    CASH(11, "现金支付"),
    CARD(12, "加油卡支付"),
    ARREARAGE(13,"欠款"),
    Recharge(14,"回罐"),
    Own_Oil(15,"自用油"),
    Accident_Write_Off(16,"事故核销"),

    BANK_CARD(17, "刷卡"),
    RECHARGE_CARD(18, "充值卡"),
    VIP_CARD(19, "电子会员卡"),
    WX_H5(20, "微信公众号支付"),

    ;

    private int value;
    private String desc;

    /**
     * 是否微信支付
     * @param payType
     * @return
     */
    public static boolean isWxPay(Integer payType) {
        Optional<PayTypeEnum> any = Lists.newArrayList(WX_WITHOUT_PWD_PAY, WX_JSAPI, WX_NATIVE, WX_H5, WX_PAY_CODE).stream().filter(payTypeEnum -> payTypeEnum.getValue() == payType).findAny();
        return any.isPresent();
    }

    /**
     * 是否支付宝支付
     * @param payType
     * @return
     */
    public static boolean isAlipayPay(Integer payType){
        Optional<PayTypeEnum> any = Lists.newArrayList(ALIPAY_H5, ALIPAY_MP, ALIPAY_NATIVE, ALIPAY_PAY_CODE).stream().filter(payTypeEnum -> payTypeEnum.getValue() == payType).findAny();
        return any.isPresent();
    }

    /**
     * 获取线上支付方式
     * @return
     */
    public static List<Integer> getOnlinePayTypes() {
        return Lists.newArrayList(WX_WITHOUT_PWD_PAY, WX_JSAPI, WX_NATIVE, WX_PAY_CODE, ALIPAY_H5, ALIPAY_MP, UNION_AUTO_PAY, ALIPAY_NATIVE, ALIPAY_PAY_CODE, WX_H5).stream().map(PayTypeEnum::getValue).collect(Collectors.toList());
    }

    /**
     * 获取微信支付方式
     * @return
     */
    public static List<Integer> getWxPayTypes() {
        return Lists.newArrayList(WX_WITHOUT_PWD_PAY, WX_JSAPI, WX_NATIVE, WX_PAY_CODE, WX_H5).stream().map(PayTypeEnum::getValue).collect(Collectors.toList());
    }

    /**
     * 获取支付宝支付方式
     * @return
     */
    public static List<Integer> getAlipayPayTypes() {
        return Lists.newArrayList(ALIPAY_H5, ALIPAY_MP, ALIPAY_NATIVE, ALIPAY_PAY_CODE).stream().map(PayTypeEnum::getValue).collect(Collectors.toList());
    }

    /**
     * 获取线下支付方式
     * @return
     */
    public static List<Integer> getOfflinePayTypes() {
        return Lists.newArrayList(POS, CASH).stream().map(PayTypeEnum::getValue).collect(Collectors.toList());
    }

    /**
     * 根据value获取desc
     * @param payType
     * @return
     */
    public static String getDescById(Integer payType){
        if (payType == null) {
            return "未知";
        }
        Optional<String> first = Arrays.stream(PayTypeEnum.values()).filter(payTypeEnum -> payTypeEnum.getValue() == payType).map(payTypeEnum -> payTypeEnum.getDesc()).findFirst();
        return first.orElse("");
    }

    public static Integer getIdByDesc(String payType) {
        if (StringUtil.isEmpty(payType)) {
            return null;
        }
        Optional<Integer> first = Arrays.stream(PayTypeEnum.values()).filter(payTypeEnum -> payTypeEnum.getDesc().equals(payType)).map(payTypeEnum -> payTypeEnum.getValue()).findFirst();
        return first.orElse(null);
    }

    public static void main(String[] args) {
        System.out.println(String.join(",", getOnlinePayTypes().stream().map(integer -> integer.toString()).collect(Collectors.toList())));
    }
}
