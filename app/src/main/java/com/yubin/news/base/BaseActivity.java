package com.yubin.news.base;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.githang.statusbar.StatusBarCompat;
import com.yubin.news.R;
import com.yubin.news.application.MyApplication;
import com.yubin.news.application.MyCode;
import com.yubin.news.application.MyError;
import com.yubin.news.base.callback.PermissionListener;
import com.yubin.news.utils.NetworkUtil;
import com.yubin.news.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;

/**
 * @author:YUBIN
 * @create at:2018/7/9.
 * @description:Activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 禁止横屏
         */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /**
         * 设置状态栏为白色，4.4以下无法将状态栏颜色设置为深色，会存在看不清图标的情况
         */
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
        MyApplication.addActivity(this);
//        PushAgent.getInstance(this).onAppStart();
        ButterKnife.bind(this);
        checkNetwork();
        initview();
        setListener();
        beginToWork();
    }

    private void checkNetwork(){
        if(!NetworkUtil.isNewworkAvailable(MyApplication.getContext())){
            ToastUtil.show(this,"网络不给力...");
        }
    }

    public abstract void  initview();
    public abstract void setListener();
    public abstract void beginToWork();

    @Override
    protected void onDestroy() {
        MyApplication.removeActivity(this);
        super.onDestroy();
    }


    private PermissionListener mlistener;

    /**
     * 权限申请
     * @param permissions 待申请的权限集合
     * @param listener  申请结果监听事件
     */
    protected void requestRunTimePermission(String[] permissions, PermissionListener listener){
        this.mlistener = listener;

        //用于存放为授权的权限
        List<String> permissionList = new ArrayList<>();
        //遍历传递过来的权限集合
        for (String permission : permissions) {
            //判断是否已经授权
            if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                //未授权，则加入待授权的权限集合中
                permissionList.add(permission);
            }
        }

        //判断集合
        if (!permissionList.isEmpty()){  //如果集合不为空，则需要去授权

            ActivityCompat.requestPermissions(this,permissionList.toArray(new String[permissionList.size()]), MyCode.REQUEST_CODE_PERMISSION);
        }else{  //为空，则已经全部授权
            listener.onGranted();
        }
    }

    /**
     * 权限申请结果
     * @param requestCode  请求码
     * @param permissions  所有的权限集合
     * @param grantResults 授权结果集合
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MyCode.REQUEST_CODE_PERMISSION:
                if (grantResults.length > 0){
                    //被用户拒绝的权限集合
                    List<String> deniedPermissions = new ArrayList<>();
                    //用户通过的权限集合
                    List<String> grantedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        //获取授权结果，这是一个int类型的值
                        int grantResult = grantResults[i];

                        if (grantResult != PackageManager.PERMISSION_GRANTED){ //用户拒绝授权的权限
                            String permission = permissions[i];
                            deniedPermissions.add(permission);
                        }else{  //用户同意的权限
                            String permission = permissions[i];
                            grantedPermissions.add(permission);
                        }
                    }

                    if (deniedPermissions.isEmpty()){
                        mlistener.onGranted();
                    }else {  //不为空
                        //回调授权成功的接口
                        mlistener.onDenied(deniedPermissions);
                        //回调授权失败的接口
                        mlistener.onGranted(grantedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

    protected boolean useThemestatusBarColor = true;//是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useStatusBarColor = true;//是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    protected void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


    public void showIntentError(){
        ToastUtil.show(this, MyError.paramError);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //友盟统计
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //友盟统计
//        MobclickAgent.onPause(this);
    }
}
