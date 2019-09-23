package com.yubin.news.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.yubin.news.R;

/**
 * Created by YUBIN on 2017/5/12.
 */

public class CustomTopTitle extends LinearLayout{
    private View view;
    public CustomTopTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.custom_top_title,this);
    }
}
