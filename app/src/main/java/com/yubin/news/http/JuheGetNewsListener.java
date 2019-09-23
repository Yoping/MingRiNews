package com.yubin.news.http;

import com.yubin.news.model.juheApi.JuheNewsBean;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/5.
 */

public interface JuheGetNewsListener {
    void onResult(List<JuheNewsBean> newsList);
    void onError(String errorInfo);
}
