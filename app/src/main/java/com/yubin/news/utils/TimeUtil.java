package com.yubin.news.utils;

/**
 * Created by YUBIN on 2017/5/15.
 */

public class TimeUtil {

    public static String changeIntTimeToString(int num){
        String time="";
        int sec=num%60;
        int min=(num/60)%60;
        int hour=num/3600;

        if(sec<10){
            time=":0"+sec;
        }else{
            time=":"+sec;
        }

        if(hour==0){
            time=min+time;
        }else{
            if(min<10){
                time=hour+":0"+min+time;
            }else{
                time=hour+":"+min+time;
            }
        }
        return time;
    }
}
