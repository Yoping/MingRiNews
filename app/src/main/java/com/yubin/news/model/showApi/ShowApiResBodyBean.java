package com.yubin.news.model.showApi;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/4.
 */

public class ShowApiResBodyBean {

    private int totalNum;
    private int ret_code;
    private List<NewsChannelBean> channelList;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public List<NewsChannelBean> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<NewsChannelBean> channelList) {
        this.channelList = channelList;
    }


}
