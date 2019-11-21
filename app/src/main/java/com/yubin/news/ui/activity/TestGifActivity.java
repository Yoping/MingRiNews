package com.yubin.news.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.yubin.news.R;
import com.yubin.news.ui.customview.GifView;
import com.yubin.news.ui.customview.PowerImageView;

/**
 * Created by YUBIN on17-5-23 下午4:20
 * Last modified 17-6-8 下午3:36
 *
 */

public class TestGifActivity extends AppCompatActivity {

    private GifView gifView;
    private boolean isGifViewPaused=true;
    private PowerImageView powerImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gif);
        gifView=(GifView)findViewById(R.id.gifview);
        gifView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGifViewPaused){
                    gifView.setPaused(false);
                    isGifViewPaused=false;
                }else{
                    gifView.setPaused(true);
                    isGifViewPaused=true;
                }

            }
        });
        powerImageView=(PowerImageView)findViewById(R.id.powerimage);
        gifView.setMovieResource(R.raw.loading);
//        powerImageView.setImageResource(R.raw.loading);

    }
}
