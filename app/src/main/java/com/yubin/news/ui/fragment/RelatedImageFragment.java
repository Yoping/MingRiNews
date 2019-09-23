package com.yubin.news.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.http.toutiaoApi.ToutiaoApiManager;
import com.yubin.news.http.toutiaoApi.ToutiaoGetRelatedImageStoryArrayListener;
import com.yubin.news.model.toutiaoApi.ToutiaoRelatedGalleryBean;
import com.yubin.news.ui.adapter.RelatedImageFragmentRecycleViewAdapter;
import com.yubin.news.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUBIN on 2017/5/18.
 */

public class RelatedImageFragment extends Fragment {

    private View rootview;
    private RecyclerView recyclerView;
    private RelatedImageFragmentRecycleViewAdapter adapter;
    private List<ToutiaoRelatedGalleryBean> datalist=new ArrayList<>();
    private String imageGroupId="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_related_image,null);
        Bundle bundle=getArguments();
        if(!TextUtils.isEmpty(bundle.getString(Constants.TOUTIAO_IMAGE_GROUP_ID,""))){
            imageGroupId=bundle.getString(Constants.TOUTIAO_IMAGE_GROUP_ID);
            LogUtil.i("imageGroupId="+imageGroupId);
        }
        initview();
        getdata();
        return rootview;
    }

    /**
     * 初始化界面
     */
    private void initview(){
        recyclerView=(RecyclerView)rootview.findViewById(R.id.recycler_view_f_related_image);
        adapter=new RelatedImageFragmentRecycleViewAdapter(getActivity(),datalist);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
    }

    /**
     * 获取数据
     */
    private void getdata(){
        ToutiaoApiManager.getImageStoryRelatedGallery(imageGroupId, new ToutiaoGetRelatedImageStoryArrayListener() {
            @Override
            public void onResult(List<ToutiaoRelatedGalleryBean> imageStoryList) {
                adapter.setData(imageStoryList);
            }

            @Override
            public void onError(String errorInfo) {

            }
        });
    }

}
