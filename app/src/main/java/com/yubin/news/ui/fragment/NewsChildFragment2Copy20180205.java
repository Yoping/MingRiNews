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
import com.yubin.news.http.toutiaoApi.ToutiaoApiManager;
import com.yubin.news.http.toutiaoApi.ToutiaoGetSomeNewsListener;
import com.yubin.news.http.toutiaoApi.ToutiaoNewsType;
import com.yubin.news.model.toutiaoApi.TouTiaoNewsBean;
import com.yubin.news.ui.adapter.NewsChildFragmentRecyclerViewAdapter2;
import com.yubin.news.ui.customview.DividerItemDecoration;
import com.yubin.news.utils.SharedPreferencesUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by YUBIN on18-2-5 下午3:15
 * Last modified 18-2-5 下午3:13
 * 之前有做过缓存处理，但是逻辑太复杂了，没有理顺，暂时保留
 */

/**
 * 今日头条的新闻列表
 * Created by YUBIN on 2017/4/1.
 */

public class NewsChildFragment2Copy20180205 extends Fragment {

    private View view;
    private PullLoadMoreRecyclerView recyclerView;
    private NewsChildFragmentRecyclerViewAdapter2 adapter;
    private List<TouTiaoNewsBean> datalist = new LinkedList<>();
    private Handler handler = new Handler();

    /**
     * 新闻类型
     */
    private int newsType=0;

    /**
     * 将数据添加到什么位置
     * 1.头部
     * 2.尾部
     * 3.未知
     */
    private static final int ADD_DATA_IN_HEAD=1;
    private static final int ADD_DATA_IN_TAIL=2;
    private static final int ADD_DATA_IN_UNKONW=3;

    /**
     * 获取数据的方式
     * 1.网络数据
     * 2.缓存数据
     * 3.未知
     */
    private static final int GET_NET_DATA=1;
    private static final int GET_CACHE_DATA=2;
    private static final int GET_UNKNOW_DATA=3;


    /**
     * 记录本次打开APP时的第几次获取数据，作为缓存变量值
     */
    private int dataKeyOfToday=0;
    private int biggestCacheKey=0;
    private boolean isFirstCacheData=true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_child2, null);
        getArgs();
        initview();
        setListener();
        /**
         * 得到上次关闭APP时缓存的数据数量
         */
        biggestCacheKey= SharedPreferencesUtil.getInt(ToutiaoNewsType.getTypeEnglish(newsType),-1);
        if(biggestCacheKey==(-1)){
            isFirstCacheData=true;
        }else{
            isFirstCacheData=false;
        }
        getData(false);
        return view;
    }

    /**
     * 获取父控件传递过来的相关参数
     */
    private void getArgs(){
        Bundle bundle=getArguments();
        newsType=bundle.getInt(Constants.KEY_NEWS_TYPE);
    }


    /**
     * 初始化界面
     */
    private void initview() {
        recyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.recyclerview_f_news_child2);
        adapter = new NewsChildFragmentRecyclerViewAdapter2(getActivity(), datalist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLinearLayout();
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 设置监听
     */
    private void setListener() {

        adapter.setOnItemClickListener(new NewsChildFragmentRecyclerViewAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
//                getData(true);
//                recyclerView.setPullLoadMoreCompleted();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        getNetData(true);
//                        recyclerView.setPullLoadMoreCompleted();
//                    }
//                }, 10);
            }

            @Override
            public void onLoadMore() {
                /**
                 * 第一次打开该页面，没有缓存过数据，每次都获取新的数据
                 */
                if(isFirstCacheData){
                    getData(true);
                }else{
                    getData(false);
                }
                recyclerView.setPullLoadMoreCompleted();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                       getNetData(false);
//                        recyclerView.setPullLoadMoreCompleted();
//                    }
//                }, 10);

            }
        });

    }

    /**
     * 获取数据
     */
    public void getData(boolean getNetData) {
        final int dataKey;
        if(getNetData){
            /**
             * 获取网络数据
             */
            dataKeyOfToday++;
            dataKey=dataKeyOfToday;
            if(dataKeyOfToday>biggestCacheKey){
                biggestCacheKey=dataKeyOfToday;
            }
        }else{
            if(biggestCacheKey>0){
                /**
                 * 依次拿最新的缓存数据
                 */
                dataKey=biggestCacheKey;
                biggestCacheKey--;
            }else{
                /**
                 * 缓存数据取完了,获取新的网络数据
                 */
                getNetData=true;
                dataKeyOfToday++;
                dataKey=dataKeyOfToday;
                if(dataKeyOfToday>biggestCacheKey){
                    biggestCacheKey=dataKeyOfToday;
                }
            }
        }

//        CustomDialog.showProgressDialog(getActivity());
        ToutiaoApiManager.getSomeNews(getActivity(),ToutiaoNewsType.getTypeEnglish(newsType),dataKey, getNetData,new ToutiaoGetSomeNewsListener() {
            @Override
            public void onResult(List<TouTiaoNewsBean> newsList,boolean getNetData,boolean isCacheData) {
//                CustomDialog.hideProgressDialog();
                for(int i=0;i<newsList.size();i++){
                    if(getNetData){
                        datalist.add(0,newsList.get(i));
                    }else{
                        /**
                         * 获取缓存数据时，如果没有缓存，就会去获取去获取网络，
                         * 这时不管是以哪种方式得到的数据都放到最后
                         */
                        datalist.add(newsList.get(i));
                    }

                }
                adapter.setData(datalist);
            }

            @Override
            public void onError(String errorInfo) {

            }
        });


//        JuheApiManager.getNews(JuheNewsType.getTypePinying(newsType), new JuheGetNewsListener() {
//            @Override
//            public void onResult(List<JuheNewsBean> newsList) {
//                for (int i = 0; i < newsList.size(); i++) {
//                    LogUtil.i("title=" + newsList.get(i).getTitle());
//                    LogUtil.i(newsList.get(i).getUrl());
//                    LogUtil.i("image1=" + newsList.get(i).getThumbnail_pic_s());
//                    LogUtil.i("image2=" + newsList.get(i).getThumbnail_pic_s02());
//                    LogUtil.i("image3=" + newsList.get(i).getThumbnail_pic_s03());
//                    datalist.add(newsList.get(i));
//                }
//                adapter.notifyDataSetChanged();
//                hasGetData=true;
//            }
//
//            @Override
//            public void onError(String errorInfo) {
//
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 记录上次打开APP时该页面缓存了多少次数据
         */
        SharedPreferencesUtil.putInt(ToutiaoNewsType.getTypeEnglish(newsType),dataKeyOfToday);
    }
}
