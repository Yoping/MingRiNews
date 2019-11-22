package com.yubin.news.utils;

import android.util.Log;

import com.yubin.news.application.MyApplication;


/**
 * Created by YUBIN on 2017/5/3.
 * 自定义日志打印类
 */

public class LogUtil {

//    private static final Boolean isDebug=true;
    /**
     * Log信息输出
     * @param text
     */
    public static void i(String text){
        if(MyApplication.isRelease){
            return;
        }
        Log.i("=========",text);
    }

    public static void debug(String tag, String text){
        Log.d(tag,text);
    }

    public static void debug(String text){
        Log.d("========",text);
    }

    public static void ex(String text){
        if(MyApplication.isRelease){
            return;
        }
        Log.i("*********exception:",text);
    }

    public static void i(String tag, String text){
        if(MyApplication.isRelease){
            return;
        }
        Log.i(tag+"=========",text);
    }

    //规定每段显示的长度
    private static int LOG_MAXLENGTH = 2000;
    /**
     * 输出长日志，一般log最多输出8000个字符
     * 但是网络请求返回的数据信息有时候会超过这个字数，所有要自己拼接一下
     * @param msg
     */
    public static void showlongLog(String msg) {
        if(MyApplication.isRelease){
            return;
        }
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
