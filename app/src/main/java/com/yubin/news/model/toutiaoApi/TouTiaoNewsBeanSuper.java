package com.yubin.news.model.toutiaoApi;

import java.util.List;

/**
 * Created by YUBIN on 2018/4/23.
 */
public class TouTiaoNewsBeanSuper {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ToutiaoNewsDataBean> getData() {
        return data;
    }

    public void setData(List<ToutiaoNewsDataBean> data) {
        this.data = data;
    }

    private String message;
    private List<ToutiaoNewsDataBean> data;
}
