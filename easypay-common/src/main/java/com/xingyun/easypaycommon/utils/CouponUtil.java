package com.xingyun.easypaycommon.utils;

import cn.hutool.core.util.RandomUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CouponUtil {

    public static List<BigDecimal> randomCouponAmount(BigDecimal moneySum, int redNum) {

        Set<String> couponAmountSet = new HashSet<>(redNum);
        List<BigDecimal> amountList =  new ArrayList<>(redNum);


        while (couponAmountSet.size() < redNum){
            couponAmountSet.clear();
            amountList.clear();

            BigDecimal min = new BigDecimal("0.01"); // 最小金额
            BigDecimal remain =  moneySum;

            for (int i = 1; i < redNum; i++) {
                // 随机生成Min到Max的随机数并规范到2位小数，是String类
                BigDecimal max =  remain.divide(new BigDecimal(redNum - i + 1),2, RoundingMode.HALF_DOWN).multiply(new BigDecimal(2.00));

                BigDecimal amount = RandomUtil.randomBigDecimal(min, max).setScale(2,RoundingMode.HALF_DOWN);

                while (couponAmountSet.contains(amount.toString())){
                    amount = RandomUtil.randomBigDecimal(min, max).setScale(2,RoundingMode.HALF_DOWN);
                }
                couponAmountSet.add(amount.toString());
                amountList.add(amount);
                remain = remain.subtract(amount);
            }
            couponAmountSet.add(remain.toString());
            amountList.add(remain);
        }
        return amountList;

    }
}
