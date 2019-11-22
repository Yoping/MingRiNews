package com.yubin.news.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by YUBIN on 2017/5/15.
 * 与时间相关的工具类
 */

public class TimeUtil {

    /**
     * 将Int类型的时间描述改为String类型 12：12
     * @param num
     * @return
     */
    public static String changeIntTimeToString(int num) {
        String time = "";
        int sec = num % 60;
        int min = (num / 60) % 60;
        int hour = num / 3600;

        if (sec < 10) {
            time = ":0" + sec;
        } else {
            time = ":" + sec;
        }

        if (hour == 0) {
            time = min + time;
        } else {
            if (min < 10) {
                time = hour + ":0" + min + time;
            } else {
                time = hour + ":" + min + time;
            }
        }
        return time;
    }



    /**
     * 将日期转换为 2018-07-24 11:03:49 String形式
     * @param date
     * @return
     */
    public static String changeDateToStr(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr=simpleDateFormat.format(date);
        return dateStr;
    }



    /**
     * 将2018-07-23 17:15:19 形式的日期转换为年月日2018-07-23
     * @param dateStr
     * @return
     */
    public static String changeTimeStrToShort(String dateStr){
//        dataStr=" 2018-07-23 17:15:19";
        dateStr=dateStr.trim();
        String shortTime;
        if(dateStr.contains(" ")){
            int indexSpace=dateStr.indexOf(" ");
            shortTime=dateStr.substring(0,indexSpace);
        }else{
            shortTime=dateStr;
        }
        return shortTime;
    }



    /**
     * 将String类型的日期2018.07.21 18:45:25 转换为long型时间戳
     * @param dateStr
     * @return
     */
    public static long changeDataToTimeStamp(String dateStr){
        Date date;
        long timeStamp=0;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = simpleDateFormat.parse(dateStr);
            timeStamp= date.getTime();
        }catch (Exception ex){
            ex.printStackTrace();
            LogUtil.ex(ex.getStackTrace().toString());
        }
        return timeStamp;
    }

    /**
     * 得到现在的日期
     * @return
     */
    public static String getNowDate(){
        return changeTimeStampToDate(System.currentTimeMillis());
    }

    /**
     * 将日期转换为星期几
     * @param datetime
     * @return
     */
    public static String changeShortDateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 得到现在的日期
     * @return
     */
    public static String getNowShortDate(){
        return changeTimeStampToShortDate(System.currentTimeMillis());
    }

    /**
     * 将long型时间戳转换为日期类型2018-07-31 18:45:25
     * @param timeStamp
     * @return
     */
    public static String changeTimeStampToDate(long timeStamp){
        String dateStr="";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(timeStamp);
            dateStr= simpleDateFormat.format(date);
        }catch (Exception ex){
            ex.printStackTrace();
            LogUtil.ex(ex.getStackTrace().toString());
        }
        return dateStr;
    }

    /**
     * 将long型时间戳转换为日期类型2018-07-31 18:45:25
     * @param timeStamp
     * @return
     */
    public static String changeTimeStampToShortDate(long timeStamp){
        String dateStr="";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(timeStamp);
            dateStr= simpleDateFormat.format(date);
        }catch (Exception ex){
            ex.printStackTrace();
            LogUtil.ex(ex.getStackTrace().toString());
        }
        return dateStr;
    }

    /**
     * 将String类型的时间戳转换为日期类型2018-07-31 18:45:25
     * @param timeStamp
     * @return
     */
    public static String changeTimeStampToDate(String timeStamp){
        String time="时间转换失败";
        try{
            long timeLong=Long.parseLong(timeStamp);
            time=changeTimeStampToDate(timeLong);
        }catch (Exception ex){
            ex.printStackTrace();
            LogUtil.i(time);
        }
        return time;
    }

    public static long getCurrentTimeStamp(){
        long time=System.currentTimeMillis();
        return time;
    }

    /**
     * 将String类型的日期2018-07-31 18:45:25转换为10分钟前、6小时前
     * @param dateStr
     * @return
     */
    public static String getSuitTimeString(String dateStr){
        if( dateStr.length()<12){
            /**
             * 如果长度小于12，应该是这种格式2018-07-31 ，直接返回
             */
            return dateStr;
        }

        String info="时间错误..";
        try {
            long myTime=changeDataToTimeStamp(dateStr);
            if(myTime>= System.currentTimeMillis()){
                info="时间错误..";
            }else{
                long timediff=(System.currentTimeMillis()-myTime)/1000;
                if(timediff<=3600){
                    info= (timediff/60)+"分钟前";
                }else if(timediff<=(3600*24)){
                    info= (timediff/3600)+"小时前";
                }else{
                    int indexSpace=dateStr.indexOf(" ")+0;
                    info= dateStr.substring(0,indexSpace);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            LogUtil.ex(ex.getStackTrace().toString());
        }
        return info;
    }

    /**
     * 将String类型的时间戳先转换为String类型的日期，在转换为10分钟前、6小时前
     * @param longTime
     * @return
     */
    public static String getSuitTimeFromStringLong(String longTime){
        String dateTime=changeTimeStampToDate(longTime);
        return getSuitTimeString(dateTime);
    }
}
