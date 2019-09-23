package com.yubin.news.model.toutiaoApi;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/11.
 */

public class ToutiaoSubImageBean {
    private int height;
    private int width;
    private String uri;
    private String url;
    private List<ToutiaoImageUrlBean> url_list;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ToutiaoImageUrlBean> getUrl_list() {
        return url_list;
    }

    public void setUrl_list(List<ToutiaoImageUrlBean> url_list) {
        this.url_list = url_list;
    }


}
