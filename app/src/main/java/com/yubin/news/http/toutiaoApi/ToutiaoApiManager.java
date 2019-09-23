package com.yubin.news.http.toutiaoApi;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yubin.news.application.MyApplication;
import com.yubin.news.cache.CacheUtil;
import com.yubin.news.model.toutiaoApi.TouTiaoNewsBean;
import com.yubin.news.model.toutiaoApi.ToutiaoGalleryBean;
import com.yubin.news.model.toutiaoApi.ToutiaoImageStoryArrayBean;
import com.yubin.news.model.toutiaoApi.ToutiaoNewsArrayBean;
import com.yubin.news.model.toutiaoApi.ToutiaoRelatedGalleryArrayBean;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.MD5Util;
import com.yubin.news.utils.MyTestUtil;
import com.yubin.news.utils.NetUtils;
import com.yubin.news.utils.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by YUBIN on 2017/5/5.
 */

public class ToutiaoApiManager {

//    今日头条API：
//    相册列表(改变max_behot_time可以获取不同的相册列表)
//    http://www.toutiao.com/api/pc/feed/?category=gallery_detail&max_behot_time=0&as=A1550911339D015&cp=5913FDA05125BE1
//
//    具体相册（其中6409150311588282626是相册groudID,其它参数不变）
//    http://a3.pstatp.com/article/full/16/1/6409150311588282626/6407645878584082946/1/0/?iid=10109443249&device_id=36240811078&ac=wifi&channel=tengxun&aid=13&app_name=news_article&version_code=612&version_name=6.1.2&device_platform=android&ab_version=120430%2C124684%2C123181%2C112577%2C125848%2C125887%2C115758%2C114037%2C122834%2C113607%2C123190%2C125863%2C123727%2C114105%2C125698%2C113608%2C125451%2C125720%2C125503%2C125174%2C118016%2C104320%2C123178%2C121512%2C112578%2C124050%2C125393%2C125907%2C122948%2C123123%2C31207%2C125872%2C121005%2C125528%2C114338%2C123717&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_feature=102749%2C94563&abflag=3&ssmix=a&device_type=A0001&device_brand=Sony&language=zh&os_api=17&os_version=4.2.2&uuid=864895026212660&openudid=40393a9a523ac06a&manifest_version_code=612&resolution=1080*1920&dpi=320&update_version_code=6124&_rticket=1494468882034


