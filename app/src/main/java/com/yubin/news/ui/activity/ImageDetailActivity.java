package com.yubin.news.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.http.toutiaoApi.ToutiaoApiManager;
import com.yubin.news.http.toutiaoApi.ToutiaoGetImageStoryGalleryListener;
import com.yubin.news.model.toutiaoApi.ToutiaoGalleryBean;
import com.yubin.news.ui.fragment.ImageDetailFragment;
import com.yubin.news.ui.fragment.RelatedImageFragment;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUBIN on17-5-29 下午2:19
 * Last modified 17-6-21 下午5:22
 *
 */

public class ImageDetailActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private MyImageFragmentViewpagerAdapter adapter;
    private List<ToutiaoGalleryBean> datalist;
    private List<Fragment> fragmentList=new ArrayList<>();
    private String imageGroupId="";
    private boolean hasInitView=false;
    public static boolean isTitleShow=true;


    private  MyTouchEventListener myTouchEventListener;
    public interface MyTouchEventListener{
        boolean onTouch(MotionEvent event);
    }
    public void setMyTouchEventListener(MyTouchEventListener myTouchEventListener){
        this.myTouchEventListener=myTouchEventListener;
    }

    /**
     * 控制获取到数据之后再去初始化界面
     */
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==1){
                initview();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        Intent intent=getIntent();
        if(intent.hasExtra(Constants.TOUTIAO_IMAGE_GROUP_ID)){
            imageGroupId=intent.getStringExtra(Constants.TOUTIAO_IMAGE_GROUP_ID);
            LogUtil.i("==================imageGroupId="+imageGroupId);
            getData();
        }else{
            ToastUtil.show(ImageDetailActivity.this,"未获取到数据！");
            finish();
        }

    }


    /**
     * 初始化界面
     */
    private void initview(){
        if(hasInitView){
            return;
        }
        viewPager=(ViewPager)findViewById(R.id.viewpager_image_detail);
        for(int i=0;i<datalist.size();i++){
            ImageDetailFragment fragment=new ImageDetailFragment();
            Bundle bundle=new Bundle();
            bundle.putString(Constants.TOUTIAO_IMAGE_GROUP_ID,imageGroupId);
            bundle.putString(Constants.TOUTIAO_IMAGE_URL,datalist.get(i).getSub_image().getUrl());
            bundle.putString(Constants.TOUTIAO_IMAGE_TITLE,(i+1)+"/"+datalist.size()+" "+datalist.get(i).getSub_abstract());
            fragment.setArguments(bundle);
            fragmentList.add(i,fragment);
        }
        RelatedImageFragment relatedImageFragment=new RelatedImageFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constants.TOUTIAO_IMAGE_GROUP_ID,imageGroupId);
        relatedImageFragment.setArguments(bundle);

        fragmentList.add(relatedImageFragment);
        adapter=new MyImageFragmentViewpagerAdapter(getSupportFragmentManager(),fragmentList,datalist);
        viewPager.setAdapter(adapter);
        hasInitView=true;


    }

    /**
     * 获取数据
     */
    private void getData(){
        ToutiaoApiManager.getImageStoryGallery(imageGroupId, new ToutiaoGetImageStoryGalleryListener() {
            @Override
            public void onResult(List<ToutiaoGalleryBean> gelleryImageList) {
                datalist=gelleryImageList;
                Message message=new Message();
                message.arg1=1;
                handler.sendMessage(message);
            }

            @Override
            public void onError(String errorInfo) {
                Message message=new Message();
                message.arg1=0;
                handler.sendMessage(message);
            }
        });
    }



    /**
     * 自定义图片Fragment-Viewpager适配器
     */
    class MyImageFragmentViewpagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;

        public MyImageFragmentViewpagerAdapter(FragmentManager fm,List<Fragment> fragmentList,List<ToutiaoGalleryBean> datalist) {
            super(fm);
            this.fragmentList=fragmentList;
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            //控制图片详情页面单击之后所有title的显示与否
            if(position<(fragmentList.size()-1)){
                ((ImageDetailFragment)fragmentList.get(position)).refreshTitle();
            }

        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }


    /**
     * 将事件分发到子View
     * @param event
     * @return
     */
        @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        if(myTouchEventListener!=null){
//            LogUtil.i("activity touch");
//            myTouchEventListener.onTouch(event);
//        }
            if(viewPager!=null){
                viewPager.dispatchTouchEvent(event);
            }

        return false;
    }

}
