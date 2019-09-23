package com.yubin.news.model.youkuApi;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/15.
 */

public class YoukuVideoArrayBean {

    private int total;
    private float cost;
    private YoukuEBean e;
    private int count;
    private List<YoukuVideoBean> videos;
    private int page;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public YoukuEBean getE() {
        return e;
    }

    public void setE(YoukuEBean e) {
        this.e = e;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<YoukuVideoBean> getVideos() {
        return videos;
    }

    public void setVideos(List<YoukuVideoBean> videos) {
        this.videos = videos;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }



}
