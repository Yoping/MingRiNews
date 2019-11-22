package com.yubin.news.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yubin.news.R;
import com.yubin.news.application.BundleKey;
import com.yubin.news.http.retrofit.RetrofitUtil;
import com.yubin.news.model.palyAndroidApi.DataBean;
import com.yubin.news.model.palyAndroidApi.HttpCode;
import com.yubin.news.model.palyAndroidApi.HttpCodeUtil;
import com.yubin.news.model.palyAndroidApi.NewsBean;
import com.yubin.news.model.palyAndroidApi.NewsListBean;
import com.yubin.news.model.palyAndroidApi.NewsListService;
import com.yubin.news.ui.adapter.KnowlegeItemAdapter;
import com.yubin.news.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class KnowledgeChildFragment extends Fragment {
    private String tag="=====NewsChildFragment====";
    private View rootView;
    @BindView(R.id.refreshLayout_f_news_child)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerView_f_news_child)
    RecyclerView recyclerView;

    private KnowlegeItemAdapter adapter;
    private List<NewsBean> datalist = new ArrayList<>();
    private int pageNum = 0;
    private int publicCountId = 0;

    //懒加载标识符
    private boolean isViewCreate=false;
    private boolean isViewVisible=false;
    private boolean isDataInited=false;

    public static KnowledgeChildFragment newInstance(int publicCountId) {
        Bundle args = new Bundle();
        args.putInt(BundleKey.publicCountId, publicCountId);
        KnowledgeChildFragment fragment = new KnowledgeChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.publicCountId = getArguments().getInt(BundleKey.publicCountId);
            tag=tag+"=="+publicCountId;
        }
        LogUtil.debug(tag,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.debug(tag,"onCreateView");
        rootView = inflater.inflate(R.layout.fragment_news_child, null);
        ButterKnife.bind(this, rootView);
        initview();
        isViewCreate=true;
        lazyLoad();
        return rootView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.debug(tag,"setUserVisibleHint:"+isVisibleToUser);
        isViewVisible=isVisibleToUser;
        lazyLoad();
    }

    private void lazyLoad(){
        LogUtil.debug(tag,"lazyLoad");
        if(isViewCreate&&isViewVisible&&(!isDataInited)){
            pageNum=1;
            getData(publicCountId,pageNum,true);
            isDataInited=true;
        }
    }

    private void getData(int publicCountId, int pageNum, boolean isLoadMore) {
        LogUtil.debug(tag,"getData");
        Retrofit retrofit = RetrofitUtil.getInstance();
        NewsListService service = retrofit.create(NewsListService.class);
        Observable<DataBean<NewsListBean>> observable = service.getCall(publicCountId, pageNum);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DataBean<NewsListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataBean<NewsListBean> dataBean) {
                        LogUtil.debug("=======", "onNext");
                        dataBean.printString();
                        if (dataBean.getErrorCode() == HttpCode.GET_MESSAGE_SUCCESS) {
                            if ((dataBean.getData() != null) && (dataBean.getData().getDatas() != null)) {

                                if (dataBean.getData().getDatas().size() == 0) {
                                    if (isLoadMore) {
                                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                                    } else {
                                        smartRefreshLayout.finishRefreshWithNoMoreData();
                                    }
                                }

                                if (!isLoadMore) {
                                    datalist.clear();
                                }
                                for (int i = 0; i < dataBean.getData().getDatas().size(); i++) {
                                    datalist.add(dataBean.getData().getDatas().get(i));
                                }
                                adapter.notifyDataSetChanged();
                                if (isLoadMore) {
                                    smartRefreshLayout.finishLoadMore();
                                } else {
                                    smartRefreshLayout.finishRefresh();
                                }
                            }
                        } else {
                            HttpCodeUtil.notOk(dataBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.debug("=====", "onError");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<NewsBean> getLocalData(List<NewsBean> list) {
        for (int i = 0; i < 10; i++) {
            NewsBean newsBean = new NewsBean();
            list.add(newsBean);
        }
        return list;
    }

    private void initview() {
        adapter = new KnowlegeItemAdapter(getActivity(), datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                getData(publicCountId, pageNum, false);
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                getData(publicCountId, pageNum, true);
            }
        });
    }
}
