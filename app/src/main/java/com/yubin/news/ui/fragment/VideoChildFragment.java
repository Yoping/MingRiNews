package com.yubin.news.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.http.youkuApi.YoukuApiManager;
import com.yubin.news.http.youkuApi.YoukuVideoArrayListener;
import com.yubin.news.model.youkuApi.YoukuVideoBean;
import com.yubin.news.ui.adapter.VideoChildFragmentRecyclerViewAdapter;
import com.yubin.news.ui.customview.DividerItemDecoration;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.WorkerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUBIN on 2017/4/1.
 */

public class VideoChildFragment extends Fragment {

    private View view;
    private PullLoadMoreRecyclerView recyclerView;
    private VideoChildFragmentRecyclerViewAdapter adapter;
    private List<YoukuVideoBean> datalist = new ArrayList<>();
    private int videoNum = 0;
    private int pageNum=1;
    private int getVideoNumEveyTime=15;
    private Handler handler = new Handler();

    public static boolean isGetApiData=true;
    private String category="电影";

    private boolean hasGetData=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_child, null);
        getArgs();
        initview();
        setListener();
        if(!hasGetData){
            getData();
        }else{
            recoverData();
        }

        return view;
    }

    /**
     * 获取父控件传递过来的相关参数
     */
    private void getArgs(){
        if(VideoFragment.isGetNetData){
            Bundle bundle=getArguments();
            category=bundle.getString(Constants.YOUKU_VIDEO_CATEGORY);
        }

    }


    /**
     * 获取网络/测试数据
     */
    private void getData(){
        if(isGetApiData){
            getNetData();
        }else{
            getTestData();
        }
    }

    private void recoverData(){

    }

    /**
     * 初始化界面
     */
    private void initview() {
        recyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.recyclerview_f_video_child);
        adapter = new VideoChildFragmentRecyclerViewAdapter(getActivity(), datalist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLinearLayout();
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 设置监听
     */
    private void setListener() {

        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

                datalist.clear();
                videoNum = 0;
                pageNum=1;
                getData();
//                recyclerView.setPullLoadMoreCompleted();

//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        datalist.clear();
//                        videoNum = 0;
//                        pageNum=1;
//                        getData();
//                        recyclerView.setPullLoadMoreCompleted();
//                    }
//                }, 2000);


            }

            @Override
            public void onLoadMore() {


                pageNum++;
                getData();
//                recyclerView.setPullLoadMoreCompleted();

//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        pageNum++;
//                        getData();
//                        recyclerView.setPullLoadMoreCompleted();
//                    }
//                }, 3000);

            }
        });

    }

    /**
     * 获取网络数据（优酷）
     */
    public void getNetData() {

        YoukuApiManager.getCategoryVideoArray(category, YoukuApiManager.periodOfCurrent, YoukuApiManager.OrderBy.view_count, pageNum, getVideoNumEveyTime, new YoukuVideoArrayListener() {
            @Override
            public void onResult(List<YoukuVideoBean> videoList) {
                for(int i=0;i<videoList.size();i++){
                    datalist.add(videoList.get(i));
                }
                adapter.setData(datalist);
                recyclerView.setPullLoadMoreCompleted();
            }

            @Override
            public void onError(String errorInfo) {

            }
        });
    }


    /**
     * 得到测试数据
     */
    private void getTestData() {
        for (int i = videoNum; i < videoNum + 10; i++) {
            LogUtil.i("addVideo  " + i);
            YoukuVideoBean youkuVideoBean = new YoukuVideoBean();
            youkuVideoBean.setTitle(WorkerUtil.getYuleMessage());
            datalist.add(youkuVideoBean);
        }
        videoNum = datalist.size();
        adapter.setData(datalist);
    }



}
