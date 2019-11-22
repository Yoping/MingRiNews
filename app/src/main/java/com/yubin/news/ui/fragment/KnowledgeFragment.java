package com.yubin.news.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.yubin.news.R;
import com.yubin.news.http.retrofit.RetrofitUtil;
import com.yubin.news.model.palyAndroidApi.DataBean;
import com.yubin.news.model.palyAndroidApi.HttpCode;
import com.yubin.news.model.palyAndroidApi.HttpCodeUtil;
import com.yubin.news.model.palyAndroidApi.NewsTermBean;
import com.yubin.news.model.palyAndroidApi.NewsTermListService;
import com.yubin.news.ui.adapter.KnowledgeTablayoutAdapter;
import com.yubin.news.ui.adapter.TablayoutFragmentStatePagerAdapter;
import com.yubin.news.ui.customview.CustomTopBar;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.TabLayoutUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class KnowledgeFragment extends Fragment {
    private String Tag = "NewsFragment";

    private View rootView;
    private KnowledgeTablayoutAdapter tablayoutAdapter;
    @BindView(R.id.custom_top_bar_f_knowledge)
    CustomTopBar customTopBar;
    @BindView(R.id.tablayout_f_knowledge)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_f_knowledge)
    ViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<NewsTermBean> newsTermBeanList = new ArrayList<>();

    public static KnowledgeFragment newInstance() {
        Bundle args = new Bundle();
        KnowledgeFragment fragment = new KnowledgeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_knowledge, null);
        ButterKnife.bind(this, rootView);
        setListener();
        getData();
        return rootView;
    }


    private void setListener(){
        customTopBar.getLeftIconEnd().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void initLocalview() {
        String[] titleList = {"秦时明月", "百步飞剑", "夜尽天明", "诸子百家", "沧海横流", "亡秦必楚", "天行九歌"};
        for (int i = 0; i < titleList.length; i++) {
            NewsTermBean newsTermBean = new NewsTermBean();
            newsTermBean.setName(titleList[i]);
            newsTermBeanList.add(newsTermBean);
        }
        initTitleAndViewpager(newsTermBeanList);
    }

    private void getData() {
        Retrofit retrofit=RetrofitUtil.getInstance();
        NewsTermListService request = retrofit.create(NewsTermListService.class);
        Observable<DataBean<List<NewsTermBean>>> observable = request.getCall();
        observable.observeOn(AndroidSchedulers.mainThread())//在主线程中观察（回调线程）
                .subscribeOn(Schedulers.io())//订阅IO线程中事件（执行线程）
                .subscribe(new Observer<DataBean<List<NewsTermBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.debug(Tag, "onSubscribe");
                    }

                    @Override
                    public void onNext(DataBean<List<NewsTermBean>> dataBean) {
                        LogUtil.debug(Tag, "onNext");
                        if (dataBean.getErrorCode() == HttpCode.GET_MESSAGE_SUCCESS) {
                            if (dataBean.getData() != null) {
                                for (int i = 0; i < dataBean.getData().size(); i++) {
                                    newsTermBeanList.add(dataBean.getData().get(i));
                                }
                                initTitleAndViewpager(newsTermBeanList);
                            }
                        } else {
                            HttpCodeUtil.notOk(dataBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.debug(Tag, "onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.debug(Tag, "onComplete");
                    }
                });
    }


    private void initTitleAndViewpager(List<NewsTermBean> newsTermList) {
        if (newsTermList == null) {
            return;
        }
        if (newsTermList.size() == 0) {
            return;
        }

        for (int i = 0; i < newsTermList.size(); i++) {
            KnowledgeChildFragment newsChildFragment = KnowledgeChildFragment.newInstance(newsTermList.get(i).getId());
            fragmentList.add(newsChildFragment);
        }

        //创建一个Tableyout-Fragment-Viewpager 数据适配器
        tablayoutAdapter = new KnowledgeTablayoutAdapter(getActivity(), getChildFragmentManager(), fragmentList, newsTermList);
        //为Viewpager设置适配器
        viewPager.setAdapter(tablayoutAdapter);
        //为Tablayout设置适配器
        tabLayout.setTabsFromPagerAdapter(tablayoutAdapter);
        //设置Tablayout的宽度与Viewpager相同
        tabLayout.setupWithViewPager(viewPager);
        /**
         * 改变下划线长度，方法还有待考量
         */
        TabLayoutUtil.reflex(tabLayout);

        tabLayout.getTabAt(0).select();
    }
}
