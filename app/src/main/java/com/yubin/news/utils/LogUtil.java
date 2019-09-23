package com.yubin.news.utils;

import android.util.Log;

/**
 * Created by YUBIN on 2017/5/3.
 */

public class LogUtil {

    private static final Boolean isDebug=true;

    /**
     * Log信息输出
     * @param text
     */
    public static void i(String text){
        if(!isDebug){
            return;
        }
        Log.i("=========",text);
    }
    public static void i(String tag,String text){
        if(!isDebug){
            return;
        }
        Log.i(tag+"=========",text);
    }

    //规定每段显示的长度
    private static int LOG_MAXLENGTH = 2000;

    public static void showlongLog(String msg) {
        int strLength = msg.length();
        Log.i("=======LongLog","===================="+strLength+"=====================");
        int start = 0;
        int end = LOG_MAXLENGTH;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                Log.i("=======LongLog", msg.substring(start, end));
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                Log.i("=======LongLog", msg.substring(start, strLength));
                break;
            }
        }
    }

}
