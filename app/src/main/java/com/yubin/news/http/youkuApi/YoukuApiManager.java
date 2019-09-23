package com.yubin.news.http.youkuApi;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.yubin.news.application.MyApplication;
import com.yubin.news.cache.CacheUtil;
import com.yubin.news.model.youkuApi.YoukuCategoriesBean;
import com.yubin.news.model.youkuApi.YoukuVideoArrayBean;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.MD5Util;
import com.yubin.news.utils.NetworkUtil;

import org.json.JSONObject;

/**
 * Created by YUBIN on 2017/5/5.
 */

public class YoukuApiManager {


//    优酷API：
//    获取分类列表
//    http://openapi.youku.com/v2/schemas/video/category.json
//
//    获取具体分类视频（其中client_id暂用优酷Demo中的792b1d08a5348d0d）
//    http://openapi.youku.com/v2/videos/by_category.json?client_id=792b1d08a5348d0d&category=音乐&period=month&orderby=comment-count&page=2&count=10

    /**
     * 获取优酷视频种类信息
     * @param listener
     */
    public static void getCategory(final YoukuCategoryListener listener) {
        String url = "http://openapi.youku.com/v2/schemas/video/category.json";
        LogUtil.i("url=" + url);
        final Gson gson = MyApplication.getGson();
        final String filepath=getAGoodCachePathyUrl(url,"youku_category_");
        if(!NetworkUtil.isNewworkAvailable(MyApplication.getInstance().getApplicationContext())){
            try{
                YoukuCategoriesBean youkuCategoriesBean = gson.fromJson(CacheUtil.readFile(filepath).toString(), YoukuCategoriesBean.class);
                listener.onResult(youkuCategoriesBean.getCategories());
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            RequestQueue requestQueue = MyApplication.getRequestQueue();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    LogUtil.i("json=" + jsonObject.toString());
                    try {
                        CacheUtil.saveFile(jsonObject.toString(),filepath);
                        YoukuCategoriesBean youkuCategoriesBean = gson.fromJson(jsonObject.toString(), YoukuCategoriesBean.class);
                        for (int i = 0; i < youkuCategoriesBean.getCategories().size(); i++) {
                            LogUtil.i("category=" + youkuCategoriesBean.getCategories().get(i).getLabel());
                        }
                        listener.onResult(youkuCategoriesBean.getCategories());
                    } catch (Exception e) {
                        LogUtil.i("数据解析错误");
                        listener.onError("数据解析错误");
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    LogUtil.i("数据返回错误");
                    listener.onError("数据返回错误");
                }
            });
            jsonObjectRequest.setShouldCache(true);
            requestQueue.add(jsonObjectRequest);
        }
    }

    /**
     * 获取数据的时间范围
     */
    public static class Period {
        //今日
        public static String today = "today";
        //本周
        public static String week = "week";
        //本月
        public static String month = "month";
        //历史
        public static String history = "history";
    }
    public static String periodOfCurrent = Period.today;

    /**
     * 排序方式
     */
    public static class OrderBy {
        //发布时间
        public static String published = "published";
        //播放数量
        public static String view_count = "view-count";
        //评论总数
        public static String comment_count = "comment-count";
        //转发总数
        public static String reference_count = "reference-count";
        //收藏时间
        public static String favorite_time = "favorite-time";
        //收藏总数
        public static String favorite_count = "favorite-count";
    }

    /**
     * 根据分类获取优酷视频信息数组
     *
     * @param orderby
     * @param page
     * @param count
     * @param listener
     */
    public static void getCategoryVideoArray(String category, String period, String orderby, int page, int count, final YoukuVideoArrayListener listener) {
        String url = "http://openapi.youku.com/v2/videos/by_category.json?client_id=792b1d08a5348d0d&category=" + category + "&period=" + period + "&orderby=" + orderby + "&page=" + page + "&count=" + count;
        LogUtil.i("url=" + url);
        final Gson gson = MyApplication.getGson();
        final String filepath=getAGoodCachePathyUrl(url,"youku_video_");
        if(!NetworkUtil.isNewworkAvailable(MyApplication.getInstance().getApplicationContext())){
            try{
                YoukuVideoArrayBean youkuVideoArrayBean = gson.fromJson(CacheUtil.readFile(filepath).toString(), YoukuVideoArrayBean.class);
                listener.onResult(youkuVideoArrayBean.getVideos());
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            RequestQueue requestQueue = MyApplication.getRequestQueue();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    LogUtil.i("json=" + jsonObject.toString());
                    try {
                        CacheUtil.saveFile(jsonObject.toString(),filepath);
                        YoukuVideoArrayBean youkuVideoArrayBean = gson.fromJson(jsonObject.toString(), YoukuVideoArrayBean.class);
                        for (int i = 0; i < youkuVideoArrayBean.getVideos().size(); i++) {
                            LogUtil.i("category=" + youkuVideoArrayBean.getVideos().get(i).getTitle());
                        }
                        listener.onResult(youkuVideoArrayBean.getVideos());
                    } catch (Exception e) {
                        LogUtil.i("数据解析错误");
                        listener.onError("数据解析错误");
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    LogUtil.i("数据返回错误");
                    listener.onError("数据返回错误");
                }
            });
            requestQueue.add(jsonObjectRequest);
        }
    }


    /**
     * 根据获取数据Url生成一个合适的数据缓存名称
     * @param url
     * @return
     */
    private static String getAGoodCachePathyUrl(String url,String sign){
        String filepath=sign+ MD5Util.stringToMD5(url);
        return filepath;
    }


}
