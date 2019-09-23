package com.yubin.news.http.toutiaoApi;

import com.yubin.news.model.toutiaoApi.ToutiaoRelatedGalleryBean;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/5.
 */

public interface ToutiaoGetRelatedImageStoryArrayListener {
    void onResult(List<ToutiaoRelatedGalleryBean> imageStoryList);
    void onError(String errorInfo);
}
