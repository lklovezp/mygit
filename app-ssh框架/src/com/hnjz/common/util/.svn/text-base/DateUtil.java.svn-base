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
 * 日期操作的工具类
 * 
 * @author wumi
 * @version $Id: DateUtil.java, v 0.1 2013-8-29 下午2:00:52 wumi Exp $
 */
public class DateUtil {

    private static Log   log                = LogFactory.getLog(DateUtil.class);
    public static String ltimePattern       = "yyyy-MM-dd HH:mm:ss";
    public static String defaultDatePattern = "yyyy-MM-dd";
    public static String timePattern        = "HH:mm";

    /**
     * 按照yyyy-MM-dd将日期对象转化为字符串
     * 
     * @param aDate  日期对象 {@link Date}
     * @return 按照yyyy-MM-dd将日期对象转化为字符串
     */
    public static final String getDate(Date aDate) {
        return getDateTime(defaultDatePattern, aDate);
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss将日期对象转化为字符串
     * 
     * @param aDate  日期对象 {@link Date}
     * @return 按照yyyy-MM-dd HH:mm:ss将日期对象转化为字符串
     */
    public static final String getDateTime(Date aDate) {
        return getDateTime(ltimePattern, aDate);
    }

    /**
     * 根据日期返回时间
     * 
     * 
     * @param theTime  日期对象 {@link Date}
     * @return
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * 按照aMask将日期对象转化为字符串
     * 
     * @param aMask  日期格式
     * @param aDate  日期对象 {@link Date}
     * @return 将日期对象转化为字符串
     */
    public static final String getDateTime(String aMask, Date aDate) {
        if (aDate == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(aMask);
        return df.format(aDate);
    }

    /**
     * 日期字符串按照给定的格式转换为yyyy-MM-dd日期对象
     * 
     * @param strDate yyyy-MM-dd 的字符串
     * @return yyyy-MM-dd 的日期对象
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate) throws ParseException {
        return convertStringToDate(defaultDatePattern, strDate);
    }

    /**
     * 将日期字符串按照给定的格式转换为日期对象
     * 
     * @param aMask 字符串格式
     * @param strDate 日期字符串
     * @return 日期对象日期对象
     * @throws ParseException
     */
    public static final Date convertStringToDate(String aMask, String strDate)
                                                                              throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(aMask);
        return df.parse(strDate);
    }

    /**
     * 将yyyy-MM-dd 格式的日期字符串转为 yyyy-MM-dd HH:mm:ss格式的日期
     * 时间部分补充为00:00:00，当天的开始时间
     * 
     * @param date yyyy-MM-dd的日期字符串
     * @return  yyyy-MM-dd HH:mm:ss格式的日期
     */
    public static Date getStartDatetime(String date) {
        SimpleDateFormat ddf = new SimpleDateFormat(defaultDatePattern);
        Date d = null;
        try {
            d = ddf.parse(date);
        } catch (Exception e) {
            log.error("转换时间出错：", e);
        }
        return getZero(d);
    }

    /**
     * 将yyyy-MM-dd 格式的日期字符串转为 yyyy-MM-dd hh:mm:ss格式的日期
     * 时间部分补充维23:59:59，当天的结束时间
     * 
     * @param date yyyy-MM-dd的日期字符串
     * @return  yyyy-MM-dd hh:mm:ss格式的日期
     */
    public static Date getEndDatetime(String date) {
        SimpleDateFormat tdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date cd = null;
        try {
            cd = tdf.parse(date.concat(" 23:59:59"));
        } catch (Exception e) {
            log.error("转换日期出错：", e);
        }
        return cd;
    }

    /**
     * 函数描述：得到时间区间的差集
     * 
     * @return
     */
    public static List<Date[]> getDifference(Date kDate, Date jDate, Date delkDate, Date deljDate) {
        List<Date[]> listDate = new ArrayList<Date[]>();
        Date[] date;
        //当要删除的开始时间大于原开始时间时
        if (compareDate(delkDate, kDate) == 1) {
            date = new Date[] { kDate, DateUtil.getZero(delkDate, -1) };
            listDate.add(date);
        }
        //当要删除的结束时间小于原结束时间时
        if (compareDate(deljDate, jDate) == -1) {
            date = new Date[] { jDate, DateUtil.getZero(deljDate, 1) };
            listDate.add(date);
        }

        return listDate;
    }

    /**
     * 函数描述：排序
     * 
     * @return
     */
    public static Date[][] getTaxis(Date[][] dates, boolean desc) {
        int x = 1;
        if (desc) {
            x = -1;
        }
        // 对dates排序
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
     * 函数描述：得到时间区间的天数
     * 
     * @return
     */
    public static int getDays(Date kDate, Date jDate) {
        return (int) ((jDate.getTime() - kDate.getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 函数描述：得到时间区间数组的交集
     * 
     * @return 返回时间区间交集，没前交集返回null
     */
    public static Date[] getIntersection(Date kDate, Date jDate, Date kDate2, Date jDate2) {
        if (kDate.after(jDate2))//开始时间1在结束时间2之后，那么没有交集
            return null;
        if (jDate.before(kDate2))//结束时间1在开始时间2之后，那么没有交集
            return null;

        if (kDate.after(kDate2))//如果开始时间1在开始时间2之后，
        {
            if (jDate.before(jDate2))//检查结束时间1同结束结束时间2比较的结果
            {
                return getIntersection(kDate, jDate);
            } else {
                return getIntersection(kDate, jDate2);
            }
        } else {
            if (jDate.before(jDate2))//检查结束时间1同结束结束时间2比较的结果
            {
                return getIntersection(kDate2, jDate);
            } else {
                return getIntersection(kDate2, jDate2);
            }

        }
    }

    /**
     * 函数描述：得到时间区间数组的交集
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
     * 函数描述：得到时间区间数组的差集
     * 
     * @return
     */
    public static Date[][] getDifferences(Date[][] dates, Date[][] delDates) {
        List<Date[]> listDate = new ArrayList<Date[]>();
        Date[][] outDate;
        delDates = getTaxis(delDates, false);// 对delDates排序

        for (int j = 0; j < dates.length; j++) {
            for (int i = 0; i < delDates.length; i++) {

                // 当要删除的区间和原区间有交集的时候
                if ((compareDate(delDates[i][1], dates[j][0]) >= 0 && compareDate(delDates[i][1],
                    dates[j][1]) <= 0)
                    || (compareDate(delDates[i][0], dates[j][0]) >= 0 && compareDate(
                        delDates[i][0], dates[j][1]) <= 0)) {
                    listDate.addAll(getDifference(dates[i][0], dates[i][1], delDates[i][0],
                        delDates[i][1]));

                }
                //当原时间的结束时间小于删除时间的结束时间时
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
     * 函数描述：比较日期
     *  
     * @param date1  日期1
     * @param date2  日期2
     * @return  当date1大时返回 1 当相等时返回0  其他为-1
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
     * 函数描述：将给定日期的时分秒清零
     * @param date	指定的日期
     * @return
     */
    public static Date getZero(Date date) {
        return getZero(date, 0);
    }

    /**
     * 函数描述：将给定日期增减（N）天后的时分秒清零
     * 
     * @param date 指定日期
     * @param days	增减的天数
     * @return
     */
    public static Date getZero(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);//天数+n
        calendar.set(Calendar.HOUR_OF_DAY, 0);//小时置0
        calendar.set(Calendar.MINUTE, 0);//分钟置0
        calendar.set(Calendar.SECOND, 0);//秒置0
        calendar.set(Calendar.MILLISECOND, 0);//毫秒置0
        Date newDate = calendar.getTime();//得到新的date对象.
        return newDate;
    }

    /**
     * 函数描述：将给定日期增减（N）天后的时分秒清零
     * 
     * @param date 指定日期
     * @param days	增减的天数
     * @return
     */
    public static Date getZero(Date date, int years, int months, int days) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, years);//年数+n
        calendar.add(Calendar.MONTH, months);//月数+n
        calendar.add(Calendar.DATE, days);//天数+n
        calendar.set(Calendar.HOUR_OF_DAY, 0);//小时置0
        calendar.set(Calendar.MINUTE, 0);//分钟置0
        calendar.set(Calendar.SECOND, 0);//秒置0
        calendar.set(Calendar.MILLISECOND, 0);//毫秒置0
        Date newDate = calendar.getTime();//得到新的date对象.
        return newDate;
    }

    /**
     * 将日期+frequency天
     * @param date YYYY-mm-DD 格式的字符串
     * @param frequency 天数
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
     * 根据某个日期和频次得到下次执行日期
     * 上次创建日期为NULL时，返回明天日期
     * @param lastTime
     * @param frequency
     * @return
     */
    public static Date getNextTime(Date lastTime, Integer frequency) {
        Calendar lastCal = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int eachDay = maxDay / frequency;
        if (lastTime != null) {//如果有最后执行时间，设置此时间为起点,并向后延迟eachDay多天
            lastCal.setTime(lastTime);
            lastCal.add(Calendar.DAY_OF_MONTH, eachDay);
        } else {//返回第二天
            lastCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        if (lastCal.get(Calendar.MONTH) != c.get(Calendar.MONTH)) {
            lastCal.set(Calendar.DATE, 1);
        }
        return lastCal.getTime();
    }

    /**
     * 获得当前季度（数字）
     * @return
     */
    public static int getCurQuarter() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        int month = calendar.get(Calendar.MONTH) + 1; //获得当前月份
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
     * 函数介绍：获取两个日期时间差
    
     * 输入参数：
    
     * 返回值：
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
				dateStr += day+"天";
			}
			if(hour>0){
				dateStr += hour + "小时";
			}
			if(min>0){
				dateStr += min + "分钟";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return StringUtils.isBlank(dateStr)?"0分钟":dateStr;
	}
	
	/**
	 * 获取下月1号
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
	 * 获取某年月的天数
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
	 * 获取上个月的日期 
	 * <br>如2015-03-03的上月是2015-02-03
	 * @param date 格式为2015-03-03的日期
	 * @return
	 */
	public static String getPreDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String d = sdf.format(date);
		String[] arr = d.split("-");
		int year = Integer.parseInt(arr[0]); //获取当前日期的年份
		int month = Integer.parseInt(arr[1]); //获取当前日期的月份
		int day = Integer.parseInt(arr[2]); //获取当前日期的日
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
