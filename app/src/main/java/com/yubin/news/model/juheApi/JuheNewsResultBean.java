package com.yubin.news.model.juheApi;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/4.
 */

public class JuheNewsResultBean {
    private String stat;
    private List<JuheNewsBean> data;
    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<JuheNewsBean> getData() {
        return data;
    }

    public void setData(List<JuheNewsBean> data) {
        this.data = data;
    }



}
