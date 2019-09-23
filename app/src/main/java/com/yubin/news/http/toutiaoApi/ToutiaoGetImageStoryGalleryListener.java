package com.yubin.news.http.toutiaoApi;

import com.yubin.news.model.toutiaoApi.ToutiaoGalleryBean;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/5.
 */

public interface ToutiaoGetImageStoryGalleryListener {
    void onResult(List<ToutiaoGalleryBean> gelleryImageList);
    void onError(String errorInfo);
}
