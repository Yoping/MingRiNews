package com.yubin.news.model.toutiaoApi;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/11.
 */

public class ToutiaoImageStoryArrayBean {

    private boolean has_more;
    private String message;
    private List<ToutiaoImageStoryBean> data;
    private TouTiaoNextBean next;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ToutiaoImageStoryBean> getData() {
        return data;
    }

    public void setData(List<ToutiaoImageStoryBean> data) {
        this.data = data;
    }

    public TouTiaoNextBean getNext() {
        return next;
    }

    public void setNext(TouTiaoNextBean next) {
        this.next = next;
    }



}
