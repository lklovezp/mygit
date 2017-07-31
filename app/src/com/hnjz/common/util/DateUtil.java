package com.hnjz.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ���ڲ����Ĺ�����
 * 
 * @author wumi
 * @version $Id: DateUtil.java, v 0.1 2013-8-29 ����2:00:52 wumi Exp $
 */
public class DateUtil {

    private static Log   log                = LogFactory.getLog(DateUtil.class);
    public static String ltimePattern       = "yyyy-MM-dd HH:mm:ss";
    public static String defaultDatePattern = "yyyy-MM-dd";
    public static String timePattern        = "HH:mm";

    /**
     * ����yyyy-MM-dd�����ڶ���ת��Ϊ�ַ���
     * 
     * @param aDate  ���ڶ��� {@link Date}
     * @return ����yyyy-MM-dd�����ڶ���ת��Ϊ�ַ���
     */
    public static final String getDate(Date aDate) {
        return getDateTime(defaultDatePattern, aDate);
    }

    /**
     * ����yyyy-MM-dd HH:mm:ss�����ڶ���ת��Ϊ�ַ���
     * 
     * @param aDate  ���ڶ��� {@link Date}
     * @return ����yyyy-MM-dd HH:mm:ss�����ڶ���ת��Ϊ�ַ���
     */
    public static final String getDateTime(Date aDate) {
        return getDateTime(ltimePattern, aDate);
    }

    /**
     * �������ڷ���ʱ��
     * 
     * 
     * @param theTime  ���ڶ��� {@link Date}
     * @return
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * ����aMask�����ڶ���ת��Ϊ�ַ���
     * 
     * @param aMask  ���ڸ�ʽ
     * @param aDate  ���ڶ��� {@link Date}
     * @return �����ڶ���ת��Ϊ�ַ���
     */
    public static final String getDateTime(String aMask, Date aDate) {
        if (aDate == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(aMask);
        return df.format(aDate);
    }

    /**
     * �����ַ������ո����ĸ�ʽת��Ϊyyyy-MM-dd���ڶ���
     * 
     * @param strDate yyyy-MM-dd ���ַ���
     * @return yyyy-MM-dd �����ڶ���
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate) throws ParseException {
        return convertStringToDate(defaultDatePattern, strDate);
    }

    /**
     * �������ַ������ո����ĸ�ʽת��Ϊ���ڶ���
     * 
     * @param aMask �ַ�����ʽ
     * @param strDate �����ַ���
     * @return ���ڶ������ڶ���
     * @throws ParseException
     */
    public static final Date convertStringToDate(String aMask, String strDate)
                                                                              throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(aMask);
        return df.parse(strDate);
    }

    /**
     * ��yyyy-MM-dd ��ʽ�������ַ���תΪ yyyy-MM-dd HH:mm:ss��ʽ������
     * ʱ�䲿�ֲ���Ϊ00:00:00������Ŀ�ʼʱ��
     * 
     * @param date yyyy-MM-dd�������ַ���
     * @return  yyyy-MM-dd HH:mm:ss��ʽ������
     */
    public static Date getStartDatetime(String date) {
        SimpleDateFormat ddf = new SimpleDateFormat(defaultDatePattern);
        Date d = null;
        try {
            d = ddf.parse(date);
        } catch (Exception e) {
            log.error("ת��ʱ�������", e);
        }
        return getZero(d);
    }

    /**
     * ��yyyy-MM-dd ��ʽ�������ַ���תΪ yyyy-MM-dd hh:mm:ss��ʽ������
     * ʱ�䲿�ֲ���ά23:59:59������Ľ���ʱ��
     * 
     * @param date yyyy-MM-dd�������ַ���
     * @return  yyyy-MM-dd hh:mm:ss��ʽ������
     */
    public static Date getEndDatetime(String date) {
        SimpleDateFormat tdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date cd = null;
        try {
            cd = tdf.parse(date.concat(" 23:59:59"));
        } catch (Exception e) {
            log.error("ת�����ڳ�����", e);
        }
        return cd;
    }

    /**
     * �����������õ�ʱ������Ĳ
     * 
     * @return
     */
    public static List<Date[]> getDifference(Date kDate, Date jDate, Date delkDate, Date deljDate) {
        List<Date[]> listDate = new ArrayList<Date[]>();
        Date[] date;
        //��Ҫɾ���Ŀ�ʼʱ�����ԭ��ʼʱ��ʱ
        if (compareDate(delkDate, kDate) == 1) {
            date = new Date[] { kDate, DateUtil.getZero(delkDate, -1) };
            listDate.add(date);
        }
        //��Ҫɾ���Ľ���ʱ��С��ԭ����ʱ��ʱ
        if (compareDate(deljDate, jDate) == -1) {
            date = new Date[] { jDate, DateUtil.getZero(deljDate, 1) };
            listDate.add(date);
        }

        return listDate;
    }

