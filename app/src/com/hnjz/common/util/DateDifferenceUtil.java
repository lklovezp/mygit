package com.hnjz.common.util;

import java.util.Calendar;
import java.util.Date;

public class DateDifferenceUtil {
    /**
     * �����������ڼ��ʱ���
     * @param dataOne
     * @param dataTwo
     * @return ��
     */
    public static Long getTimeDifferenceValue(Date dataOne, Date dataTwo) {
        Calendar calendarOne = Calendar.getInstance();
        Calendar calendarTwo = Calendar.getInstance();
        calendarOne.setTime(dataOne);
        calendarTwo.setTime(dataTwo);
        return Math.abs(calendarOne.getTimeInMillis() - calendarTwo.getTimeInMillis()) / 1000;
    }

    /**
     * ��"��"ת��Ϊ��Ҫ����ʾʱ��
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
        if (m > 0 && m < 60) {//��
            strBuffer.append(m).append("����");
        } else if (m >= 60) {//����1Сʱ
            Long h = m / 60;
            Long hm = m % 60;
            if (h >= 1 && h < 24) {//Сʱ
                strBuffer.append(h).append("Сʱ");
            } else {//��
                Long d = h / 24;
                Long dh = h % 24;
                strBuffer.append(d).append("��");
                if (dh > 0) {
                    strBuffer.append(dh).append("Сʱ");
                }
            }
            if (hm > 0) {
                strBuffer.append(hm).append("����");
            }
        }
        if (mmod > 0) {
            strBuffer.append(mmod).append("��");
        }
        return strBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(DateDifferenceUtil.getTimeConvert(23000l));
    }
}