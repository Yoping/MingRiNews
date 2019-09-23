package com.yubin.news.http.youkuApi;

import com.yubin.news.model.youkuApi.YoukuCategoryBean;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/5.
 */

public interface YoukuCategoryListener {
    void onResult(List<YoukuCategoryBean> categorieList);
    void onError(String errorInfo);
}
