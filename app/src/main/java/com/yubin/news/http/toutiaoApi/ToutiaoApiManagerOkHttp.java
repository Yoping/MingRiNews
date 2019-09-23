package com.yubin.news.http.toutiaoApi;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yubin.news.application.MyApplication;
import com.yubin.news.cache.CacheUtil;
import com.yubin.news.model.toutiaoApi.TouTiaoNewsBean;
import com.yubin.news.model.toutiaoApi.ToutiaoGalleryBean;
import com.yubin.news.model.toutiaoApi.ToutiaoImageStoryArrayBean;
import com.yubin.news.model.toutiaoApi.ToutiaoNewsArrayBean;
import com.yubin.news.model.toutiaoApi.ToutiaoRelatedGalleryArrayBean;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.MD5Util;
import com.yubin.news.utils.MyTestUtil;
import com.yubin.news.utils.NetUtils;
import com.yubin.news.utils.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by YUBIN on 2017/5/5.
 */

public class ToutiaoApiManagerOkHttp {

    /**
     * 根据传入的新闻类型获取一些新闻,通过isGetNewData决定取缓存数据，还是重新获取网络数据
     * @param context
     * @param newsType
     * @param dataFlag
     * @param isGetNewData
     * @param listener
     */
    public static void getSomeNews(final Context context, String newsType, int dataFlag, boolean isGetNewData , final ToutiaoGetSomeNewsListener listener) {
        final String url = "http://is.snssdk.com/api/news/feed/v53/?category=" + newsType + "&concern_id=6215497899594222081&refer=1&count=5&last_refresh_sub_entrance_interval=1495004536&loc_mode=5&tt_from=enter_auto&lac=4527&cid=28883&cp=569a11bef9578q1&iid=10109443249&device_id=36240811078&ac=wifi&channel=tengxun&aid=13&app_name=news_article&version_code=612&version_name=6.1.2&device_platform=android&ab_version=120430%2C127611%2C126061%2C125848%2C115758%2C114037%2C122834%2C126067%2C125863%2C123727%2C114105%2C127189%2C127128%2C127585%2C126071%2C127719%2C125503%2C127044%2C125174%2C118016%2C127332%2C104320%2C126057%2C127025%2C121512%2C126524%2C126879%2C126272%2C122948%2C123123%2C31207%2C128166%2C125528%2C126019%2C114338%2C123717%2C127758&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_feature=102749%2C94563&abflag=3&ssmix=a&device_type=A0001&device_brand=Sony&language=zh&os_api=17&os_version=4.2.2&uuid=864895026212660&openudid=40393a9a523ac06a&manifest_version_code=612&resolution=1080*1920&dpi=320&update_version_code=6124&_rticket=1495004536331";
        LogUtil.i("url=" + url);
        final Gson gson = MyApplication.getGson();
//        getSomeNewsOfNet(url,gson,isGetNewData,listener,"");
//        getSomeNewsOfNetOKHttpGet(url,gson,isGetNewData,listener,"");
        getSomeNewsOfNetOKHttpNewThread(url,gson,isGetNewData,listener,"");



    }


