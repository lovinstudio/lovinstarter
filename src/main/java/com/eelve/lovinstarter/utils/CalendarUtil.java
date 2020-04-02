package com.eelve.lovinstarter.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @ClassName CalendarUtil
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/7/22 14:24
 * @Version 1.0
 **/
public class CalendarUtil {

    private static final String DATE_WITH_SPLIT = "yyyy-MM-dd";

    private static final String DATE_WITHNOT_SPLIT = "yyyyMMdd";

    private static final String DATETIME_WITH_SPLIT = "yyyy-MM-dd HH:mm:ss";

    private static final String DATETIME_WITHNOT_SPLIT = "yyyyMMddHHmmss";

    private static final Calendar calendar = Calendar.getInstance();

    /**
     * 将日期转换成字符串
     *
     * @param dateTime 日期
     * @param withSplit true返回yyyy-MM-dd HH:mm:ss||false返回yyyyMMddHHmmss
     * @return 日期字符串
     */
    public static String convertDateTimeToString(Date dateTime, boolean withSplit) {
        SimpleDateFormat dateFormat = null;
        if (withSplit) {
            dateFormat = new SimpleDateFormat(DATETIME_WITH_SPLIT);
        } else {
            dateFormat = new SimpleDateFormat(DATETIME_WITHNOT_SPLIT);
        }
        return dateFormat.format(dateTime);
    }

    /**
     * 将日期转换成字符串
     *
     * @param date 日期
     * @param withSplit true返回yyyy-MM-dd||false返回yyyyMMdd
     * @return 日期字符串
     */
    public static String convertDateToString(Date date, boolean withSplit) {
        SimpleDateFormat dateFormat = null;
        if (withSplit) {
            dateFormat = new SimpleDateFormat(DATE_WITH_SPLIT);
        } else {
            dateFormat = new SimpleDateFormat(DATE_WITHNOT_SPLIT);
        }
        return dateFormat.format(date);
    }

    /**
     * 把格式为(yyyy-MM-dd或yyyyMMdd)的字符串转换为Date型(不带时分秒)
     *
     * @param date 字符串日期
     * @return Date型日期(失败返回null)
     */
    public static Date convertStringToDate(String date) {
        int length = date.length();
        SimpleDateFormat dateFormat = null;
        if (8 == length || 10 == length) {
            if (10 == length) {
                dateFormat = new SimpleDateFormat(DATE_WITH_SPLIT);
            } else {
                dateFormat = new SimpleDateFormat(DATE_WITHNOT_SPLIT);
            }
            ParsePosition position = new ParsePosition(0);
            return dateFormat.parse(date, position);
        } else {
            if (19 == length || 14 == length) {
                return convertStringToDateTime(date);
            } else {
                return null;
            }
        }
    }

    /**
     * 把格式为(yyyy-MM-dd hh:mm:ss或yyyyMMddHHmmss)的字符串转换为DateTime型(带时分秒)
     *
     * @param date 字符串日期
     * @return DateTime型日期(失败返回null)
     */
    public static Date convertStringToDateTime(String date) {
        int length = date.length();
        SimpleDateFormat dateFormat = null;
        if (19 == length || 14 == length) {
            if (19 == length) {
                dateFormat = new SimpleDateFormat(DATETIME_WITH_SPLIT);
            } else {
                dateFormat = new SimpleDateFormat(DATETIME_WITHNOT_SPLIT);
            }
            ParsePosition position = new ParsePosition(0);
            return dateFormat.parse(date, position);
        } else {
            if (8 == length || 10 == length) {
                return convertStringToDate(date);
            } else {
                return null;
            }
        }
    }

    /**
     * 取得时间（ yyyy-MM-dd 00:00:00）
     *
     * @return Date
     */
    public static Date getDate(Date date) {
        if (null == date) {
            return convertStringToDate(convertDateToString(new Date(), true));
        }
        return convertStringToDate(convertDateToString(date, true));
    }

