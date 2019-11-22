package com.yubin.news.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.ui.activity.ImageDetailActivity;
import com.yubin.news.utils.GlideUtil;
import com.yubin.news.utils.LogUtil;

/**
 * Created by YUBIN on 2017/5/11.
 */

public class ImageDetailFragment extends Fragment {

    private View view;
    /**
     * 可缩放自定义图片控件
     */
    private ImageView ivImage;
    private TextView tvTitle;
    private TextView tvLocation;
    /**
     * 本组图片组ID
     */
    private String imageGroupId;
    /**
     * 本张图片地址
     */
    private String imageUrl;
    private String title;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image_detail, null);
        ivImage = (ImageView) view.findViewById(R.id.iv_f_image_detail_image);
        tvTitle = (TextView) view.findViewById(R.id.tv_f_image_detail_title);
        tvLocation = (TextView) view.findViewById(R.id.tv_f_image_detail_location);

        getArg();

        ivImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP){
                    showTitle();
                }
                return false;
            }
        });

        return view;
    }


    /**
     *刷新图片标题的显示
     */
    public void refreshTitle(){
        if(tvTitle!=null){
            if(ImageDetailActivity.isTitleShow){
                tvTitle.setVisibility(View.VISIBLE);
            }else{
                tvTitle.setVisibility(View.INVISIBLE);
            }
        }

    }

    /**
     * 控制图片标题的显示与否
     */
    private void showTitle(){
        if(ImageDetailActivity.isTitleShow){
            LogUtil.i("hideTitle");
            tvTitle.setVisibility(View.INVISIBLE);
            ImageDetailActivity.isTitleShow=false;
        }else{
            LogUtil.i("showTitle");
            tvTitle.setVisibility(View.VISIBLE);
            ImageDetailActivity.isTitleShow=true;
        }
    }

    /**
     * 弹出框显示大图
     */
    private void showBigImage() {
        // 一个自定义的布局，作为显示的内容
        View convertView = LayoutInflater.from(getActivity())
                .inflate(R.layout.custom_big_image_view, null);

        ImageView bigImage = (ImageView) convertView
                .findViewById(R.id.big_image);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image1);

        bigImage.setImageBitmap(bitmap);
        final PopupWindow popupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });


        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.pop_window_image_background));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(tvLocation);

    }


    @Nullable
    @Override
    public View getView() {
        return view;
    }

    /**
     * 获取相关参数
     */
    private void getArg() {
        Bundle bundle = getArguments();
        imageGroupId = bundle.getString(Constants.TOUTIAO_IMAGE_GROUP_ID, "");
        imageUrl = bundle.getString(Constants.TOUTIAO_IMAGE_URL, "");
        title = bundle.getString(Constants.TOUTIAO_IMAGE_TITLE, "");
        if ((!TextUtils.isEmpty(imageUrl)) && (!TextUtils.isEmpty(title))) {
            setImage(imageUrl, title);
        }
    }

    private void setImage(String url, String title) {

        /**
         * 调用Glide加载网络图片到可缩放图片控件中
         */
//        Glide.with(getActivity()).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
////                ivImage.setImageBitmap(resource,true);
//                ivImage.setImageBitmap(resource);
//            }
//        });

        GlideUtil.loadBigImage(getActivity(),url,ivImage);

        tvTitle.setText(title);
    }


    @Override
    public void onResume() {
        super.onResume();
        refreshTitle();
    }
}
