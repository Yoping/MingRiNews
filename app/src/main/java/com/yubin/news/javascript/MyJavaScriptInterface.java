package com.yubin.news.javascript;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;


/**
 * @author:YUBIN
 * @create at:2018/7/17.
 * @description:
 */
public class MyJavaScriptInterface {
    private Context context;
    public MyJavaScriptInterface(Context context) {
        this.context = context;
    }

    /**
     * 点击图片时的回调执行Android原生方法
     * @param img
     */
    @JavascriptInterface
    public void openImage(String img) {
        Log.i("TAG", "响应点击事件!");
//        Intent intent = new Intent();
//        intent.putExtra("image", img);
//        intent.setClass(context, LoginActivity.class);//BigImageActivity查看大图的类，自己定义就好
//        context.startActivity(intent);
    }


}
