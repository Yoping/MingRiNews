package com.yubin.news.http.toutiaoApi;

import com.yubin.news.model.toutiaoApi.TouTiaoNewsBean;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/5.
 */

public interface ToutiaoGetSomeNewsListener {
    void onResult(List<TouTiaoNewsBean> newsList,boolean getNetData,boolean isCacheData);
    void onError(String errorInfo);
}
