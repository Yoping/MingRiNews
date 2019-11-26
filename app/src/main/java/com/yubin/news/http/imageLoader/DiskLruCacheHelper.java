package com.yubin.news.http.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import com.jakewharton.disklrucache.DiskLruCache;
import com.yubin.news.utils.MD5Util;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by YUBIN on 2019/10/18.
 */
public class DiskLruCacheHelper {
    private long MAX_DISK_MEMORY_SIZE = 60 * 1024 * 1024;
    private int DISK_CACHE_INDEX = 0;
    private DiskLruCache diskLruCache;

    public DiskLruCacheHelper(Context context, String fileName) {
        File diskCacheDir = getDiskCacheDir(context, fileName);
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdir();
        }
        if (diskCacheDir.getUsableSpace() > MAX_DISK_MEMORY_SIZE) {
            try {
                diskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, MAX_DISK_MEMORY_SIZE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取磁盘缓存文件
     *
     * @param name
     * @return
     */
    public File getDiskCacheDir(Context context, String name) {
        boolean available = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if (available) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + name);
    }

    /**
     * 将URL转化为缓存key值
     *
     * @param url
     * @return
     */
    public static String changeUrlToKey(String url) {
        return MD5Util.stringToMD5(url);
    }

    /**
     * 通过Key值获取输入流
     *
     * @param key
     * @return
     * @throws IOException
     */
    public InputStream getInputStreamByKey(String key) throws IOException {
        DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
        FileInputStream inputStream = null;
        if (snapshot != null) {
            inputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);

        }
        return inputStream;
    }

    /**
     * 通过key值获取位图
     * @param key
     * @return
     * @throws IOException
     */
    public Bitmap getBitmapByKey(String key, int reqWidth, int reqHeight) {
        FileInputStream inputStream=null;
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);
            if (snapshot == null) {
                return null;
            }else{
                inputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
            }
            if(reqWidth==0||reqHeight==0){
                return BitmapFactory.decodeStream(inputStream);
            }else{
                FileDescriptor fileDescriptor=inputStream.getFD();
                return ImageResizer.getSuitSizeOfBitmapFromFile(fileDescriptor,reqWidth,reqHeight);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 保存一个对象
     *
     * @param key
     * @param object
     * @return
     */
    public boolean putObject(String key, Object object) {
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(key);
            OutputStream os = editor.newOutputStream(0);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            try {
                oos.writeObject(object);
                editor.commit();
                diskLruCache.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                editor.abort();
                diskLruCache.flush();
                return false;
            } finally {
                os.close();
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 通过位图输入流保存图片
     *
     * @param key
     * @param inputStream
     * @return
     */
    public boolean putBitmapByInpuStream(String key, InputStream inputStream) {
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(key);
            OutputStream outputStream = editor.newOutputStream(DISK_CACHE_INDEX);
            try {
                int length;
                byte[] buffer = new byte[1024];
                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
                editor.abort();
            } finally {

            }
            diskLruCache.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
