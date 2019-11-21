package com.yubin.news.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by YUBIN on17-5-24 下午2:19
 * Last modified 17-6-15 下午12:07
 *
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//        Intent intent = new Intent(SplashActivity.this, OkhttpActivity.class);
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
