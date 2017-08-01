package com.hnjz.common.util;

import java.util.Calendar;
import java.util.Date;

public class DateDifferenceUtil {
    /**
     * 计算两个日期间的时间差
     * @param dataOne
     * @param dataTwo
     * @return 秒
     */
    public static Long getTimeDifferenceValue(Date dataOne, Date dataTwo) {
        Calendar calendarOne = Calendar.getInstance();
        Calendar calendarTwo = Calendar.getInstance();
        calendarOne.setTime(dataOne);
        calendarTwo.setTime(dataTwo);
        return Math.abs(calendarOne.getTimeInMillis() - calendarTwo.getTimeInMillis()) / 1000;
    }

    /**
     * 将"秒"转换为需要的显示时间
     * @param s
     * @return
     */
    public static String getTimeConvert(Long s) {
        if(null == s){
            return "";
        }
        Long m = s / 60;
        Long mmod = s % 60;
        StringBuffer strBuffer = new StringBuffer();
        if (m > 0 && m < 60) {//分
            strBuffer.append(m).append("分钟");
        } else if (m >= 60) {//大于1小时
            Long h = m / 60;
            Long hm = m % 60;
            if (h >= 1 && h < 24) {//小时
                strBuffer.append(h).append("小时");
            } else {//天
                Long d = h / 24;
                Long dh = h % 24;
                strBuffer.append(d).append("天");
                if (dh > 0) {
                    strBuffer.append(dh).append("小时");
                }
            }
            if (hm > 0) {
                strBuffer.append(hm).append("分钟");
            }
        }
        if (mmod > 0) {
            strBuffer.append(mmod).append("秒");
        }
        return strBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(DateDifferenceUtil.getTimeConvert(23000l));
    }
}
