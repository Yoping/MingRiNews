package com.yubin.news.http.okhttp;

import com.yubin.news.utils.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by YUBIN on 2018/4/23.
 */
public class OkhttpManager {
    public static String url="http://is.snssdk.com/api/news/feed/v53/?category=news_society";

    public static void getData(String url, final Listener listener){
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().url(url).get().build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onError("请求出错");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String data=response.body().string();
                    LogUtil.i("--data---"+data);
                    listener.onResult(data);
                }else{
                    listener.onError("数据错误");
                }
            }
        });
    }

    public static void getDataNewThread(final String url, final Listener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                final Request request=new Request.Builder().get().url(url).build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        listener.onError("请求出错");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            String data=response.body().string();
                            LogUtil.i("--data--"+data);
                            listener.onResult(data);
                        }else{
                            listener.onError("数据错误");
                        }
                    }
                });
            }
        }).start();

    }
}
