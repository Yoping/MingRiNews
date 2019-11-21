package com.yubin.news.base;

import com.yubin.news.ui.fragment.BaseFragment;

public class LazyLoadFragment extends BaseFragment {
    //懒加载标识符
    private boolean isViewCreate=false;
    private boolean isViewVisible=false;
    private boolean isDataInited=false;
}
