package com.yubin.news.http.toutiaoApi;

import com.yubin.news.model.toutiaoApi.ToutiaoImageStoryBean;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/5.
 */

public interface ToutiaoGetImageStoryArrayListener {
    void onResult(List<ToutiaoImageStoryBean> imageStoryList);
    void onError(String errorInfo);
}
