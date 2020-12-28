package com.boxuegu.basis.pulsar.qimoor.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 */
public class TimeUtil {
    public TimeUtil() {
    }

    public static int getUnixTimestamp() {
        long rest = System.currentTimeMillis() / 1000L;
        return (int) rest;
    }

    public static String getNowStr(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getNowStrYYMMDD() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getNowStr() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getNowWithNoSecond() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        Date date = new Date(new Date().getTime() - 60 * 1000);
        return sdf.format(date);
    }

    public static String getTimeStrByLong(Long times) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(times);
        return sdf.format(date);
    }

    public static String getDateID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getDateID(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        return (new SimpleDateFormat("yyyyMMdd")).format(date);
    }

    /**
     * 格式化数据 20200401/044540
     */
    public static String formatTimeStr(String time) throws ParseException {
        String newTime = time.substring(0, 4) + "-";
        newTime += time.substring(4, 6) + "-";
        newTime += time.substring(6, 8) + " ";
        newTime += time.substring(9, 11) + ":";
        newTime += time.substring(11, 13) + ":";
        newTime += time.substring(13, 15);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(newTime);
        return sdf.format(date);
    }

    public static Date formatTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(time);
    }

    public static Long getNowLong() {
        Date date = new Date();
        return date.getTime();
    }

    public static Long getLongTimeByStr(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        new Date();

        try {
            Date date = sdf.parse(time);
            return date.getTime();
        } catch (ParseException var4) {
            var4.printStackTrace();
            return Long.parseLong("0");
        }
    }

    public static Integer getNextDateID(Integer date, Integer i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date befoerdate;
        try {
            befoerdate = sdf.parse(date + "");
        } catch (ParseException var5) {
            var5.printStackTrace();
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(befoerdate);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + i);
        return Integer.parseInt(sdf.format(calendar.getTime()));
    }

    public static Integer getNextDateID(String date, Integer i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date befoerdate;
        try {
            befoerdate = sdf.parse(date);
        } catch (ParseException var5) {
            var5.printStackTrace();
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(befoerdate);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + i);
        return Integer.parseInt(sdf.format(calendar.getTime()));
    }

    public static int getBettwenDays(int date1, int date2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date d1 = sdf.parse(date1 + "");
            Date d2 = sdf.parse(date2 + "");
            return (int) ((d2.getTime() - d1.getTime()) / 86400000L);
        } catch (Exception var5) {
            return -1;
        }
    }

    public static int getBettwenHours(String date1, String date2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
            return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60));
        } catch (Exception var5) {
            return -1;
        }
    }


    public static long BettwenTimes(String date1, String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse(date1 + "");
        Date d2 = sdf.parse(date2 + "");
        return d1.getTime() - d2.getTime();
    }

    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(today);
    }

    public static String getCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getCurrentMonthFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        return sdf.format(date);
    }

    public static String getCurrentOnlyMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return String.valueOf(month);
    }

    public static String getDefineCurrentMonth(int preMonth, int dayNum) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal_1 = Calendar.getInstance();
        cal_1.add(Calendar.MONTH, preMonth);
        cal_1.set(Calendar.DATE, dayNum);
        return format.format(cal_1.getTime());
    }

    public static String getCurrentMonthFirst() {
        return getDefineCurrentMonth(0, 1);
    }

    public static String after2Years() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 2);
        date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy");
        return df.format(date);
    }

    /**
     * 根据开始时间、结束时间，以24小时分组返回，如果开始、结束时间在24小时内，返回结果索引0是开始时间，索引1是结束时间
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     */
    public static List<String> getDaysBy24Hours(String beginTime, String endTime) {
        List<String> result = new ArrayList<>();
        Long hours = 86400000L;
        Long beginLong = TimeUtil.getLongTimeByStr(beginTime);
        Long endLong = TimeUtil.getLongTimeByStr(endTime);
        result.add(beginTime);
        while (true) {
            beginLong = beginLong + hours;
            if (beginLong >= endLong) {
                result.add(endTime);
                break;
            }
            beginTime = TimeUtil.getTimeStrByLong(beginLong);
            result.add(beginTime);
        }
        return result;
    }

    public static String getYMD(String timeStr) {
        return timeStr.substring(0, 13);
    }

    /**
     * 判断时间是否在某个时间区间内
     */
    public static boolean isEffectiveDate(String nowTimeStr, String startTimeStr, String endTimeStr) throws ParseException {
        String format = "HH:mm";
        Date nowTime = new SimpleDateFormat(format).parse(nowTimeStr);
        Date startTime = new SimpleDateFormat(format).parse(startTimeStr);
        Date endTime = new SimpleDateFormat(format).parse(endTimeStr);
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    /**
     * @param date1  字符串日期1
     * @param date2  字符串日期2
     * @param format 日期格式化方式  format="yyyy-MM-dd"
     * @description: 计算两个字符串日期相差的天数
     */
    public static Long dayDiff(String date1, String date2, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        long diff = 0L;
        try {
            long d1 = formater.parse(date1).getTime();
            long d2 = formater.parse(date2).getTime();
            //diff=(Math.abs(d1-d2) / (1000 * 60 * 60 * 24));
            diff = (d1 - d2) / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    public static boolean compareTo(String nowTime, String beforeTime) {
        int i = nowTime.compareTo(beforeTime);
        return i < 0 || i == 0;
    }

    public static String beforeOneHourToNowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR,
                calendar.get(Calendar.HOUR) - 1);// 让日期加1
        calendar.get(Calendar.DATE);// 加1之后的日期Top
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    public static String getISO8601Timestamp(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(tz);
        return df.format(date);
    }

    public static String getISO8601TimeByStr(String timeStr) {
        return timeStr.replace(" ", "T");
    }

    public static int timeDiff(String beginTime, String endTime) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date fromDate2 = simpleFormat.parse(beginTime);
        Date toDate2 = simpleFormat.parse(endTime);
        long from2 = fromDate2.getTime();
        long to2 = toDate2.getTime();
        return (int) ((to2 - from2) / 1000);
    }

    public static void main(String[] args) throws Exception {
//        Date date = new Date();
//        String s = "2019-02-01 11:11:11";
//        System.out.println(getISO8601Timestamp(date));
//        System.out.println(getISO8601TimeByStr(s));
        System.out.println(timeDiff("2020-09-14 17:55:00", "2020-09-14 17:56:01"));
    }
}
