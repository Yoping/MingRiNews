package com.yubin.news.http.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.yubin.news.R;
import com.yubin.news.utils.LogUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by YUBIN on 2019/10/15.
 */
public class ImageLoader {
    private final String Tag="=======ImageLoader";
    public static final int TAG_KEY_URL=0;//????
    public static final int MSG_POST_RESULT=1;
    private Context context;
    private String DISK_CACHE_DIR_NAME="imageLoader_cache";
    private LruCache<String, Bitmap> memoryCache;
    private DiskLruCacheHelper diskLruCacheHelper;
    private long MAX_DISK_MEMORY_SIZE = 60 * 1024 * 1024;
    private static volatile ImageLoader instance;
    private ExecutorService threadPool;

    public static ImageLoader getInstance(Context context){
        if(instance==null){
            synchronized (ImageLoader.class){
                if(instance==null){
                    instance=new ImageLoader(context);
                }
            }
        }
        return instance;
    }

    private ImageLoader(Context context2) {
        context = context2.getApplicationContext();
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        //创建内存缓存
        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
        //创建磁盘缓存
        diskLruCacheHelper=new DiskLruCacheHelper(context,DISK_CACHE_DIR_NAME);
        //创建线程池
        threadPool= Executors.newFixedThreadPool(5);
    }

    private static class LoaderResult{
        public ImageView imageView;
        public String url;
        public Bitmap bitmap;
        public LoaderResult(ImageView imageView, String url, Bitmap bitmap){
            this.imageView=imageView;
            this.url=url;
            this.bitmap=bitmap;
        }
    }

    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            LoaderResult loaderResult=(LoaderResult)msg.obj;
            ImageView imageView=loaderResult.imageView;
            String url=(String)imageView.getTag(R.id.tag_image_url);
            if(url.equals(loaderResult.url)){
                imageView.setImageBitmap(loaderResult.bitmap);
            }else{
                Log.w(Tag,"set image bitmal,but url has changed,ignored!");
            }
        }
    };

    /**
     * 加载图片
     * @param imageView
     * @param url
     */
    public void displayBitmap(final ImageView imageView, final String url){
        imageView.setTag(R.id.tag_image_url,url);
        final String key= DiskLruCacheHelper.changeUrlToKey(url);
        Bitmap bitmap=getBitmapFormCache(key,imageView.getWidth(),imageView.getHeight());
        if(bitmap!=null){
            imageView.setImageBitmap(bitmap);
        }else{
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap2=downloadBitmap(url);
                    if(bitmap2!=null){
                        putBitmapToMemCache(key,bitmap2);
                        putBitmapToDiskCache(key,bitmap2);
                        LoaderResult loaderResult=new LoaderResult(imageView,url,bitmap2);
                        handler.obtainMessage(MSG_POST_RESULT,loaderResult).sendToTarget();
                    }
                }
            };
            threadPool.execute(runnable);
        }
    }

    /**
     * 从网络上下载图片
     * @return
     */
    private Bitmap downloadBitmap(String urlStr){
        HttpURLConnection urlConnection=null;
        BufferedInputStream bufferedInputStream=null;
        Bitmap bitmap=null;
        try {
            URL url=new URL(urlStr);
            urlConnection= (HttpURLConnection) url.openConnection();
            bufferedInputStream=new BufferedInputStream(urlConnection.getInputStream());
            bitmap=BitmapFactory.decodeStream(bufferedInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            if(bufferedInputStream!=null){
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    /**
     * 从缓存中获取图片
     * @param key
     * @return
     */
    private Bitmap getBitmapFormCache(String key, int reqWidth, int reqHeight){
        Bitmap bitmap=getBitmapFromMemCache(key);
        if(bitmap==null){
            bitmap=getBitmapFromDiskCache(key,reqWidth,reqHeight);
            if(bitmap!=null){
                putBitmapToMemCache(key,bitmap);
            }else{
                LogUtil.debug(Tag,"get from disk="+key);
            }
        }else{
            LogUtil.debug(Tag,"get from cache="+key);
        }
        return bitmap;
    }

    /**
     * 从内存缓存中获取图片
     * @return
     */
    private Bitmap getBitmapFromMemCache(String key){
        return memoryCache.get(key);

    }

    /**
     * 将图片存入内存缓存
     * @param key
     * @param bitmap
     */
    private void putBitmapToMemCache(String key, Bitmap bitmap){
        if(getBitmapFromMemCache(key)==null){
            memoryCache.put(key,bitmap);
        }
    }

    /**
     * 从磁盘缓存中获取图片
     * @param key
     * @return
     */
    private Bitmap getBitmapFromDiskCache(String key, int reqWidth, int reqHeight){
        try{
            return diskLruCacheHelper.getBitmapByKey(key,reqWidth,reqHeight);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将图片存入磁盘缓存
     * @param key
     */
    private boolean putBitmapToDiskCache(String key, InputStream inputStream){
        return diskLruCacheHelper.putBitmapByInpuStream(key,inputStream);
    }

    private boolean putBitmapToDiskCache(String key, Object object){
        return diskLruCacheHelper.putObject(key,object);
    }

    private static final int IO_BUFFERED_SIZE=8*1024;
    private static boolean downloadUrlToStream(String urlStr, OutputStream outputStream){
        HttpURLConnection urlConnection=null;
        BufferedOutputStream outStream=null;
        BufferedInputStream inStream=null;
        try {
            URL url=new URL(urlStr);
            urlConnection= (HttpURLConnection) url.openConnection();
            inStream=new BufferedInputStream(urlConnection.getInputStream(),IO_BUFFERED_SIZE);
            outStream=new BufferedOutputStream(outputStream,IO_BUFFERED_SIZE);
            int b;
            while ((b= inStream.read())!=-1){
                outStream.write(b);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            try {
                if(outStream!=null){
                    outStream.close();
                }
                if(inStream!=null){
                    inStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }





































}