    /**
     * 取得当前时间（ yyyy-MM-dd HH:mm:ss）
     *
     * @return
     */
    public static Date getDateTime() {
        return new Date();
    }

    /**
     * 取得当前时间（ yyyy-MM-dd HH:mm:ss）
     *
     * @return
     */
    public static Date getDateTimeMillSecond() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String dateStr = formatter.format(new Date());
        ParsePosition position = new ParsePosition(0);
        return formatter.parse(dateStr, position);
    }

    /**
     * 获取当前年度
     *
     * @return 年度
     */
    public static int getCurrYear() {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return 月份
     */
    public static int getCurrMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 日期比较大小
     *
     * @param date1 日期
     * @param date2 日期
     * @param canEq 是否可以相等
     * @return date1-date2:true(大于)||false(小于)
     * @throws java.text.ParseException
     */
    public static boolean dateCompare(Date date1, Date date2, boolean canEq) {
        boolean result = false;
        long time = 1000 * 3600 * 24;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_WITHNOT_SPLIT);
        Date firstDate = convertStringToDate(dateFormat.format(date1));
        Date lastDate = convertStringToDate(dateFormat.format(date2));
        long dateRange = (firstDate.getTime() - lastDate.getTime()) / time;
        if (canEq) {
            if (dateRange >= 0) {
                result = true;
            }
        } else {
            if (dateRange > 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * @title: dateCompare
     * @description: 比较日期大小
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static int dateCompare(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_WITHNOT_SPLIT);
        String dateFirst = dateFormat.format(date1);
        String dateLast = dateFormat.format(date2);
        int dateFirstIntVal = Integer.parseInt(dateFirst);
        int dateLastIntVal = Integer.parseInt(dateLast);
        if (dateFirstIntVal > dateLastIntVal) {
            return 1;
        } else if (dateFirstIntVal < dateLastIntVal) {
            return -1;
        }
        return 0;
    }

    /**
     * 取得时间差(精确到秒)
     *
     * @param startDateTime 起始时间
     * @param endDateTime 结束时间
     * @return 时间差
     */
    public static long getdifftime(Date startDateTime, Date endDateTime) {
        long startTime = getUnixtime(startDateTime);
        long endTime = getUnixtime(endDateTime);
        return endTime - startTime;
    }

    /**
     * 判断是否为同一天
     *
     * @param date1 日期
     * @param date2 日期
     * @return true||false
     */
    public static boolean sameDayCheck(Date date1, Date date2) {
        boolean result = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_WITHNOT_SPLIT);
        String dateFirst = dateFormat.format(date1);
        String dateLast = dateFormat.format(date2);
        if (dateFirst.equals(dateLast)) {
            result = true;
        }
        return result;
    }

    /**
     * 判断是否为同一月
     *
     * @param date1 日期
     * @param date2 日期
     * @return true||false
     */
    public static boolean sameMonthCheck(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    /**
     * 日期加N天
     *
     * @param date
     * @param day
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date dateAdd(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        return date;
    }

    /**
     * 日期加N小时
     *
     * @param date
     * @param hour
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date dateAddHours(Date date, int hour) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.HOUR_OF_DAY, hour);// 把日期往后增加N小时.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推N小时的结果
        return date;
    }

    /**
     * 日期加N分钟
     *
     * @param date
     * @param hour
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date dateAddMinutes(Date date, int minutes) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MINUTE, minutes);// 把日期往后增加N分钟.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推N分钟的结果
        return date;
    }

    /**
     * 日期加N分钟
     *
     * @param date
     * @param hour
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date dateAddSeconds(Date date, int seconds) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.SECOND, seconds);// 把日期往后增加N秒.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推N秒的结果
        return date;
    }

    /**
     * 取unix时间戳
     *
     * @param date 日期
     * @return Long
     */
    public static Long getUnixtime(Date date) {
        Date dateTime = convertStringToDateTime(convertDateTimeToString(date, true));
        Long unixTime = dateTime.getTime() / 1000;
        return unixTime;
    }

    /**
     * 取系统当前时间的毫秒数
     *
     * @return Long 毫秒数
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 取某年某月的第一天
     *
     * @param year 年度
     * @param month 月份
     * @return 某年某月的第一天
     */
    public static Date getMonthFirstDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return getDate(cal.getTime());
    }

    /**
     * 取某年某月的最后一天
     *
     * @param year 年度
     * @param month 月份
     * @return 某年某月的最后一天
     */
    public static Date getMonthLastDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDate(cal.getTime());
    }

    /**
     * 取当前季度起始月份
     *
     * @param currMonth 当前月份
     * @return int 起始月份
     */
    public static int getQuarterFirstMonth(int currMonth) {
        int season = getSeason(currMonth);
        switch (season) {
            case 1:
                return 1;
            case 2:
                return 4;
            case 3:
                return 7;
            case 4:
                return 10;
            default:
                return 1;
        }
    }

    /**
     * 取当前季度最后月份
     *
     * @param currMonth 当前月份
     * @return int 最后月份
     */
    public static int getQuarterLastMonth(int currMonth) {
        int season = getSeason(currMonth);
        switch (season) {
            case 1:
                return 3;
            case 2:
                return 6;
            case 3:
                return 9;
            case 4:
                return 12;
            default:
                return 1;
        }
    }

    /**
     * 获取季度信息
     *
     * @param month 月份
     * @return 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     */
    public static int getSeason(int month) {
        int season = 0;
        switch (month - 1) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

    /**
     * 取得当前时间（ yyyy-MM-dd HH:mm:ss）
     *
     * @return
     */
    public static Date getCurrDateTime() {
        return new Date();
    }

    /**
     * 获取当天的日期 (yyyy-MM-dd)
     *
     * @return
     */
//    public static Date getToday() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_WITH_SPLIT);
////        return DateTime.parse(simpleDateFormat.format(getCurrDateTime())).toDate();
//        return simpleDateFormat.format(getCurrDateTime());
//    }

    /**
     * 获取当天的日期的周末日期
     *
     * @return
     */
    public static Date getSunday4NowDate() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 7);
        return c.getTime();
    }

    public static Date getMonday4NowDate() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        return cal.getTime();
    }

    public static String thisMonth() {
        int x;                  // 日期属性：年
        int y;                  // 日期属性：月
        int z;                  // 日期属性：日
        Calendar localTime = Calendar.getInstance();
        String strY = null;
        x = localTime.get(Calendar.YEAR);
        y = localTime.get(Calendar.MONTH) + 1;
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-01";
    }

    /**
     * 功能：得到当前月份月底 格式为：xxxx-yy-zz (eg: 2007-12-31)<br>
     *
     * @return String
     * @author pure
     */
    public static String thisMonthEnd() {
        int x;                  // 日期属性：年
        int y;                  // 日期属性：月
        int z;                  // 日期属性：日
        Calendar localTime = Calendar.getInstance();
        String strY = null;
        String strZ = null;
        boolean leap = false;
        x = localTime.get(Calendar.YEAR);
        y = localTime.get(Calendar.MONTH) + 1;
        if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
            strZ = "31";
        }
        if (y == 4 || y == 6 || y == 9 || y == 11) {
            strZ = "30";
        }
        if (y == 2) {
            leap = leapYear(x);
            if (leap) {
                strZ = "29";
            } else {
                strZ = "28";
            }
        }
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-" + strZ;
    }

    /**
     * 功能：判断输入年份是否为闰年<br>
     *
     * @param year
     * @return 是：true 否：false
     * @author pure
     */
    public static boolean leapYear(int year) {
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0)
                    leap = true;
                else
                    leap = false;
            } else
                leap = true;
        } else
            leap = false;
        return leap;
    }

    /**
     * @title: secToTime
     * @description: 秒数转化成时分秒形式 xx:xx:xx
     * @param time
     * @return
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    private static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static void main(String[] args) {
        System.out.println(CalendarUtil.thisMonth());
    }
}
