package com.yubin.news.http.youkuApi;

import com.yubin.news.model.youkuApi.YoukuVideoBean;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/5.
 */

public interface YoukuVideoArrayListener {
    void onResult(List<YoukuVideoBean> videoList);
    void onError(String errorInfo);
}