    /**
     * 获取网络新闻
     * @param url
     * @param gson
     * @param listener
     * @param cacheFilepath
     */
    public static void getSomeNewsOfNet(String url,final Gson gson,final boolean getNetData,final ToutiaoGetSomeNewsListener listener,final String cacheFilepath){
        RequestQueue requestQueue = MyApplication.getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
//                LogUtil.i("json=" + jsonObject.toString());
//                LogUtil.showlongLog("long json=" + jsonObject.toString());
                try {
                    String dataStr = jsonObject.getString("data");
                    LogUtil.i("data=success");
                    convertSomeNewsJson(dataStr,gson,listener,cacheFilepath,getNetData,false);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LogUtil.i("数据返回错误！");
                LogUtil.i("volleyError.toString()=" + volleyError.toString());
                listener.onError("数据返回错误！");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    public static void getSomeNewsOfNetOKHttpGet(String url,final Gson gson,final boolean getNetData,final ToutiaoGetSomeNewsListener listener,final String cacheFilepath){

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).get().build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.i("okhttp------onFailure");
                listener.onError("数据返回错误！");
            }

            /**注意：
             * 1.该返回方法不在主线程里面
             * 2.通过response.body().string()拿到返回的数据，但是该方法只能调用一次，二次调用会报错
             * @param call
             * @param response
             * @throws IOException
             */
            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                try{
                    if(response.isSuccessful()){
                        String dataStr =response.body().string();
                        LogUtil.i("okhttp------data:"+dataStr);
                        convertSomeNewsJson(dataStr,gson,listener,cacheFilepath,getNetData,false);

                    }else{
                        LogUtil.i("okhttp------respone fail");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }


    /**
     * okhttp构建Post请求
     * @param url
     * @param gson
     * @param getNetData
     * @param listener
     * @param cacheFilepath
     */
    public static void getSomeNewsOfNetOKHttpPost(String url,final Gson gson,final boolean getNetData,final ToutiaoGetSomeNewsListener listener,final String cacheFilepath){
        OkHttpClient client=new OkHttpClient();
        FormBody.Builder body=new FormBody.Builder();
        body.add("name","yubin");
        body.add("age","5");
        Request request=new Request.Builder().url(url).post(body.build()).build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.i("okhttp------onFailure");
                listener.onError("数据返回错误！");
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                try{
                    if(response.isSuccessful()){
                        String dataStr =response.body().string();
                        LogUtil.i("okhttp------data:"+dataStr);
                        convertSomeNewsJson(dataStr,gson,listener,cacheFilepath,getNetData,false);
                    }else{
                        LogUtil.i("okhttp------respone fail");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }



    public static void getSomeNewsOfNetOKHttpNewThread(final String url,final Gson gson,final boolean getNetData,final ToutiaoGetSomeNewsListener listener,final String cacheFilepath){

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(url).get().build();
                Call call=client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        LogUtil.i("okhttp------onFailure");
                        listener.onError("数据返回错误！");
                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {
                        try{
                            if(response.isSuccessful()){
                                String dataStr =response.body().string();
                                LogUtil.i("okhttp------data:"+dataStr);
                                convertSomeNewsJson(dataStr,gson,listener,cacheFilepath,getNetData,false);

                            }else{
                                LogUtil.i("okhttp------respone fail");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();



    }

//    /**
//     * 获取网络新闻
//     * @param url
//     * @param gson
//     * @param listener
//     * @param cacheFilepath
//     */
//    private static void getSomeNewsOfNet(String url,final Gson gson,final boolean getNetData,final ToutiaoGetSomeNewsListener listener,final String cacheFilepath){
//        RequestQueue requestQueue = MyApplication.getRequestQueue();
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject jsonObject) {
////                LogUtil.i("json=" + jsonObject.toString());
////                LogUtil.showlongLog("long json=" + jsonObject.toString());
//                try {
//                    String dataStr = jsonObject.getString("data");
//                    LogUtil.i("data=success");
//                    convertSomeNewsJson(dataStr,gson,listener,cacheFilepath,getNetData,false);
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                LogUtil.i("数据返回错误！");
//                LogUtil.i("volleyError.toString()=" + volleyError.toString());
//                listener.onError("数据返回错误！");
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }

    /**
     * 解析新闻内容
     * @param dataStr
     * @param gson
     * @param listener
     * @param cacheFilepath
     */
    public static  void convertSomeNewsJson(String dataStr,Gson gson,ToutiaoGetSomeNewsListener listener,String cacheFilepath,boolean getNetData,boolean isCacheData){
        List<TouTiaoNewsBean> datalist = new ArrayList<>();
        try {
//            CacheUtil.saveFile(dataStr,cacheFilepath);
//            LogUtil.i("saveFile=" + cacheFilepath);
            dataStr = "{\"data\":" + dataStr + "}";
//            LogUtil.showlongLog("dataStr="+dataStr);
            ToutiaoNewsArrayBean toutiaoNewsArrayBean = gson.fromJson(dataStr, ToutiaoNewsArrayBean.class);
            for (int i = 0; i < toutiaoNewsArrayBean.getData().size(); i++) {
                String content=toutiaoNewsArrayBean.getData().get(i).getContent();
                //content=decodeUnicode(content);//转换编码之后需要去掉“”，不转的话解析之后的数据为空，好纠结啊。。
                if(content.contains("action_extra")){
                    String tempStr=content.substring(content.indexOf("action_extra"),content.indexOf("action_extra")+50);
                    String tempStr2=tempStr.replace("extra\": \"{\"channel","extra\":{\"channel");
                    tempStr2=tempStr2.replace("}\", \"","}, \"");
                    content=content.replace(tempStr,tempStr2);
                }
                if(content.contains("log_extra")){
                    content=content.replace("log_extra\": \"{\"rit","log_extra\": {\"rit");
                    content=content.replace("}\", \"log_pb","}, \"log_pb");
                    content=content.replace("}\", \"phone_number","}, \"phone_number");
                }

                if(content.contains("\"user_auth_info\": \"\", ")){
                    content=content.replace("\"user_auth_info\": \"\", ","\"user_auth_info\":{}, ");
                }

                if(content.contains("user_auth_info\": \"{\"auth_type")){
                    content=content.replace("user_auth_info\": \"{\"auth_type","user_auth_info\": {\"auth_type");
                    content=content.replace("}\", \"user_verified","}, \"user_verified");
                }

                TouTiaoNewsBean touTiaoNewsBean = gson.fromJson(content, TouTiaoNewsBean.class);
                String title = touTiaoNewsBean.getTitle();
                LogUtil.i("title=" + title);
                LogUtil.showlongLog("content+"+content);
                datalist.add(touTiaoNewsBean);
            }

            listener.onResult(datalist,getNetData,isCacheData);
        } catch (Exception ex) {
            listener.onError("数据解析错误！");
            listener.onResult(datalist,getNetData,isCacheData);
            ex.printStackTrace();
        }
    }




}
