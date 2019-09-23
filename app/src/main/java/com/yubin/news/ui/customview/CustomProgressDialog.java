package com.yubin.news.ui.customview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.yubin.news.R;


/**
 * Created by YUBIN  at 17-9-11 上午11:30
 * Last modified at 17-9-11 上午11:21
 */

/**
 * Created by YUBIN on18-2-6 下午3:59
 * Last modified 17-10-7 上午9:58
 *
 */

/**
 * @author http://blog.csdn.net/finddreams
 * @Description:自定义对话框
 */
public class CustomProgressDialog extends Dialog {

    private AnimationDrawable mAnimation;
    private Context mContext;
    private ImageView mImageView;
    private static TextView mLoadingTv;
    private static CustomProgressDialog instance;
    private static String textInfo;

    private CustomProgressDialog(Context context) {
        super(context, R.style.custom_dailog);
        this.mContext = context;
        setCanceledOnTouchOutside(true);
    }


    public static CustomProgressDialog getInstance(Context context){
        instance=new CustomProgressDialog(context);
        return instance;
    }

    public static void showDialog(Context context){
        textInfo="玩命加载中...";
        if(instance!=null){
            instance.dismiss();
            instance.cancel();
            instance=null;
        }
        instance=getInstance(context);
        instance.show();
    }

    public static void showDialog(Context context,String info){
        textInfo=info;
        if(instance!=null){
            instance.dismiss();
            instance.cancel();
            instance=null;
        }
        instance=getInstance(context);
        instance.show();
    }


    public static void dismissDialog(){
        if(instance!=null){
            instance.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);
        mLoadingTv = (TextView) findViewById(R.id.loadingTv);
        mImageView = (ImageView) findViewById(R.id.loadingIv);
        mImageView.setBackgroundResource(R.drawable.loading_ani);
        mLoadingTv.setText(textInfo);
        /**
         * 通过ImageView对象拿到背景显示的AnimationDrawable
         * */
        mAnimation = (AnimationDrawable) mImageView.getBackground();
        /**
         * 为了防止在onCreate方法中只显示第一帧的解决方案之一
         * */
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();

            }
        });
    }

    public void setContent(String str) {
        mLoadingTv.setText(str);
    }

}
