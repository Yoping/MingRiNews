package com.yubin.news.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yubin.news.application.MyApplication;

/**
 * Created by YUBIN on 2015/12/3.
 */

public class SharedPreferencesUtil {

    /**
     *  数据保存文件名
     *  */
    private static final String sharedPreferences_name="my_save_file";

    /**
     * SharedPreferencesUtil 数据保存工具实例
     */
    private static SharedPreferencesUtil instance;

    /**
     * SharedPreferences 实例
     */
    private static SharedPreferences sharedPreferences;

    /**
     * SharedPreferences 编辑器实例
     */
    private static SharedPreferences.Editor editor;

    /**
     * 私有构造方法
     * @param context
     */
    private SharedPreferencesUtil(Context context){
        sharedPreferences=context.getSharedPreferences(sharedPreferences_name,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    /**
     * 唯一实例静态获取方法
     * @return
     */
    public synchronized static SharedPreferencesUtil getInstance(){
        if(instance==null){
            instance=new SharedPreferencesUtil(MyApplication.getInstance());
        }
        return instance;
    }


    /**
     * 保存一个key对应的字符串类型的数据==========================
     * @param key
     * @param value
     * @return
     */
    public static synchronized boolean putString(String key,String value){
        editor.putString(key,value);
        return  editor.commit();
    }

    /**
     * 获得key对应的字符串数据
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(String key,String defaultValue){
        return  sharedPreferences.getString(key,defaultValue);
    }


    /**
     * 保存一个key对应的Int类型的数据=============================
     * @param key
     * @param value
     * @return
     */
    public synchronized static boolean putInt(String key, int value){
        editor.putInt(key,value);
        return  editor.commit();
    }

    /**
     * 获得key对应的Int数据
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key,int defaultValue){
        return  sharedPreferences.getInt(key,defaultValue);
    }

    /**
     * 保存一个key对应的Float类型的数据===========================
     * @param key
     * @param value
     * @return
     */
    public synchronized static boolean putFloat(String key, float value){
        editor.putFloat(key,value);
        return  editor.commit();
    }

    /**
     * 获得key对应的Float数据
     * @param key
     * @param defaultValue
     * @return
     */
    public static float getFloat(String key,float defaultValue){
        return  sharedPreferences.getFloat(key,defaultValue);
    }


    /**
     * 保存一个key对应的Boolean类型的数据=============================
     * @param key
     * @param value
     * @return
     */
    public synchronized static boolean putBoolean(String key, boolean value){
        editor.putBoolean(key,value);
        return  editor.commit();
    }

    /**
     * 获得key对应的Boolean数据
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String key,boolean defaultValue){
        return  sharedPreferences.getBoolean(key,defaultValue);
    }






}