    /**
     * ��������������
     * 
     * @return
     */
    public static Date[][] getTaxis(Date[][] dates, boolean desc) {
        int x = 1;
        if (desc) {
            x = -1;
        }
        // ��dates����
        for (int i = 0; i < dates.length; i++) {
            int k = i;
            for (int j = i + 1; j < dates.length; j++) {
                if (compareDate(dates[k][1], dates[j][1]) == x) {
                    k = j;
                }
            }
            if (k != i) {
                Date tempk = dates[k][0];
                Date tempj = dates[k][1];
                dates[k][0] = dates[i][0];
                dates[k][1] = dates[i][1];
                dates[i][0] = tempk;
                dates[i][1] = tempj;
            }

        }
        return dates;
    }

    /**
     * �����������õ�ʱ�����������
     * 
     * @return
     */
    public static int getDays(Date kDate, Date jDate) {
        return (int) ((jDate.getTime() - kDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * �����������õ�ʱ����������Ľ���
     * 
     * @return ����ʱ�����佻����ûǰ��������null
     */
    public static Date[] getIntersection(Date kDate, Date jDate, Date kDate2, Date jDate2) {
        if (kDate.after(jDate2))//��ʼʱ��1�ڽ���ʱ��2֮����ôû�н���
            return null;
        if (jDate.before(kDate2))//����ʱ��1�ڿ�ʼʱ��2֮����ôû�н���
            return null;

        if (kDate.after(kDate2))//�����ʼʱ��1�ڿ�ʼʱ��2֮��
        {
            if (jDate.before(jDate2))//������ʱ��1ͬ��������ʱ��2�ȽϵĽ��
            {
                return getIntersection(kDate, jDate);
            } else {
                return getIntersection(kDate, jDate2);
            }
        } else {
            if (jDate.before(jDate2))//������ʱ��1ͬ��������ʱ��2�ȽϵĽ��
            {
                return getIntersection(kDate2, jDate);
            } else {
                return getIntersection(kDate2, jDate2);
            }

        }
    }

    /**
     * �����������õ�ʱ����������Ľ���
     * 
     * @return
     */
    private static Date[] getIntersection(Date start, Date end) {
        Date result[] = new Date[2];
        result[0] = start;
        result[1] = end;
        return result;
    }

    /**
     * �����������õ�ʱ����������Ĳ
     * 
     * @return
     */
    public static Date[][] getDifferences(Date[][] dates, Date[][] delDates) {
        List<Date[]> listDate = new ArrayList<Date[]>();
        Date[][] outDate;
        delDates = getTaxis(delDates, false);// ��delDates����

        for (int j = 0; j < dates.length; j++) {
            for (int i = 0; i < delDates.length; i++) {

                // ��Ҫɾ���������ԭ�����н�����ʱ��
                if ((compareDate(delDates[i][1], dates[j][0]) >= 0 && compareDate(delDates[i][1],
                    dates[j][1]) <= 0)
                    || (compareDate(delDates[i][0], dates[j][0]) >= 0 && compareDate(
                        delDates[i][0], dates[j][1]) <= 0)) {
                    listDate.addAll(getDifference(dates[i][0], dates[i][1], delDates[i][0],
                        delDates[i][1]));

                }
                //��ԭʱ��Ľ���ʱ��С��ɾ��ʱ��Ľ���ʱ��ʱ
                if (compareDate(dates[j][1], delDates[i][1]) <= 0) {
                    break;
                }
            }
        }
        outDate = new Date[listDate.size()][2];
        int n = 0;
        for (Date[] date : listDate) {
            outDate[n][0] = date[0];
            outDate[n][1] = date[1];
            n++;
        }
        return outDate;
    }

    /**
     * �����������Ƚ�����
     *  
     * @param date1  ����1
     * @param date2  ����2
     * @return  ��date1��ʱ���� 1 �����ʱ����0  ����Ϊ-1
     */
    public static int compareDate(Date date1, Date date2) {
        if (date1 == null && date2 != null) {
            return -1;
        } else if (date1 != null && date2 == null) {
            return 1;
        } else if (date1 == null && date2 == null) {
            return 0;
        }
        int outInt = 0;
        if (date1.after(date2)) {
            outInt = 1;
        }
        if (date1.before(date2)) {
            outInt = -1;
        }
        return outInt;
    }

    /**
     * �������������������ڵ�ʱ��������
     * @param date	ָ��������
     * @return
     */
    public static Date getZero(Date date) {
        return getZero(date, 0);
    }

    /**
     * ��������������������������N������ʱ��������
     * 
     * @param date ָ������
     * @param days	����������
     * @return
     */
    public static Date getZero(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);//����+n
        calendar.set(Calendar.HOUR_OF_DAY, 0);//Сʱ��0
        calendar.set(Calendar.MINUTE, 0);//������0
        calendar.set(Calendar.SECOND, 0);//����0
        calendar.set(Calendar.MILLISECOND, 0);//������0
        Date newDate = calendar.getTime();//�õ��µ�date����.
        return newDate;
    }

    /**
     * ��������������������������N������ʱ��������
     * 
     * @param date ָ������
     * @param days	����������
     * @return
     */
    public static Date getZero(Date date, int years, int months, int days) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);//����+n
        calendar.add(Calendar.MONTH, months);//����+n
        calendar.add(Calendar.DATE, days);//����+n
        calendar.set(Calendar.HOUR_OF_DAY, 0);//Сʱ��0
        calendar.set(Calendar.MINUTE, 0);//������0
        calendar.set(Calendar.SECOND, 0);//����0
        calendar.set(Calendar.MILLISECOND, 0);//������0
        Date newDate = calendar.getTime();//�õ��µ�date����.
        return newDate;
    }

