package com.yubin.news.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.http.youkuApi.YoukuApiManager;
import com.yubin.news.http.youkuApi.YoukuCategoryListener;
import com.yubin.news.model.youkuApi.YoukuCategoryBean;
import com.yubin.news.ui.activity.MainActivity;
import com.yubin.news.ui.adapter.TablayoutFragmentStatePagerAdapter;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.ToastUtil;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUBIN on 2017/4/1.
 */

public class VideoFragment extends Fragment {

    private View rootView;
    private TextView tvSetting;
    private TabLayout tabLayout;
    private TablayoutFragmentStatePagerAdapter adapter;
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    public static boolean isGetNetData = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_video, null);
        initview();
        setListeners();
        return rootView;
    }

    /**
     * 初始化界面
     */
    private void initview() {
        tvSetting = (TextView) rootView.findViewById(R.id.tv_f_video_setting);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tablayout_f_video);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager_f_video);

        tvSetting.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvSetting.setText("\ue642");

        initdata();
        adapter = new TablayoutFragmentStatePagerAdapter(getFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setListeners(){

        tvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetting();
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 初始化Tab数据
     */
    private void initdata() {

        if (isGetNetData) {
            YoukuApiManager.getCategory(new YoukuCategoryListener() {
                @Override
                public void onResult(List<YoukuCategoryBean> categorieList) {
                    for (int i = 0; i < categorieList.size(); i++) {
                        titleList.add(categorieList.get(i).getLabel());
                        Fragment fragment = new VideoChildFragment();
                        String category = categorieList.get(i).getLabel();
                        try {
                            category = URLEncoder.encode(category, "UTF-8");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString(Constants.YOUKU_VIDEO_CATEGORY, category);
                        LogUtil.i("category--------" + category);
                        fragment.setArguments(bundle);
                        fragmentList.add(fragment);
                    }
                    adapter.setData(fragmentList, titleList);

                }

                @Override
                public void onError(String errorInfo) {

                }
            });

        } else {
            titleList.add("秦时明月");
            titleList.add("百步飞剑");
            titleList.add("夜尽天明");
            titleList.add("诸子百家");
            titleList.add("君临天下");
            titleList.add("沧海横流");
            titleList.add("亡秦必楚");
            titleList.add("天行九歌");

            for (int i = 0; i < titleList.size(); i++) {
                Fragment fragment = new VideoChildFragment();
                fragmentList.add(i, fragment);
            }
            adapter.setData(fragmentList, titleList);
        }

    }


    /**
     * 弹出框显示大图
     */
    private void showSetting() {
        // 一个自定义的布局，作为显示的内容
        View convertView = LayoutInflater.from(getActivity())
                .inflate(R.layout.custom_video_setting, null);

        View layoutToday = convertView.findViewById(R.id.layout_c_video_setting_today);
        View layoutWeek = convertView.findViewById(R.id.layout_c_video_setting_week);
        View layoutMonth = convertView.findViewById(R.id.layout_c_video_setting_month);
        View layoutHistory = convertView.findViewById(R.id.layout_c_video_setting_history);

        TextView tvIconToday = (TextView) convertView.findViewById(R.id.tv_icon_c_video_setting_today);
        TextView tvIconWeek = (TextView) convertView.findViewById(R.id.tv_icon_c_video_setting_week);
        TextView tvIconMonth = (TextView) convertView.findViewById(R.id.tv_icon_c_video_setting_month);
        TextView tvIconHistory = (TextView) convertView.findViewById(R.id.tv_icon_c_video_setting_history);

        TextView tvToday = (TextView) convertView.findViewById(R.id.tv_c_video_setting_today);
        TextView tvWeek = (TextView) convertView.findViewById(R.id.tv_c_video_setting_week);
        TextView tvMonth = (TextView) convertView.findViewById(R.id.tv_c_video_setting_month);
        TextView tvHistory = (TextView) convertView.findViewById(R.id.tv_c_video_setting_history);

        tvIconToday.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconWeek.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconMonth.setTypeface(((MainActivity) getActivity()).getTypeface());
        tvIconHistory.setTypeface(((MainActivity) getActivity()).getTypeface());

        tvIconToday.setText("\ue641");
        tvIconWeek.setText("\ue641");
        tvIconMonth.setText("\ue641");
        tvIconHistory.setText("\ue641");

        if(YoukuApiManager.periodOfCurrent.equals(YoukuApiManager.Period.today)){
            tvToday.setTextColor(getResources().getColor(R.color.black));
            tvIconToday.setTextColor(getResources().getColor(R.color.black));
        }else if(YoukuApiManager.periodOfCurrent.equals(YoukuApiManager.Period.week)){
            tvWeek.setTextColor(getResources().getColor(R.color.black));
            tvIconWeek.setTextColor(getResources().getColor(R.color.black));
        }else if(YoukuApiManager.periodOfCurrent.equals(YoukuApiManager.Period.month)){
            tvMonth.setTextColor(getResources().getColor(R.color.black));
            tvIconMonth.setTextColor(getResources().getColor(R.color.black));
        }else if(YoukuApiManager.periodOfCurrent.equals(YoukuApiManager.Period.history)){
            tvHistory.setTextColor(getResources().getColor(R.color.black));
            tvIconHistory.setTextColor(getResources().getColor(R.color.black));
        }

        final PopupWindow popupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });


        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.pop_window_image_background));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(tvSetting);

        layoutToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoukuApiManager.periodOfCurrent = YoukuApiManager.Period.today;
                popupWindow.dismiss();
                ToastUtil.show(getActivity(),"设置成功！→_→请手动刷新。。");
            }
        });
        layoutWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoukuApiManager.periodOfCurrent = YoukuApiManager.Period.week;
                popupWindow.dismiss();
                ToastUtil.show(getActivity(),"设置成功！→_→请手动刷新。。");
            }
        });
        layoutMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoukuApiManager.periodOfCurrent = YoukuApiManager.Period.month;
                popupWindow.dismiss();
                ToastUtil.show(getActivity(),"设置成功！→_→请手动刷新。。");
            }
        });
        layoutHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoukuApiManager.periodOfCurrent = YoukuApiManager.Period.history;
                popupWindow.dismiss();
                ToastUtil.show(getActivity(),"设置成功！→_→请手动刷新。。");
            }
        });

    }
}
