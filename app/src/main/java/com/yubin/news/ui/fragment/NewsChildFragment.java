package com.yubin.news.ui.fragment;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.http.JuheApiManager;
import com.yubin.news.http.JuheGetNewsListener;
import com.yubin.news.model.juheApi.JuheNewsBean;
import com.yubin.news.model.juheApi.JuheNewsType;
import com.yubin.news.ui.adapter.NewsChildFgRycViewAdapter;
import com.yubin.news.ui.customview.DividerItemDecoration;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.MyTestUtil;
import com.yubin.news.utils.WorkerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 聚合头条新闻列表
 * Created by YUBIN on 2017/4/1.
 */

public class NewsChildFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private NewsChildFgRycViewAdapter adapter;
    private List<JuheNewsBean> datalist = new ArrayList<>();
    private int newsNum = 0;
    private Handler handler = new Handler();

    /**
     * 是否获取网络数据开关
     */
    public static boolean isGetApiData=true;
    /**
     * 新闻类型
     */
    private int newsType=0;

    /**
     * 是否已经获取过数据，控制是否调用缓存数据
     */
    private boolean hasGetData=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_child, null);
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
        Bundle bundle=getArguments();
        newsType=bundle.getInt(Constants.KEY_NEWS_TYPE);
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
        recyclerView = view.findViewById(R.id.recyclerView_f_news_child);
        refreshLayout=view.findViewById(R.id.refreshLayout_f_news_child);
        adapter = new NewsChildFgRycViewAdapter(getActivity(), datalist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 设置监听
     */
    private void setListener() {

        adapter.setOnItemClickListener(new NewsChildFgRycViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datalist.clear();
                        newsNum = 0;
                        getData();
                        refreshLayout.finishLoadMore();
                    }
                }, 1000);
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                        refreshLayout.finishRefresh();
                    }
                }, 1000);
            }
        });


//        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
//            @Override
//            public void onRefresh() {
//
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        datalist.clear();
//                        newsNum = 0;
//                        getData();
//                        recyclerView.setPullLoadMoreCompleted();
//                    }
//                }, 1000);
//
//
//            }
//
//            @Override
//            public void onLoadMore() {
//
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                       getData();
//                        recyclerView.setPullLoadMoreCompleted();
//                    }
//                }, 1000);
//
//            }
//        });

    }

    /**
     * 获取网络数据
     */
    public void getNetData() {
        JuheApiManager.getNews(JuheNewsType.getTypePinying(newsType), new JuheGetNewsListener() {
            @Override
            public void onResult(List<JuheNewsBean> newsList) {
                for (int i = 0; i < newsList.size(); i++) {
                    LogUtil.i("title=" + newsList.get(i).getTitle());
                    LogUtil.i(newsList.get(i).getUrl());
                    LogUtil.i("image1=" + newsList.get(i).getThumbnail_pic_s());
                    LogUtil.i("image2=" + newsList.get(i).getThumbnail_pic_s02());
                    LogUtil.i("image3=" + newsList.get(i).getThumbnail_pic_s03());
                    datalist.add(newsList.get(i));
                }
                adapter.notifyDataSetChanged();
                hasGetData=true;
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
        for (int i = newsNum; i < newsNum + 10; i++) {
            LogUtil.i("addNews  " + i);
            JuheNewsBean juheNewsBean = new JuheNewsBean();
            juheNewsBean.setTitle(WorkerUtil.getYuleMessage());
            juheNewsBean.setAuthor_name("中国新闻网");
            juheNewsBean.setDate("2017.5.5 11:" + i);
            juheNewsBean.setUrl(MyTestUtil.getAWebUrl());
            juheNewsBean.setThumbnail_pic_s("hahah");
            juheNewsBean.setThumbnail_pic_s02("shabi");
            juheNewsBean.setThumbnail_pic_s03("naocan");
            datalist.add(juheNewsBean);
        }
        newsNum = datalist.size();
        adapter.notifyDataSetChanged();
    }

//    private void go(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Looper.myLooper();
//                Looper.prepare();
//
//                Looper.loop();
//            }
//        }).start();
//    }


}