    /**
     * 获取图片故事的封面图片列表
     *
     * @param listener
     */
    public static void getImageStoryArray(final ToutiaoGetImageStoryArrayListener listener) {
        Random random = new Random();
        int behot_time = 1494994952 + random.nextInt(99999);
        String as = "A135C921EC0050F";
        as = as.replace(as.charAt(random.nextInt(as.length())), letter[random.nextInt(16)]);
        as = as.replace(as.charAt(random.nextInt(as.length())), letter[random.nextInt(16)]);
        as = as.replace(as.charAt(random.nextInt(as.length())), letter[random.nextInt(16)]);
        as = as.replace(as.charAt(random.nextInt(as.length())), letter[random.nextInt(16)]);
        as = as.replace(as.charAt(random.nextInt(as.length())), letter[random.nextInt(16)]);
        String cp = "591C00A5F0BF1E1";
        cp = cp.replace(cp.charAt(random.nextInt(cp.length())), letter[random.nextInt(16)]);
        cp = cp.replace(cp.charAt(random.nextInt(cp.length())), letter[random.nextInt(16)]);
        cp = cp.replace(cp.charAt(random.nextInt(cp.length())), letter[random.nextInt(16)]);
        cp = cp.replace(cp.charAt(random.nextInt(cp.length())), letter[random.nextInt(16)]);
        cp = cp.replace(cp.charAt(random.nextInt(cp.length())), letter[random.nextInt(16)]);
        String url = "http://www.toutiao.com/api/pc/feed/?category=%E7%BB%84%E5%9B%BE&utm_source=toutiao&max_behot_time=" + behot_time + "&as=" + as + "&cp=" + cp;
        LogUtil.i("url=" + url);
        RequestQueue requestQueue = MyApplication.getRequestQueue();
        final Gson gson = MyApplication.getGson();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                LogUtil.i("json=" + jsonObject.toString());
                try {
                    String message = jsonObject.getString("message");
                    if (message.equals("success")) {
                        LogUtil.i("message=success");
                        ToutiaoImageStoryArrayBean toutiaoImageStoryArrayBean = gson.fromJson(jsonObject.toString(), ToutiaoImageStoryArrayBean.class);
                        for (int i = 0; i < toutiaoImageStoryArrayBean.getData().size(); i++) {
                            LogUtil.i("toutiaoImageStoryArrayBean i=" + i + "  " + toutiaoImageStoryArrayBean.getData().get(i).getTitle());
                        }

                        listener.onResult(toutiaoImageStoryArrayBean.getData());
                    } else {
                        listener.onError(null);
                    }
                } catch (Exception ex) {
                    listener.onError("数据解析错误！");
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError("数据返回错误！");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//                headers.put("Accept-Encoding", "gzip, deflate, sdch");
//                headers.put("Accept-Language", "zh-CN,zh;q=0.8");
//                headers.put("Cache-Control", "max-age=0");
//                headers.put("Connection", "keep-alive");
//                headers.put("Host", "www.toutiao.com");
//                headers.put("Upgrade-Insecure-Requests", "1");
//                headers.put("Content-Type", "application/x-www-form-urlencoded");
//                headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
                LogUtil.i("headers=" + headers);
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setShouldCache(true);
        requestQueue.add(jsonObjectRequest);
    }

    private static char letter[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 获取图片故事的封面图片列表(伪造数据)
     *
     * @param listener
     */
    public static void getImageStoryArrayMyData(final ToutiaoGetImageStoryArrayListener listener) {
        Gson gson = MyApplication.getGson();
        try {
            JSONObject jsonObject = new JSONObject(MyTestUtil.getAImageStoryListJson());
            LogUtil.i("json=" + jsonObject.toString());
            String message = jsonObject.getString("message");
            if (message.equals("success")) {
                LogUtil.i("message=success");
                ToutiaoImageStoryArrayBean toutiaoImageStoryArrayBean = gson.fromJson(jsonObject.toString(), ToutiaoImageStoryArrayBean.class);

                for (int i = 0; i < toutiaoImageStoryArrayBean.getData().size(); i++) {
                    LogUtil.i("toutiaoImageStoryArrayBean i=" + i + "  " + toutiaoImageStoryArrayBean.getData().get(i).getTitle());
                }

                listener.onResult(toutiaoImageStoryArrayBean.getData());
            } else {
                listener.onError(null);
            }
        } catch (Exception ex) {
            listener.onError("数据解析错误！");
            ex.printStackTrace();
        }
    }

    /**
     * 获取图片故事的封面图片列表(使用UrlConnection获取)
     *
     * @param listener
     */
    public static void getImageStoryArrayUseConn(final ToutiaoGetImageStoryArrayListener listener) {
        Random random = new Random();
        int behot_time = 1494468928 + random.nextInt(99999);
        final String url = "http://www.toutiao.com/api/pc/feed/?category=gallery_detail&max_behot_time=" + behot_time + "&as=A1550911339D015&cp=5913FDA05125BE1";
        LogUtil.i("url=" + url);
        final Gson gson = MyApplication.getGson();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = NetUtils.get(url);
                LogUtil.i("data=" + data);
                JSONObject jsonObject = new JSONObject();
                LogUtil.i("json=" + jsonObject.toString());
                try {
                    String message = jsonObject.getString("message");
                    if (message.equals("success")) {
                        LogUtil.i("message=success");
                        ToutiaoImageStoryArrayBean toutiaoImageStoryArrayBean = gson.fromJson(jsonObject.toString(), ToutiaoImageStoryArrayBean.class);

                        for (int i = 0; i < toutiaoImageStoryArrayBean.getData().size(); i++) {
                            LogUtil.i("toutiaoImageStoryArrayBean i=" + i + "  " + toutiaoImageStoryArrayBean.getData().get(i).getTitle());
                        }

                        listener.onResult(toutiaoImageStoryArrayBean.getData());
                    } else {
                        listener.onError(null);
                    }
                } catch (Exception e) {
                    LogUtil.i("数据解析错误！");
                    e.printStackTrace();
                }

            }
        }).start();

    }


    /**
     * 通过图片列表中的groupId继续获取这一套图片的详细信息
     *
     * @param groupId
     * @param listener
     */
    public static void getImageStoryGallery(String groupId, final ToutiaoGetImageStoryGalleryListener listener) {
        String url = "http://a3.pstatp.com/article/full/16/1/" + groupId + "/6407645878584082946/1/0/?iid=10109443249&device_id=36240811078&ac=wifi&channel=tengxun&aid=13&app_name=news_article&version_code=612&version_name=6.1.2&device_platform=android&ab_version=120430%2C124684%2C123181%2C112577%2C125848%2C125887%2C115758%2C114037%2C122834%2C113607%2C123190%2C125863%2C123727%2C114105%2C125698%2C113608%2C125451%2C125720%2C125503%2C125174%2C118016%2C104320%2C123178%2C121512%2C112578%2C124050%2C125393%2C125907%2C122948%2C123123%2C31207%2C125872%2C121005%2C125528%2C114338%2C123717&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_feature=102749%2C94563&abflag=3&ssmix=a&device_type=A0001&device_brand=Sony&language=zh&os_api=17&os_version=4.2.2&uuid=864895026212660&openudid=40393a9a523ac06a&manifest_version_code=612&resolution=1080*1920&dpi=320&update_version_code=6124&_rticket=1494468882034";
        LogUtil.i("url=" + url);
        final String filepath=getAGoodCachePathyUrl(url,"image_story_"+groupId+"_");
        final Gson gson = MyApplication.getGson();
        if(!NetworkUtil.isNewworkAvailable(MyApplication.getInstance().getApplicationContext())){
            try {
                Type typeToken = new TypeToken<ArrayList<ToutiaoGalleryBean>>() {}.getType();
                List<ToutiaoGalleryBean> toutiaoGalleryBeanList = gson.fromJson(CacheUtil.readFile(filepath).toString(), typeToken);
                listener.onResult(toutiaoGalleryBeanList);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            RequestQueue requestQueue = MyApplication.getRequestQueue();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    LogUtil.showlongLog("json=" + jsonObject.toString());
                    try {
                        String message = jsonObject.getString("message");
                        if (message.equals("success")) {
                            JSONObject dataJsonObject = jsonObject.getJSONObject("data");
                            JSONArray galleryListJsonObject = dataJsonObject.getJSONArray("gallery");
                            CacheUtil.saveFile(galleryListJsonObject.toString(),filepath);
                            Type typeToken = new TypeToken<ArrayList<ToutiaoGalleryBean>>() {
                            }.getType();
                            List<ToutiaoGalleryBean> toutiaoGalleryBeanList = gson.fromJson(galleryListJsonObject.toString(), typeToken);
                            for (int i = 0; i < toutiaoGalleryBeanList.size(); i++) {
                                LogUtil.i("imageTitle=" + toutiaoGalleryBeanList.get(i).getSub_abstract());
                                LogUtil.i("imageurl=" + toutiaoGalleryBeanList.get(i).getSub_image().getUrl());
                            }
                            listener.onResult(toutiaoGalleryBeanList);
                        } else {
                            LogUtil.i("data=null");
                            listener.onError(null);
                        }
                    } catch (Exception ex) {
                        listener.onError("数据解析错误！");
                        ex.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    listener.onError("数据返回错误！");

                }
            });
            requestQueue.add(jsonObjectRequest);
        }

    }



    /**
     * 获取图片故事的相关图片故事
     *
     * @param groupId
     * @param listener
     */
    public static void getImageStoryRelatedGallery(String groupId, final ToutiaoGetRelatedImageStoryArrayListener listener) {
        String url = "http://is.snssdk.com/2/article/information/v21/?group_id=" + groupId + "&item_id=6410690021409423873&aggr_type=1&context=1&from=click_related&article_page=2&iid=10109443249&device_id=36240811078&ac=wifi&channel=tengxun&aid=13&app_name=news_article&version_code=612&version_name=6.1.2&device_platform=android&ab_version=120430%2C127611%2C126061%2C125848%2C115758%2C114037%2C122834%2C126067%2C125863%2C123727%2C114105%2C127189%2C127128%2C126071%2C127719%2C125503%2C127044%2C125174%2C118016%2C127332%2C126057%2C127025%2C121512%2C128478%2C128608%2C126272%2C122948%2C123123%2C31207%2C128166%2C128397%2C125528%2C126019%2C114338%2C123717%2C127758&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_feature=102749%2C94563&abflag=3&ssmix=a&device_type=A0001&device_brand=Sony&language=zh&os_api=17&os_version=4.2.2&uuid=864895026212660&openudid=40393a9a523ac06a&manifest_version_code=612&resolution=1080*1920&dpi=320&update_version_code=6124&_rticket=1495087956803";
        LogUtil.i("url=" + url);
        final Gson gson = MyApplication.getGson();
        final String filepath=getAGoodCachePathyUrl(url,"related_gallery"+groupId+"_");
        if(!NetworkUtil.isNewworkAvailable(MyApplication.getInstance().getApplicationContext())){
            try {
                ToutiaoRelatedGalleryArrayBean toutiaoRelatedGalleryArrayBean = gson.fromJson(CacheUtil.readFile(filepath).toString(), ToutiaoRelatedGalleryArrayBean.class);
                for (int i = 0; i < toutiaoRelatedGalleryArrayBean.getRelatedGalleryList().size(); i++) {
                    LogUtil.i("galleryTitle=" + toutiaoRelatedGalleryArrayBean.getRelatedGalleryList().get(i).getTitle());
                }
                listener.onResult(toutiaoRelatedGalleryArrayBean.getRelatedGalleryList());
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            RequestQueue requestQueue = MyApplication.getRequestQueue();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    LogUtil.showlongLog("json=" + jsonObject.toString());
                    try {
                        String message = jsonObject.getString("message");
                        if (message.equals("success")) {
                            JSONObject data = jsonObject.getJSONObject("data");
                            String relatedGalleryStr = data.getString("related_gallery");
                            relatedGalleryStr = "{\"related_gallery\":" + relatedGalleryStr + "}";
                            CacheUtil.saveFile(relatedGalleryStr.toString(),filepath);
                            ToutiaoRelatedGalleryArrayBean toutiaoRelatedGalleryArrayBean = gson.fromJson(relatedGalleryStr, ToutiaoRelatedGalleryArrayBean.class);
                            for (int i = 0; i < toutiaoRelatedGalleryArrayBean.getRelatedGalleryList().size(); i++) {
                                LogUtil.i("galleryTitle=" + toutiaoRelatedGalleryArrayBean.getRelatedGalleryList().get(i).getTitle());
                            }
                            listener.onResult(toutiaoRelatedGalleryArrayBean.getRelatedGalleryList());
                        } else {
                            LogUtil.i("data=null");
                            listener.onError(null);
                        }
                    } catch (Exception ex) {
                        listener.onError("数据解析错误！");
                        ex.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    LogUtil.i("数据返回错误！");
                    listener.onError("数据返回错误！");
                }
            });
            requestQueue.add(jsonObjectRequest);
        }


    }


    /**
     * 根据获取数据Url生成一个合适的数据缓存名称
     * @param url
     * @return
     */
    private static String getAGoodCachePathyUrl(String url,String sign){
        String filepath;
//        if(url.contains("/")){
//            filepath=url.substring(url.lastIndexOf("/")+1,url.length());
//        }else{
//            filepath=url;
//        }
//        if(filepath.length()>30){
//            filepath=filepath.substring(filepath.length()-30,filepath.length());
//        }
        filepath=sign+ MD5Util.stringToMD5(url);
        return filepath;
    }

    /**
     * 根据传入的新闻类型获取一些新闻,通过isGetNewData决定取缓存数据，还是重新获取网络数据
     * @param context
     * @param newsType
     * @param dataFlag
     * @param isGetNewData
     * @param listener
     */
    public static void getSomeNews(final Context context, String newsType, int dataFlag, boolean isGetNewData , final ToutiaoGetSomeNewsListener listener) {
        final String url = "http://is.snssdk.com/api/news/feed/v53/?category=" + newsType + "&concern_id=6215497899594222081&refer=1&count=5&last_refresh_sub_entrance_interval=1495004536&loc_mode=5&tt_from=enter_auto&lac=4527&cid=28883&cp=569a11bef9578q1&iid=10109443249&device_id=36240811078&ac=wifi&channel=tengxun&aid=13&app_name=news_article&version_code=612&version_name=6.1.2&device_platform=android&ab_version=120430%2C127611%2C126061%2C125848%2C115758%2C114037%2C122834%2C126067%2C125863%2C123727%2C114105%2C127189%2C127128%2C127585%2C126071%2C127719%2C125503%2C127044%2C125174%2C118016%2C127332%2C104320%2C126057%2C127025%2C121512%2C126524%2C126879%2C126272%2C122948%2C123123%2C31207%2C128166%2C125528%2C126019%2C114338%2C123717%2C127758&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_feature=102749%2C94563&abflag=3&ssmix=a&device_type=A0001&device_brand=Sony&language=zh&os_api=17&os_version=4.2.2&uuid=864895026212660&openudid=40393a9a523ac06a&manifest_version_code=612&resolution=1080*1920&dpi=320&update_version_code=6124&_rticket=1495004536331";
        LogUtil.i("url=" + url);
        final String cacheFilepath=getAGoodCachePathyUrl(url,newsType+"_"+dataFlag+"_");
        final Gson gson = MyApplication.getGson();
        if(isGetNewData){
            getSomeNewsOfNet(url,gson,isGetNewData,listener,cacheFilepath);
        }else{
            try {
                String dataStr= CacheUtil.readFile(cacheFilepath).toString();
                if(!dataStr.equals(CacheUtil.ERROR)){
                    convertSomeNewsJson(dataStr,gson,listener,cacheFilepath,isGetNewData,true);
                }else{
                    getSomeNewsOfNet(url,gson,isGetNewData,listener,cacheFilepath);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


    }

    /**
     * 获取网络新闻
     * @param url
     * @param gson
     * @param listener
     * @param cacheFilepath
     */
    private static void getSomeNewsOfNet(String url,final Gson gson,final boolean getNetData,final ToutiaoGetSomeNewsListener listener,final String cacheFilepath){
        RequestQueue requestQueue = MyApplication.getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
//                LogUtil.i("json=" + jsonObject.toString());
//                LogUtil.showlongLog("long json=" + jsonObject.toString());
                try {
                    String dataStr = jsonObject.getString("data");
                    LogUtil.i("data=success");
                    convertSomeNewsJson(dataStr,gson,listener,cacheFilepath,getNetData,false);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LogUtil.i("数据返回错误！");
                LogUtil.i("volleyError.toString()=" + volleyError.toString());
                listener.onError("数据返回错误！");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * 解析新闻内容
     * @param dataStr
     * @param gson
     * @param listener
     * @param cacheFilepath
     */
    public static  void convertSomeNewsJson(String dataStr,Gson gson,ToutiaoGetSomeNewsListener listener,String cacheFilepath,boolean getNetData,boolean isCacheData){
        List<TouTiaoNewsBean> datalist = new ArrayList<>();
        try {
//            CacheUtil.saveFile(dataStr,cacheFilepath);
//            LogUtil.i("saveFile=" + cacheFilepath);
            dataStr = "{\"data\":" + dataStr + "}";
//            LogUtil.showlongLog("dataStr="+dataStr);
            ToutiaoNewsArrayBean toutiaoNewsArrayBean = gson.fromJson(dataStr, ToutiaoNewsArrayBean.class);
            for (int i = 0; i < toutiaoNewsArrayBean.getData().size(); i++) {
                String content=toutiaoNewsArrayBean.getData().get(i).getContent();
                //content=decodeUnicode(content);//转换编码之后需要去掉“”，不转的话解析之后的数据为空，好纠结啊。。
                if(content.contains("action_extra")){
                    String tempStr=content.substring(content.indexOf("action_extra"),content.indexOf("action_extra")+50);
                    String tempStr2=tempStr.replace("extra\": \"{\"channel","extra\":{\"channel");
                    tempStr2=tempStr2.replace("}\", \"","}, \"");
                    content=content.replace(tempStr,tempStr2);
                }
                if(content.contains("log_extra")){
                    content=content.replace("log_extra\": \"{\"rit","log_extra\": {\"rit");
                    content=content.replace("}\", \"log_pb","}, \"log_pb");
                    content=content.replace("}\", \"phone_number","}, \"phone_number");
                }

                if(content.contains("\"user_auth_info\": \"\", ")){
                    content=content.replace("\"user_auth_info\": \"\", ","\"user_auth_info\":{}, ");
                }

                if(content.contains("user_auth_info\": \"{\"auth_type")){
                    content=content.replace("user_auth_info\": \"{\"auth_type","user_auth_info\": {\"auth_type");
                    content=content.replace("}\", \"user_verified","}, \"user_verified");
                }

                TouTiaoNewsBean touTiaoNewsBean = gson.fromJson(content, TouTiaoNewsBean.class);
                String title = touTiaoNewsBean.getTitle();
                LogUtil.i("title=" + title);
                LogUtil.showlongLog("content+"+content);
                datalist.add(touTiaoNewsBean);
            }

            listener.onResult(datalist,getNetData,isCacheData);
        } catch (Exception ex) {
            listener.onError("数据解析错误！");
            listener.onResult(datalist,getNetData,isCacheData);
            ex.printStackTrace();
        }
    }


    /**
     * 将Unicode编码转化成正常文字
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {

        char aChar;

        int len = theString.length();

        StringBuffer outBuffer = new StringBuffer(len);

        for (int x = 0; x < len;) {

            aChar = theString.charAt(x++);

            if (aChar == '\\') {

                aChar = theString.charAt(x++);

                if (aChar == 'u') {

                    // Read the xxxx

                    int value = 0;

                    for (int i = 0; i < 4; i++) {

                        aChar = theString.charAt(x++);

                        switch (aChar) {

                            case '0':

                            case '1':

                            case '2':

                            case '3':

                            case '4':

                            case '5':

                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';

                    else if (aChar == 'n')

                        aChar = '\n';

                    else if (aChar == 'f')

                        aChar = '\f';

                    outBuffer.append(aChar);

                }

            } else

                outBuffer.append(aChar);

        }

        return outBuffer.toString();

    }

}
