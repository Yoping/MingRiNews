package com.yubin.news.model.palyAndroidApi;


import com.yubin.news.utils.LogUtil;

public class HttpCodeUtil {
    public static void notOk(DataBean dataBean){
        LogUtil.i("========http not ok",dataBean.getErrorMsg());
    }
}
