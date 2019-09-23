package com.yubin.news.http.showApi;

import com.google.gson.Gson;
import com.show.api.ShowApiRequest;
import com.yubin.news.application.Constants;
import com.yubin.news.application.MyApplication;
import com.yubin.news.model.showApi.ShowApiChannelBean;
import com.yubin.news.utils.LogUtil;

/**
 * Created by YUBIN on 2017/5/5.
 */

public class ShowApiManager {


    public void getChannelData(){
        new Thread(){
            //在新线程中发送网络请求
            public void run() {
                final String res=new ShowApiRequest( "http://route.showapi.com/109-34", Constants.SHOWAPI_APPID,Constants.SHOWAPI_SECRET)
                        .post();
                System.out.println(res);
                parseData(res);
            }
        }.start();
    }

    private void parseData(String data){
        Gson gson= MyApplication.getGson();
        ShowApiChannelBean showApiChannelBean=gson.fromJson(data,ShowApiChannelBean.class);
        for(int i=0;i<showApiChannelBean.getShowapi_res_body().getChannelList().size();i++){
            LogUtil.i(showApiChannelBean.getShowapi_res_body().getChannelList().get(i).getName());
        }
    }
}
