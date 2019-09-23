package com.yubin.news.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by YUBIN on 2017/5/3.
 */

public class ToastUtil {

    /**
     * 快速显示Toast
     * @param context
     * @param text
     */
    public static void show(Context context,String text){
        if(context!=null){
            Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
        }
    }
}
