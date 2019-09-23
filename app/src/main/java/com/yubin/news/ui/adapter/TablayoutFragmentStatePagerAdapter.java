package com.yubin.news.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Tablayout-Fragment 数据适配器
 * Created by YUBIN on 2017/5/4.
 */

public class TablayoutFragmentStatePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> titleList;

    public TablayoutFragmentStatePagerAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String> titleList) {
        super(fm);
        this.fragmentList=fragmentList;
        this.titleList=titleList;
    }

    /**
     * 更新数据
     * @param fragmentList
     * @param titleList
     */
    public void setData(List<Fragment> fragmentList,List<String> titleList){
        this.fragmentList=fragmentList;
        this.titleList=titleList;
        notifyDataSetChanged();
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
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
