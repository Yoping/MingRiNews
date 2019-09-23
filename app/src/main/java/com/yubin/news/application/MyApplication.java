package com.yubin.news.application;

import android.app.Activity;
import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.youku.cloud.player.YoukuPlayerConfig;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.SharedPreferencesUtil;
import com.yubin.news.utils.WorkerUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by YUBIN on 2017/5/3.
 */

public class MyApplication extends Application {

    public static final String CLIENT_ID_WITH_AD = "bf4edfb8e9421ca6";
    public static final String CLIENT_SECRET_WITH_AD = "8de4913e21bc7da95b86b9584852c9a5";

    public static List<Activity> activityList;
    public static MyApplication instance;

    public MyApplication() {
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        SharedPreferencesUtil.getInstance();
        activityList = new LinkedList<Activity>();
        YoukuPlayerConfig.setLog(true);
        /**设置client_id和client_secret*/
        YoukuPlayerConfig.setClientIdAndSecret(CLIENT_ID_WITH_AD, CLIENT_SECRET_WITH_AD);
        /****sdk初始化*/
//        YoukuPlayerConfig.onInitial((Application) getApplicationContext());
        initVolley();
    }

    private static RequestQueue requestQueue;
    private static Gson gson;

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static Gson getGson() {
        return gson;
    }

    private void initVolley() {
        LogUtil.i("initvolley");
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        gson = new Gson();
    }


    /**================================ about activity ==================================**/
    /**
     * 从链表中移除一个Activity
     *
     * @param act
     */
    public void removeActivity(Activity act) {
        if (!activityList.contains(act))
            activityList.remove(act);
    }

    /**
     * 往链表中添加一个Activity
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (!WorkerUtil
                .isNullOrEmpty(getActivity(activity.getClass().getName()))) {
            activityList.remove(getActivity(activity.getClass().getName()));
        }
        activityList.add(activity);
    }

    /**
     * 通过Activity的名称得到一个Activity
     *
     * @param activityName
     * @return
     */
    public static Activity getActivity(String activityName) {
        for (Activity activity : activityList) {
            if (activityName.equals(activity.getClass().getName())) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 结束所有Activity并退出
     */
    public static void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    /**
     * 结束所有的Activity
     */
    public static void cleanAllActivity() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}