    /**
     * ������+frequency��
     * @param date YYYY-mm-DD ��ʽ���ַ���
     * @param frequency ����
     * @return
     */
    public static String getNextDate(String date, int frequency) {
        SimpleDateFormat ddf = new SimpleDateFormat(defaultDatePattern);
        Date d = null;
        try {
            d = ddf.parse(date);
        } catch (Exception e) {
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DAY_OF_MONTH, frequency);
        return ddf.format(c.getTime());
    }

    /**
     * ����ĳ�����ں�Ƶ�εõ��´�ִ������
     * �ϴδ�������ΪNULLʱ��������������
     * @param lastTime
     * @param frequency
     * @return
     */
    public static Date getNextTime(Date lastTime, Integer frequency) {
        Calendar lastCal = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int eachDay = maxDay / frequency;
        if (lastTime != null) {//��������ִ��ʱ�䣬���ô�ʱ��Ϊ���,������ӳ�eachDay����
            lastCal.setTime(lastTime);
            lastCal.add(Calendar.DAY_OF_MONTH, eachDay);
        } else {//���صڶ���
            lastCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        if (lastCal.get(Calendar.MONTH) != c.get(Calendar.MONTH)) {
            lastCal.set(Calendar.DATE, 1);
        }
        return lastCal.getTime();
    }

    /**
     * ��õ�ǰ���ȣ����֣�
     * @return
     */
    public static int getCurQuarter() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        int month = calendar.get(Calendar.MONTH) + 1; //��õ�ǰ�·�
        int quarter = 0;
        if (month >= 1 && month <= 3) {
            quarter = 1;
        }
        if (month >= 4 && month <= 6) {
            quarter = 2;
        }
        if (month >= 7 && month <= 9) {
            quarter = 3;
        }
        if (month >= 10 && month <= 12) {
            quarter = 4;
        }
        return quarter;
    }

    /**
     * 
     * �������ܣ���ȡ��������ʱ���
    
     * ���������
    
     * ����ֵ��
     */
	public static String getTwoDateMinue(String starttime, String endtime) {
		String dateStr = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = df.parse(starttime);
			Date now = df.parse(endtime);
			long l = now.getTime() - date.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			if(day>0){
				dateStr += day+"��";
			}
			if(hour>0){
				dateStr += hour + "Сʱ";
			}
			if(min>0){
				dateStr += min + "����";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return StringUtils.isBlank(dateStr)?"0����":dateStr;
	}
	
	/**
	 * ��ȡ����1��
	 * @param date
	 * @return
	 */
	public static Date getNextMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
		calendar.add(Calendar.MONTH, 1);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
	}
	
	/**
	 * ��ȡĳ���µ�����
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDays(int year, int month){
		int days = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}
	
	public static void main(String[] args){
		System.out.println(getTwoDateMinue("2015-05-05 14:50","2016-06-05 17:04"));
	}
	
	/**
	 * ��ȡ�ϸ��µ����� 
	 * <br>��2015-03-03��������2015-02-03
	 * @param date ��ʽΪ2015-03-03������
	 * @return
	 */
	public static String getPreDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String d = sdf.format(date);
		String[] arr = d.split("-");
		int year = Integer.parseInt(arr[0]); //��ȡ��ǰ���ڵ����
		int month = Integer.parseInt(arr[1]); //��ȡ��ǰ���ڵ��·�
		int day = Integer.parseInt(arr[2]); //��ȡ��ǰ���ڵ���
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		int year2 = year;
		int month2 = month - 1;
		if (month2 == 0) {
			year2 = year2 - 1;
			month2 = 12;
		}
		int day2 = day;
		
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.YEAR, year2);
		c2.add(Calendar.MONTH, month2);
		int days2 = c2.get(Calendar.DAY_OF_MONTH);
		if (day2 > days2) {
			day2 = days2;
		}
		String m2 = "";
		if (month2 < 10) {
			m2 = '0' + String.valueOf(month2);
		}
		String t2 = String.valueOf(year2) + '-' + m2 + '-' + String.valueOf(day2);
		return t2;
	}
}