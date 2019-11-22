package com.yubin.news.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yubin.news.R;

/**
 * @author:YUBIN
 * @create at:2018/7/18.
 * @description:Glide加载工具，用于统一占位图等操作
 */

public class GlideUtil {
    public static void loadBanner(Context context, String url, ImageView imageView) {
        LogUtil.i("banner gilde=" + url);
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.default_pic)
                .error(R.mipmap.lv)
                .centerCrop()
                .dontAnimate()
                .into(imageView);
    }

    public static void load(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.default_pic)
                .error(R.mipmap.logo)
                .centerCrop()
                .dontAnimate()
                .into(imageView);
    }

    public static void loadLocalImage(Context context, int resoureId, ImageView imageView) {
        Glide.with(context)
                .load(resoureId)
                .placeholder(R.mipmap.default_pic)
                .error(R.mipmap.lv)
                .centerCrop()
                .dontAnimate()
                .into(imageView);
    }

    /**
     * 加载刷新动画
     * @param context
     * @param imageView
     */
    public static void loadRefreshLoading(Context context, ImageView imageView) {
        Glide.with(context)
                .load(R.mipmap.loading_book_refresh)
                .placeholder(R.mipmap.loading_book_refresh)
                .error(R.mipmap.loading_book_refresh)
                .centerCrop()
                .dontAnimate()
                .into(imageView);
    }

    /**
     *加载动画
     * @param context
     * @param imageView
     */
    public static void loadLoadmoreLoading(Context context, ImageView imageView) {
        Glide.with(context)
                .load(R.mipmap.loading_book_loadmore)
                .placeholder(R.mipmap.loading_book_loadmore)
                .error(R.mipmap.loading_book_loadmore)
                .centerCrop()
                .dontAnimate()
                .into(imageView);
    }

    public static void loadBackground(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.splash)
                .error(R.mipmap.splash)
                .centerCrop()
                .dontAnimate()
                .into(imageView);
    }

    public static void loadAvatar(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.default_avatar)
                .error(R.mipmap.default_avatar)
                .centerCrop()
                .dontAnimate()
                .into(imageView);
    }

    public static void loadBigImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.default_pic)
                .error(R.mipmap.logo)
                .centerCrop()
                .dontAnimate()
                .into(imageView);
    }
}
