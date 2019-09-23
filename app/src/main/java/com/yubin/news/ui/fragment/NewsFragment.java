package com.yubin.news.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.http.toutiaoApi.ToutiaoNewsType;
import com.yubin.news.model.juheApi.JuheNewsType;
import com.yubin.news.ui.activity.MainActivity;
import com.yubin.news.ui.adapter.TablayoutFragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment{

    private View view;
    private View searchLayout;
    private View searchLayout2;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> fragmentList;
    private List<String> titleList;

    private TablayoutFragmentStatePagerAdapter tablayoutFragmentStatePagerAdapter;

    private boolean isUseToutiaoData=true;

    private float x=0;
    private float y=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_news,null);
        initview();
        setListeners();
        return view;
    }

    /**
     * 初始化界面
     */
    private void initview(){
        searchLayout=view.findViewById(R.id.layout_f_news_search);
        searchLayout2=view.findViewById(R.id.layout_f_news_search2);
        tabLayout=(TabLayout)view.findViewById(R.id.tablayout_f_news);
        viewPager=(ViewPager)view.findViewById(R.id.viewpager_f_news);

        titleList=new ArrayList<String>();
//        titleList.add("秦时明月");
//        titleList.add("百步飞剑");
//        titleList.add("夜尽天明");
//        titleList.add("诸子百家");
//        titleList.add("万里长城");
//        titleList.add("君临天下");
//        titleList.add("沧海横流");
//        titleList.add("亡秦必楚");
//        titleList.add("天行九歌");
//        titleList.add("火影忍者");

        fragmentList=new ArrayList<Fragment>();

        if(isUseToutiaoData){
            for(int i=0;i<ToutiaoNewsType.TOTAL_TYPE_NUMBER;i++){
                titleList.add(i, ToutiaoNewsType.getTypeChinese(i));
                NewsChildFragment2 fragment=new NewsChildFragment2();
                Bundle bundle=new Bundle();
                bundle.putInt(Constants.KEY_NEWS_TYPE,i);
                fragment.setArguments(bundle);
                fragmentList.add(i,fragment);
            }
        }else{
            for(int i=0;i<JuheNewsType.TOTAL_TYPE_NUMBER;i++){
                titleList.add(i, JuheNewsType.getTypeChinese(i));
                NewsChildFragment fragment=new NewsChildFragment();
                Bundle bundle=new Bundle();
                bundle.putInt(Constants.KEY_NEWS_TYPE,i);
                fragment.setArguments(bundle);
                fragmentList.add(i,fragment);
            }
        }


        //创建一个Tableyout-Fragment-Viewpager 数据适配器
        tablayoutFragmentStatePagerAdapter=new TablayoutFragmentStatePagerAdapter(getFragmentManager(),fragmentList,titleList);

        //为Viewpager设置适配器
        viewPager.setAdapter(tablayoutFragmentStatePagerAdapter);

        //为Tablayout设置适配器
        tabLayout.setTabsFromPagerAdapter(tablayoutFragmentStatePagerAdapter);

        //设置Tablayout的宽度与Viewpager相同
        tabLayout.setupWithViewPager(viewPager);



    }

    private void setListeners(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index=tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /**
         * 通过自定义触控接口得到触摸事件，控制是否隐藏顶部搜索栏
         */
        ((MainActivity)getActivity()).registerMyTouchListenr(new MainActivity.MyTouchListener() {
            @Override
            public void onMyTouchListener(MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x=event.getX();
                        y=event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if((event.getY()-y)>Constants.ErrorOfTouch){//下滑
                            showSearchView();
                        }else if((y-event.getY())>Constants.ErrorOfTouch){//上滑
                            hideSearchView();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        x=event.getX();
                        y=event.getY();
                        break;
                }
            }
        });

    }



    private boolean searchLayoutIsShow=true;

    /**
     * 隐藏搜索栏
     */
    public void hideSearchView(){
        if((searchLayout!=null)&&(searchLayoutIsShow)){
            ObjectAnimator.ofFloat(searchLayout,"translationY",0,-searchLayout2.getHeight()).setDuration(300).start();
            searchLayoutIsShow=false;
        }
    }

    /**
     * 显示搜索栏
     */
    public void showSearchView(){
        if((searchLayout!=null)&&(!searchLayoutIsShow)){
            ObjectAnimator.ofFloat(searchLayout,"translationY",-searchLayout2.getHeight(),0).setDuration(300).start();
            searchLayoutIsShow=true;
        }
    }




}
