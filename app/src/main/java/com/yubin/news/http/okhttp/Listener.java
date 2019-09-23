package com.yubin.news.http.okhttp;

/**
 * Created by YUBIN on 2018/4/23.
 */
public interface Listener {
    void onResult(String data);
    void onError(String errorInfo);
}
