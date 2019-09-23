package com.yubin.news.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.ui.activity.MainActivity;

/**
 * Created by YUBIN on 2017/6/9.
 */

public class GuideFragment extends BaseFragment {

    private View rootview;
    private ImageView ivImage;
    private TextView tvGoToApp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_guide,null);
        ivImage=(ImageView)rootview.findViewById(R.id.iv_f_guide_image);
        tvGoToApp=(TextView)rootview.findViewById(R.id.tv_f_guide_go_to_app);
        Bundle bundle=getArguments();
        int imageIndex=bundle.getInt(Constants.GUIDE_PAGE_INDEX,0);
        setImageByIndex(imageIndex);
        if(bundle.getBoolean(Constants.GO_TO_APP,true)){
            tvGoToApp.setVisibility(View.VISIBLE);
        }else{
            tvGoToApp.setVisibility(View.GONE);
        }
        tvGoToApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return  rootview;
    }

    private void setImageByIndex(int index){
        if(index==0){
            ivImage.setImageResource(R.mipmap.image1);
        }else if(index==1){
            ivImage.setImageResource(R.mipmap.image2);
        }else{
            ivImage.setImageResource(R.mipmap.image3);
        }
    }

}
