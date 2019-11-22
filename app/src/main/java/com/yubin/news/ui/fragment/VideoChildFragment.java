package com.yubin.news.ui.fragment;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.base.LazyLoadFragment;
import com.yubin.news.http.youkuApi.YoukuApiManager;
import com.yubin.news.http.youkuApi.YoukuVideoArrayListener;
import com.yubin.news.model.youkuApi.YoukuVideoBean;
import com.yubin.news.ui.adapter.VideoChildFragmentRecyclerViewAdapter;
import com.yubin.news.ui.customview.CustomProgressDialog;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.WorkerUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUBIN on 2017/4/1.
 */

public class VideoChildFragment extends LazyLoadFragment {

    private View view;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private VideoChildFragmentRecyclerViewAdapter adapter;
    private List<YoukuVideoBean> datalist = new ArrayList<>();
    private int videoNum = 0;
    private int pageNum = 1;
    private int getVideoNumEveyTime = 15;
    private Handler handler = new Handler();

    public static boolean isGetApiData = true;
    private String category = "电影";

    private boolean hasGetData = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video_child, null);
        getArgs();
        initview();
        setListener();
        super.nofityViewCreate();
        return view;
    }

    @Override
    public void getInitData() {
        if (!hasGetData) {
            getData(false, true);
        } else {
            recoverData();
        }
    }

    /**
     * 获取父控件传递过来的相关参数
     */
    private void getArgs() {
        if (VideoFragment.isGetNetData) {
            Bundle bundle = getArguments();
            category = bundle.getString(Constants.YOUKU_VIDEO_CATEGORY);
        }

    }


    /**
     * 获取网络/测试数据
     */
    private void getData(boolean isLoadMore, boolean needProgDialog) {
        if (isGetApiData) {
            getNetData(isLoadMore, needProgDialog);
        } else {
            getTestData();
        }
    }

    private void recoverData() {

    }

    /**
     * 初始化界面
     */
    private void initview() {
        recyclerView = view.findViewById(R.id.recyclerView_f_video_child);
        refreshLayout = view.findViewById(R.id.refreshLayout_f_video_child);
        adapter = new VideoChildFragmentRecyclerViewAdapter(getActivity(), datalist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 设置监听
     */
    private void setListener() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                datalist.clear();
                videoNum = 0;
                pageNum = 1;
                getData(false, false);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                pageNum++;
                getData(true, false);
            }
        });


    }

    /**
     * 获取网络数据（优酷）
     */
    public void getNetData(boolean isLoadMore, boolean needProgDialog) {

        if (needProgDialog) {
            CustomProgressDialog.showDialog(getContext());
        }
        YoukuApiManager.getCategoryVideoArray(category, YoukuApiManager.periodOfCurrent, YoukuApiManager.OrderBy.view_count, pageNum, getVideoNumEveyTime, new YoukuVideoArrayListener() {
            @Override
            public void onResult(List<YoukuVideoBean> videoList) {

                for (int i = 0; i < videoList.size(); i++) {
                    datalist.add(videoList.get(i));
                }
                adapter.setData(datalist);
                finishedGetData(isLoadMore, needProgDialog);
            }

            @Override
            public void onError(String errorInfo) {
                finishedGetData(isLoadMore, needProgDialog);
            }
        });
    }

    private void finishedGetData(boolean isLoadMore, boolean needProgDialog) {
        if (needProgDialog) {
            CustomProgressDialog.dismissDialog();
        }
        if (!isLoadMore) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
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
