package com.yubin.news.http.imageLoader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by YUBIN on 2019/10/16.
 */
public class ImageResizer {

    public static final String Tag = "imageResizer";

    /**
     * 计算最合适的图片大小
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int countSuitSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        if (reqWidth == 0 || reqHeight == 0) {
            return 1;
        }
        int width = options.outWidth;
        int height = options.outHeight;
        int suitSize = 1;

        if(width>reqWidth){
            suitSize=width/reqWidth;
            return suitSize*suitSize;
        }else if(height>reqHeight){
            suitSize=height/reqHeight;
            return suitSize*suitSize;
        }


//        if ((height > reqHeight) || (width > reqWidth)) {
//            final int halfHeight = height / 2;
//            final int halfWidth = width / 2;
//            while ((halfHeight / suitSize) >= reqHeight && (halfWidth / suitSize) >= reqWidth) {
//                /**
//                 * suitSize（options中的inSampleSize应该为2的指数，自动向下取最大的2的指数）
//                 */
//                suitSize = suitSize * 2;
//            }
//        }
        return suitSize;
    }

    /**
     * 从资源文件中加载最适大小的图片
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap getSuitSizeOfBitmapFormRes(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        /**
         * 仅仅加载图片的长宽信息
         */
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        /**
         * 以最合适的尺寸去加载图片
         */
        options.inSampleSize = countSuitSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap getSuitSizeOfBitmapFromFile(FileDescriptor fd, int reqWidth, int reqHeight){
        /**
         * FileInputStream是一种有序的文件流，而两次decodeStream调用影响了了文件流的位置属性，使第二次得到的内容为null，
         * 通过文件描述符来加载则不会出现这种情况。
         */
        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFileDescriptor(fd,null,options);
        options.inSampleSize=countSuitSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeFileDescriptor(fd,null,options);
    }











}
