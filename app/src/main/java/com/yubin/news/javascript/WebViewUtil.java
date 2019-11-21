package com.yubin.news.javascript;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yubin.news.application.MyApplication;
import com.yubin.news.application.MySetting;
import com.yubin.news.utils.NetworkUtil;


/**
 * @author:YUBIN
 * @create at:2018/7/17.
 * @description: 新闻详情页Webview的统一配置工具
 */

public class WebViewUtil {

    //默认文本类型
    public final static String MIME_TYPE = "text/html";
    //默认编码格式
    public final static String ENCODING = "UTF-8";
    //图片加载地址总目录
    public final static String baseUrl = "http://119.23.108.68";
    //本地默认图片地址
    public final static String defaultImagePath="file:///android_res/mipmap/click_to_dl_img.png";

    /**
     * 将HTML中图片的相对地址替换为绝对地址
     * @param html
     * @return
     */
    public static String changeImageUrlWithBase(String html) {
        return html.replace("img src=\"", "img src=\"" + baseUrl);
    }

    /**
     * 对拿到的HTML进行一些需要的转化操作************
     * @param html
     * @return
     */
    public static boolean hasChangeToLocalImg=false;
    public static String changeToMyHtml(String html) {
        if(!(NetworkUtil.getNetworkType(MyApplication.getContext())==NetworkUtil.NETWORK_WIFI)){
            //不是WIFI网络则加载本地默认图片
            html=changeImageToLocalImgAndKeepNetImg(html,defaultImagePath);
            hasChangeToLocalImg=true;
        }else{
            hasChangeToLocalImg=false;
        }
//        html=changeImageToLocalImgAndKeepNetImg(html,defaultImagePath);
        html = removeFont(html);
        return html;
    }

    /**
     * 将图片地址转化为本地默认图片
     * 正则：src="http.*?" alt=
     * @param html
     * @param localImagePath
     * @return
     */
    public static String changeImageToLocalImg(String html, String localImagePath) {
        String beginStr = "src=\"http";
        String endStr = "\" alt=";
        String regStr = beginStr + ".*?" + endStr;
        String replaceToStr = "src=\""+defaultImagePath+endStr;
        html = html.replaceAll(regStr, replaceToStr);
        return html;
    }

    /**
     * 将图片地址转化为本地默认图片,并且保留原来的网络图片地址
     * 正则：src="http.*?" alt=
     * @param html
     * @param localImagePath
     * @return
     */
    //将网络图片地址暂时保存在这个字段当中
    public static final String IMAGE_SRC_OF_NET="src_of_net";
    public static String changeImageToLocalImgAndKeepNetImg(String html, String localImagePath) {
        String replceStr="src=\"http";
        String replaceToStr ="src=\""+localImagePath+"\" data-"+IMAGE_SRC_OF_NET+"=\"http";
        html = html.replaceAll(replceStr, replaceToStr);
        return html;
    }

    /**
     * 移除html中的字体属性
     * @param html
     * @return
     */
    public static String removeFont(String html) {
        String regexStr="font-size: \\d+px;";
        return html.replaceAll(regexStr, "");
    }


    /**
     * 对Webview进行一些相关的设置
     * @param webView
     */
    public static void setMyWebViewSetting(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        /***可执行javaScript*/
        webSettings.setJavaScriptEnabled(true);
        /*** JavaScript可以自动打开windows*/
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        /*** 设置缓存*/
        webSettings.setAppCacheEnabled(true);
        /*** 设置缓存模式,一共有四种模式*/
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        /*** 设置缓存路径*/
//        webSettings.setAppCachePath("");
        /*** 支持缩放(适配到当前屏幕)*/
        webSettings.setSupportZoom(true);
        /*** 将图片调整到合适的大小*/
//        webSettings.setUseWideViewPort(true);
        /*** 支持内容重新布局,一共有四种方式,默认的是NARROW_COLUMNS*/
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        /*** 设置可以被显示的屏幕控制*/
        webSettings.setDisplayZoomControls(true);
        /*** 设置默认字体大小*/
//        webSettings.setDefaultFontSize(14);
        setMyWebViewTextSize(webView, MySetting.FONT_SIZE_MIDDLE);
        webSettings.setDefaultTextEncodingName("utf-8");

        webView.setBackgroundColor(0); // 设置背景色
        webView.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255
        webView.setVisibility(View.VISIBLE); // 加载完之后进行设置显示，以免加载时初始化效果不好看
    }

    /**
     * 设置加载进来的页面自适应手机屏幕大小
     * @param webView
     */
    public static void setScreenSizeAuto(WebView webView){
        WebSettings settings=webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }

    public static void setMyWebViewTextSize(WebView webView, int fontSize) {
        if (fontSize == MySetting.FONT_SIZE_SMALL) {
            webView.getSettings().setTextSize(WebSettings.TextSize.SMALLER);
        } else if (fontSize == MySetting.FONT_SIZE_BIG) {
            webView.getSettings().setTextSize(WebSettings.TextSize.LARGER);
        } else {
            webView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        }

    }
}
