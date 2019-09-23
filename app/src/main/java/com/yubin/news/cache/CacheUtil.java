package com.yubin.news.cache;

import android.content.Context;

import com.yubin.news.application.MyApplication;
import com.yubin.news.utils.LogUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by YUBIN on 2017/6/14.
 */

public class CacheUtil {
    public static final boolean useCache=false;
    public static final String ERROR="no_the_cache";
    /**
     * 保存序列化的对象到缓存文件中
     * @param serializedObject
     * @param fileName
     * @return
     */
    public static boolean saveFile(Serializable serializedObject,String fileName){
//        if(!useCache){
//            return false;
//        }
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try{
            fos= MyApplication.getInstance().getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(serializedObject);
            oos.flush();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            try{
                oos.close();
                fos.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * 从缓存文件中读取出序列化的对象
     * @param fileName
     * @return
     */
    public static Serializable readFile(String fileName){
//        if(!useCache){
//            return null;
//        }
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        try{
            fis=MyApplication.getInstance().getApplicationContext().openFileInput(fileName);
            ois=new ObjectInputStream(fis);
            LogUtil.i("----- return ois ----");
            return (Serializable) ois.readObject();
        }catch (Exception ex){
            ex.printStackTrace();
            LogUtil.i("----- return null ----");
            return ERROR;
        }finally {
            try{
                ois.close();
                fis.close();
            }catch (Exception ex){
                ex.printStackTrace();
                LogUtil.i("----- return null ----");
                return ERROR;
            }
        }
    }
}

