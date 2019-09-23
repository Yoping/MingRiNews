package com.yubin.news.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yubin.news.R;
import com.yubin.news.http.toutiaoApi.ToutiaoApiManager;
import com.yubin.news.http.toutiaoApi.ToutiaoGetImageStoryArrayListener;
import com.yubin.news.model.toutiaoApi.ToutiaoImageStoryBean;
import com.yubin.news.ui.adapter.ImageFragmentRecyclerViewAdapter;
import com.yubin.news.ui.customview.DividerGridItemDecoration;
import com.yubin.news.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by YUBIN on 2017/4/1.
 */

public class ImageFragment extends Fragment {

    private View view;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private ImageFragmentRecyclerViewAdapter adapter;
    private List<ToutiaoImageStoryBean> datalist=new ArrayList<>();
    private Handler handler=new Handler();

    private int num=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_image,null);
        initview();
        return view;
    }

    private void initview(){
        pullLoadMoreRecyclerView=(PullLoadMoreRecyclerView)view.findViewById(R.id.recyclerview_f_image);
        adapter=new ImageFragmentRecyclerViewAdapter(getActivity(),datalist);
        pullLoadMoreRecyclerView.setAdapter(adapter);
        pullLoadMoreRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        pullLoadMoreRecyclerView.setGridLayout(2);
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datalist.clear();
                        adapter.notifyDataSetChanged();
                        getData();
                        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                },1000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
//                        pullLoadMoreRecyclerView.refresh();
                        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                },1000);
            }
        });
        getData();

    }

    private void getData(){
        ToutiaoApiManager.getImageStoryArrayMyData(new ToutiaoGetImageStoryArrayListener() {
            @Override
            public void onResult(List<ToutiaoImageStoryBean> imageStoryList) {
                for (int i = 0; i < imageStoryList.size(); i++) {
                    datalist.add(imageStoryList.get(i));
                }
                adapter.setData(datalist);
            }

            @Override
            public void onError(String errorInfo) {

            }
        });

    }
}
