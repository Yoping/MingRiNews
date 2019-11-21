package com.yubin.news.javascript;

import android.webkit.WebView;

/**
 * @author: YUBIN
 * @create at:2018/7/17.
 * @description: javaScript函数，用于对webview进行一些相关的操作
 */

public class JavaScriptUtil {

    /**
     * 统一添加一些需要执行的script方法
     * @param webView
     */
    public static void addSomeScriptFunction(WebView webView){
        if(WebViewUtil.hasChangeToLocalImg){
            loadLocalImage(webView);
        }else{
            imgReset(webView);
        }
    }

    /**
     * 重置图片大小，使自适应屏幕大小
     * @param webView
     */
    public static void imgReset(WebView webView) {
        //遍历所有的img节点，并添加图片自适应屏幕样式
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }

    /**
     * 先加载本地图片，点击之后再加载网络图片(顺便重置了图片大小)
     * 注意：这个操作需要数据执行了WebViewUtil中的changeImageToLocalImgAndKeepNetImg方法才能生效
     * @param webView
     */
    public static void loadLocalImage(WebView webView) {
        webView.loadUrl("javascript:(function(){" +
                "var objs=document.getElementsByTagName(\"img\");\n" +
                "            for(var i=0;i<objs.length;i++){\n" +
                "\t\t\t\tobjs[i].style.width='100%';\n" +
                "\t\t\t\tobjs[i].style.height='auto';\n" +
                "                objs[i].onclick=function(){\n" +
                "                    this.src=this.dataset."+WebViewUtil.IMAGE_SRC_OF_NET+";\t\t\t\n" +
                "\t\t\t\t}\n" +
                "            }})()");//后面一定要带一个（）才有用

//        function(){
//            var objs=document.getElementsByTagName("img");
//            for(var i=0;i<objs.length;i++){
//                objs[i].style.width='100%';
//                objs[i].style.height='auto';
//                objs[i].onclick=function(){
//                    this.src=this.dataset.src_of_net;
//                }
//            }
//        }

    }


    /**
     * 添加图片点击函数，回调给Android原生方法进行处理
     * @param webView
     */
    public final static String IMAGE_LISTENER = "imagelistener";
    public static void addImageClickListner(WebView webView) {
        //遍历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window."+IMAGE_LISTENER+".openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }
}
