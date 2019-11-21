package com.yubin.news.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yubin.news.ui.fragment.BaseFragment;
import com.yubin.news.utils.LogUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class LazyLoadFragment extends BaseFragment {
    private String tag="====lazyLoadFragment===";
    //懒加载标识符
    private boolean isViewCreate=false;
    private boolean isViewVisible=false;
    private boolean isDataInited=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.debug(tag, "setUserVisibleHint:" + isVisibleToUser);
        isViewVisible = isVisibleToUser;
        lazyLoad();
    }

    public void lazyLoad() {
        LogUtil.debug(tag, "lazyLoad");
        if (isViewCreate && isViewVisible && (!isDataInited)) {
            getInitData();
            isDataInited = true;
        }
    }

    /**
     * 子类onCreateView初始化view之后需要调用这个方法
     */
    public void nofityViewCreate(){
        isViewCreate = true;
        lazyLoad();
    }

    public abstract void getInitData();
}
