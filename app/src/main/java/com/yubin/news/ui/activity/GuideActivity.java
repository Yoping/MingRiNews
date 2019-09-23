package com.yubin.news.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.ui.fragment.GuideFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUBIN on17-5-22 下午2:18
 * Last modified 17-6-9 下午2:36
 *
 */

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView tvDot1;
    private TextView tvDot2;
    private TextView tvDot3;
    private List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initview();
    }

    private void initview() {
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.viewpager_guide);
        tvDot1 = (TextView) findViewById(R.id.tv_guide_dot1);
        tvDot2 = (TextView) findViewById(R.id.tv_guide_dot2);
        tvDot3 = (TextView) findViewById(R.id.tv_guide_dot3);
        fragmentList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Fragment fragment = new GuideFragment();
            Bundle bundle=new Bundle();
            if(i<2){
                bundle.putBoolean(Constants.GO_TO_APP,false);
            }else{
                bundle.putBoolean(Constants.GO_TO_APP,true);
            }
            bundle.putInt(Constants.GUIDE_PAGE_INDEX,i);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
        viewPager.setAdapter(new GuideFragmentPagerAdapter(getSupportFragmentManager(),fragmentList));
    }

    class GuideFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        public GuideFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            setDotSelect(position);
            super.setPrimaryItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    private void setDotSelect(int position) {
        tvDot1.setBackgroundResource(R.drawable.round_dot_grey);
        tvDot2.setBackgroundResource(R.drawable.round_dot_grey);
        tvDot3.setBackgroundResource(R.drawable.round_dot_grey);

        if (position == 0) {
            tvDot1.setBackgroundResource(R.drawable.round_dot);
        } else if (position == 1) {
            tvDot2.setBackgroundResource(R.drawable.round_dot);
        } else if (position == 2) {
            tvDot3.setBackgroundResource(R.drawable.round_dot);
        }

    }
}
