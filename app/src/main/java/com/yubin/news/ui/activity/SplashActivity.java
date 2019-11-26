package com.yubin.news.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.yubin.news.base.BaseActivity;
import com.yubin.news.base.callback.PermissionListener;
import com.yubin.news.utils.NetworkUtil;
import com.yubin.news.utils.ToastUtil;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by YUBIN on17-5-24 下午2:19
 * Last modified 17-6-15 下午12:07
 *
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initview() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void beginToWork() {
        if (!NetworkUtil.isNewworkAvailable(SplashActivity.this)) {
            ToastUtil.show(getApplicationContext(), "网络好像出问题了...");
        }
        requestPermission();
    }

    /**
     * 获取通知推送权限
     */
    private void requestPermission() {
        requestRunTimePermission(new String[]{
                        Manifest.permission.INTERNET,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE}
                , new PermissionListener() {
                    @Override
                    public void onGranted() {
                        /**
                         * 所有权限授权成功
                         */
                        goToMainActivity();

                    }

                    @Override
                    public void onGranted(List<String> grantedPermission) {
                        /**
                         * 授权成功的权限集合
                         */
                        goToMainActivity();

                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        /**
                         * 授权失败的权限集合
                         */
                        goToMainActivity();

                    }
                });
    }

    private void goToMainActivity(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

//        if (SharedPreferencesUtil.getBoolean(Constants.IS_APP_FIRST_OPEN, true)) {
//            SharedPreferencesUtil.putBoolean(Constants.IS_APP_FIRST_OPEN, false);
//            Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
//            startActivity(intent);
//            finish();
//        } else {
//            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }


}
