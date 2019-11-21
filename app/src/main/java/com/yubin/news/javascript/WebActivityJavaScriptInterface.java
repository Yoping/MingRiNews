package com.yubin.news.javascript;

import android.webkit.JavascriptInterface;

import com.yubin.news.application.MyApplication;
import com.yubin.news.ui.activity.WebActivity;
import com.yubin.news.utils.LogUtil;


/**
 * @author:YUBIN
 * @create at:2018/8/2.
 * @description: JavaScriptInterface,用Webview加载一些H5页面时注入，供网页调用Android原生的方法
 */

public class WebActivityJavaScriptInterface {
    private WebActivity webActivity;

    public WebActivityJavaScriptInterface(WebActivity webActivity) {
        this.webActivity = webActivity;
    }

    /**
     * 打开分享面板
     * @param imageUrl
     */
    @JavascriptInterface
    public void openShareBoard(String imageUrl) {
        LogUtil.i("JavascriptInterface=======openShareBoard:"+imageUrl);
//        webActivity.setImageUrl(imageUrl);
//        webActivity.requestSharePermission();
        webActivity.share(imageUrl);

    }

    /**
     * 弹出提示信息
     * @param info
     */
    @JavascriptInterface
    public void showToast(String info) {
        LogUtil.i("JavascriptInterface=======showToast:"+info);
//        ToastUtil.show(webActivity, info);
        webActivity.showToastInfo(info);
    }

    /**
     * 获取用户token
     */
    @JavascriptInterface
    public String getToken() {
        LogUtil.i("JavascriptInterface=======getToken");
//        return MyApplication.user_token;
        return "token";
    }

    /**
     * 关闭页面
     */
    @JavascriptInterface
    public void close() {
        LogUtil.i("JavascriptInterface=======close");
        webActivity.finishActivity();
    }
    /**
     * 返回
     */
    @JavascriptInterface
    public void goBack() {
        LogUtil.i("JavascriptInterface=======goBack");
        webActivity.goBack();
    }


}
