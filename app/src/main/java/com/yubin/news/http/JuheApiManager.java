package com.yubin.news.http;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.yubin.news.application.Constants;
import com.yubin.news.application.MyApplication;
import com.yubin.news.model.juheApi.JuheNewsResultBean;
import com.yubin.news.utils.LogUtil;

import org.json.JSONObject;

/**
 * Created by YUBIN on 2017/5/5.
 */

public class JuheApiManager {

    public static void getNews(String newsType,final JuheGetNewsListener listener){
        String url="http://v.juhe.cn/toutiao/index?type="+newsType+"&key="+ Constants.JUHEAPI_KEY;
        LogUtil.i("url="+url);
        RequestQueue requestQueue= MyApplication.getRequestQueue();
        final Gson gson=MyApplication.getGson();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                LogUtil.i("json="+jsonObject.toString());
                try{
                    JSONObject resultJsonObject=jsonObject.getJSONObject("result");
                    String stat=resultJsonObject.getString("stat");
                    if(stat.equals("1")){
                        JuheNewsResultBean juheNewsResultBean=gson.fromJson(resultJsonObject.toString(), JuheNewsResultBean.class);
                        listener.onResult(juheNewsResultBean.getData());
                    }else{
                        listener.onError(null);
                    }
                }catch (Exception ex){
                    listener.onError("数据解析错误！");
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError("数据返回错误！");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}
