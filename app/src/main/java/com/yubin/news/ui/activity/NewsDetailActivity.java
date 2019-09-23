package com.yubin.news.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.ui.customview.CustomProgressDialog;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.ToastUtil;

import java.util.Map;

import static android.R.attr.keycode;

/**
 * Created by YUBIN on17-5-29 下午2:19
 * Last modified 17-6-5 上午11:01
 *
 */

public class NewsDetailActivity extends AppCompatActivity {

    private WebView webView;
    private Map<String, String> extraHeaders;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomProgressDialog.showDialog(NewsDetailActivity.this);

        setContentView(R.layout.activity_news_detail);
        webView=(WebView) findViewById(R.id.webview_news_detail);

//        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
//        webView.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
//        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
//        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
//        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
//        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
//        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
//        webView.getSettings().setDomStorageEnabled(true);//DOM Storage

        url= Constants.GoToNewsUrl;
        LogUtil.i("url="+url);

//        extraHeaders = new HashMap<String, String>();
//        extraHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
//        extraHeaders.put("Content-Type", "text/html; charset=utf-8");
//        extraHeaders.put("Host", "toutiao.com");
//        extraHeaders.put("Referer", "http://www.toutiao.com");
//        webView.loadUrl(Constants.GoToNewsUrl,extraHeaders);


        webView.setWebViewClient(new WebViewClient(){
//            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                webView.loadUrl(Constants.GoToNewsUrl,extraHeaders);
                webView.loadUrl(url);

                return false;
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                ToastUtil.show(NewsDetailActivity.this,"服务器抛出了一个问题...");
                super.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                ToastUtil.show(NewsDetailActivity.this,"服务器抛出了一个问题...");
//                super.onReceivedError(view, request, error);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                CustomProgressDialog.dismissDialog();
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
//        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keycode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


}
