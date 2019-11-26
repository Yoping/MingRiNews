package com.yubin.news.ui.fragment;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yubin.news.R;
import com.yubin.news.http.toutiaoApi.ToutiaoApiManager;
import com.yubin.news.http.toutiaoApi.ToutiaoGetImageStoryArrayListener;
import com.yubin.news.model.toutiaoApi.ToutiaoImageStoryBean;
import com.yubin.news.ui.adapter.ImageFgRecyViewAdapter;
import com.yubin.news.ui.customview.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by YUBIN on 2017/4/1.
 */

public class ImageFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private ImageFgRecyViewAdapter adapter;
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
        recyclerView = view.findViewById(R.id.recyclerView_f_image);
        refreshLayout=view.findViewById(R.id.refreshLayout_f_image);
        adapter=new ImageFgRecyViewAdapter(getActivity(),datalist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datalist.clear();
                        adapter.notifyDataSetChanged();
                        getData();
                        refreshLayout.finishRefresh();
                    }
                },1000);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                        refreshLayout.finishLoadMoreWithNoMoreData();
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
