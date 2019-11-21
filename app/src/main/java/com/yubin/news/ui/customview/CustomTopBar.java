package com.yubin.news.ui.customview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yubin.news.R;

import androidx.annotation.Nullable;

/**
 * @author:YUBIN
 * @create at:2018/7/9.
 * @description: 自定义titlebar
 */

public class CustomTopBar extends LinearLayout {

    private View rootview;
    private View layoutBg;
    private ImageView ivLeftIcon;
    private ImageView ivLeftIconEnd;
    private ImageView ivRightIcon;
    private ImageView ivRightIconEnd;
    private TextView tvTitle;

    public CustomTopBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        rootview= LayoutInflater.from(context).inflate(R.layout.custom_top_bar,this);
        layoutBg=rootview.findViewById(R.id.layout_custom_top_title);
        ivLeftIcon=rootview.findViewById(R.id.iv_custom_top_title_left_icon);
        ivLeftIconEnd=rootview.findViewById(R.id.iv_custom_top_title_left_icon_end);
        ivRightIcon=rootview.findViewById(R.id.iv_custom_top_title_right_icon);
        ivRightIconEnd=rootview.findViewById(R.id.iv_custom_top_title_right_icon_end);
        tvTitle=rootview.findViewById(R.id.tv_custom_top_title_text);
    }

    public View getRootView(){
        return rootview;
    }

    public View getLayoutBg(){
        return layoutBg;
    }

    public ImageView getLeftIcon(){
        ivLeftIcon.setVisibility(VISIBLE);
        return ivLeftIcon;
    }
    public ImageView getLeftIconEnd(){
        ivLeftIconEnd.setVisibility(VISIBLE);
        return ivLeftIconEnd;
    }

    public ImageView getRightIcon(){
        ivRightIcon.setVisibility(VISIBLE);
        return ivRightIcon;
    }
    public ImageView getRightIconEnd(){
        ivRightIconEnd.setVisibility(VISIBLE);
        return ivRightIconEnd;
    }

    public TextView getTitle(){
        tvTitle.setBackgroundColor(Color.argb(0,0,0,0));
        return tvTitle;
    }

    public void setTitleImage(){
//        tvTitle.setBackgroundResource(R.mipmap.etcj);
    }


}
