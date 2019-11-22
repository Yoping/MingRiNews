package com.yubin.news.ui.adapter;

import android.content.Context;

import com.yubin.news.model.palyAndroidApi.NewsTermBean;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Tablayout-Fragment 数据适配器
 * Created by YUBIN on 2017/5/4.
 */

public class KnowledgeTablayoutAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<NewsTermBean> titleList;
    private Context context;

    public KnowledgeTablayoutAdapter(Context context, FragmentManager fm, List<Fragment> fragmentList, List<NewsTermBean> titleList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    /**
     * 更新数据
     *
     * @param fragmentList
     * @param titleList
     */
    public void setData(List<Fragment> fragmentList, List<NewsTermBean> titleList) {
        this.fragmentList = fragmentList;
        this.titleList = titleList;
        notifyDataSetChanged();
    }

    public void setTitleList(List<NewsTermBean> titleList) {
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * 返回当前Tab的名称
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position).getName();
    }

}
