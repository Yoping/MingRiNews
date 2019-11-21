package com.yubin.news.ui.fragment;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.base.LazyLoadFragment;
import com.yubin.news.http.toutiaoApi.ToutiaoApiManager;
import com.yubin.news.http.toutiaoApi.ToutiaoGetSomeNewsListener;
import com.yubin.news.http.toutiaoApi.ToutiaoNewsType;
import com.yubin.news.model.toutiaoApi.TouTiaoNewsBean;
import com.yubin.news.ui.activity.MainActivity;
import com.yubin.news.ui.adapter.NewsChildFragmentRecyclerViewAdapter2;
import com.yubin.news.ui.customview.CustomProgressDialog;
import com.yubin.news.ui.customview.DividerItemDecoration;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.SharedPreferencesUtil;
import com.yubin.news.utils.ToastUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 今日头条的新闻列表
 * Created by YUBIN on 2017/4/1.
 */

public class NewsChildFragment2 extends LazyLoadFragment {
    public String tag = "====newsChildFragment2====";

    private View view;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private NewsChildFragmentRecyclerViewAdapter2 adapter;
    private List<TouTiaoNewsBean> datalist = new LinkedList<>();
    private Handler handler = new Handler();

    /**
     * 新闻类型
     */
    private int newsType = 0;

    /**
     * 将数据添加到什么位置
     * 1.头部
     * 2.尾部
     * 3.未知
     */
    private static final int ADD_DATA_IN_HEAD = 1;
    private static final int ADD_DATA_IN_TAIL = 2;
    private static final int ADD_DATA_IN_UNKONW = 3;

    /**
     * 获取数据的方式
     * 1.网络数据
     * 2.缓存数据
     * 3.未知
     */
    private static final int GET_NET_DATA = 1;
    private static final int GET_CACHE_DATA = 2;
    private static final int GET_UNKNOW_DATA = 3;


    /**
     * 记录本次打开APP时的第几次获取数据，作为缓存变量值
     */
    private int dataKeyOfToday = 0;
    private int biggestCacheKey = 0;
    private boolean isFirstCacheData = true;

    //懒加载标识符
    private boolean isViewCreate = false;
    private boolean isViewVisible = false;
    private boolean isDataInited = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.debug(tag,"onCreateView");
        view = inflater.inflate(R.layout.fragment_news_child2, null);
        getArgs();
        initview();
        setListener();
//        /**
//         * 得到上次关闭APP时缓存的数据数量
//         */
//        biggestCacheKey = SharedPreferencesUtil.getInt(ToutiaoNewsType.getTypeEnglish(newsType), -1);
//        if (biggestCacheKey == (-1)) {
//            isFirstCacheData = true;
//        } else {
//            isFirstCacheData = false;
//        }
        super.nofityViewCreate();
        return view;
    }

    @Override
    public void getInitData() {
        getData(true, true);
    }


    /**
     * 获取父控件传递过来的相关参数
     */
    public void getArgs() {
        Bundle bundle = getArguments();
        newsType = bundle.getInt(Constants.KEY_NEWS_TYPE);
    }


    /**
     * 初始化界面
     */
    public void initview() {
        recyclerView = view.findViewById(R.id.recyclerView_f_news_child2);
        refreshLayout = view.findViewById(R.id.refreshLayout_f_news_child2);
        adapter = new NewsChildFragmentRecyclerViewAdapter2(getActivity(), datalist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        if (datalist.size() == 0) {
            TouTiaoNewsBean temp = new TouTiaoNewsBean();
            temp.setTitle("占位符");
            datalist.add(temp);
        }
    }

    /**
     * 设置监听
     */
    public void setListener() {

        adapter.setOnItemClickListener(new NewsChildFragmentRecyclerViewAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                if (((MainActivity) getActivity()).getNewsFragment() != null) {
                    ((MainActivity) getActivity()).getNewsFragment().hideSearchView();
                }

//                ((NewsFragment) getParentFragment()).hideSearchView();//控制针？
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        ToastUtil.show(getActivity(), "已经是最新的数据！");
                    }
                }, 1000);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                if (((MainActivity) getActivity()).getNewsFragment() != null) {
                    ((MainActivity) getActivity()).getNewsFragment().hideSearchView();
                }
//                ((NewsFragment) getParentFragment()).hideSearchView();//空指针？
                getData(true, false);
            }
        });

    }

    /**
     * 获取数据
     */
    public void getData(boolean getNetData, boolean needProgDialog) {
        final int dataKey;
        if (getNetData) {
            /**
             * 获取网络数据
             */
            dataKeyOfToday++;
            dataKey = dataKeyOfToday;
            if (dataKeyOfToday > biggestCacheKey) {
                biggestCacheKey = dataKeyOfToday;
            }
        } else {
            if (biggestCacheKey > 0) {
                /**
                 * 依次拿最新的缓存数据
                 */
                dataKey = biggestCacheKey;
                biggestCacheKey--;
            } else {
                /**
                 * 缓存数据取完了,获取新的网络数据
                 */
                getNetData = true;
                dataKeyOfToday++;
                dataKey = dataKeyOfToday;
                if (dataKeyOfToday > biggestCacheKey) {
                    biggestCacheKey = dataKeyOfToday;
                }
            }
        }

        if (needProgDialog) {
            CustomProgressDialog.showDialog(getActivity());
        }


        ToutiaoApiManager.getSomeNews(getActivity(), ToutiaoNewsType.getTypeEnglish(newsType), dataKey, getNetData, new ToutiaoGetSomeNewsListener() {
            @Override
            public void onResult(List<TouTiaoNewsBean> newsList, boolean getNetData, boolean isCacheData) {
                CustomProgressDialog.dismissDialog();
                for (int i = 0; i < newsList.size(); i++) {
                    datalist.add(newsList.get(i));

//                    if(getNetData){
//                        datalist.add(0,newsList.get(i));
//                    }else{
//                        /**
//                         * 获取缓存数据时，如果没有缓存，就会去获取去获取网络，
//                         * 这时不管是以哪种方式得到的数据都放到最后
//                         */
//                        datalist.add(newsList.get(i));
//                    }

                }
                refreshLayout.finishLoadMore();
                adapter.setData(datalist);
            }

            @Override
            public void onError(String errorInfo) {
                CustomProgressDialog.dismissDialog();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 记录上次打开APP时该页面缓存了多少次数据
         */
        SharedPreferencesUtil.putInt(ToutiaoNewsType.getTypeEnglish(newsType), dataKeyOfToday);
    }
}
