package com.yubin.news.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.githang.statusbar.StatusBarCompat;
import com.yubin.news.R;
import com.yubin.news.application.BundleKey;
import com.yubin.news.application.MyApplication;
import com.yubin.news.base.BaseActivity;
import com.yubin.news.javascript.WebActivityJavaScriptInterface;
import com.yubin.news.ui.customview.CustomTopBar;
import com.yubin.news.ui.customview.CustomTopTitle;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.ToastUtil;

import java.util.HashMap;

/**
 * @author:YUBIN
 * @create at:2018/7/18.
 * @description: 多功能网页加载界面，用于加载各种H5网页界面
 * MODE_INVOTE_FRIENDS = 邀请好友
 * MODE_ET_CHEATS = 以太秘籍
 * MODE_LOGIN_AGREEMENT = 用户登录、注册协议
 * MODE_ETAI_SHOP = 以太商城
 * MODE_ETAI_WDF_ACT = 精彩活动
 */


public class WebActivity extends BaseActivity {

    private CustomTopBar customTopTitle;
    private int currentMode = MODE_INVOTE_FRIENDS;
    public static final int MODE_INVOTE_FRIENDS = 1;
    public static final int MODE_ET_CHEATS = 2;
    public static final int MODE_LOGIN_AGREEMENT = 3;
    public static final int MODE_ETAI_SHOP = 4;
    public static final int MODE_ETAI_WDF_ACT = 5;
    public static final int MODE_ETAI_WDF_ACT_ANS_QUES = 6;
    private WebView webView;
    private ImageView ivBack;
    private String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web);
        getArgs();
        super.onCreate(savedInstanceState);

    }

    private void getArgs() {

        Intent intent=getIntent();
        if(!intent.getStringExtra(BundleKey.NewsUrl).isEmpty()){
            url=intent.getStringExtra(BundleKey.NewsUrl);
        }
    }

    @Override
    public void initview() {
        customTopTitle = findViewById(R.id.custom_top_title_web);
        ivBack=customTopTitle.getLeftIcon();
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

//        //设置加载进来的页面自适应手机屏幕
//        WebSettings settings=webView.getSettings();
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);

        webView.addJavascriptInterface(new WebActivityJavaScriptInterface(WebActivity.this), "android");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

        });
        webView.loadUrl(url);




    }


    @Override
    public void setListener() {
        customTopTitle.getLeftIconEnd().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void beginToWork() {

    }

    public void showToastInfo(String info) {
        ToastUtil.show(WebActivity.this, info);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 供JS调用
     *
     * @param imageUrl
     */
    public void share(String imageUrl) {

    }

    /**
     * 供JS调用
     */
    public void finishActivity() {
        finish();
    }

    /**
     * 供JS调用
     */
    public void goBack() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }


}
