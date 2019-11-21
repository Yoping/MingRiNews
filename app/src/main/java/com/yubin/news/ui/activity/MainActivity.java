

package com.yubin.news.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.application.MyApplication;
import com.yubin.news.http.youkuApi.YoukuApiManager;
import com.yubin.news.ui.fragment.ImageFragment;
import com.yubin.news.ui.fragment.MyFragment;
import com.yubin.news.ui.fragment.NewsFragment;
import com.yubin.news.ui.fragment.VideoFragment;
import com.yubin.news.utils.FileUtil;
import com.yubin.news.utils.ToastUtil;

import java.util.ArrayList;
/**
 * Created by YUBIN on17-6-29 下午2:17
 * Last modified 17-6-15 下午12:02
 *
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    /**
     * 新闻主界面
     */
    private NewsFragment newsFragment;
    /**
     * 视频主界面
     */
    private VideoFragment videoFragment;
    /**
     * 图片主界面
     */
    private ImageFragment imageFragment;
    /**
     * 我的主界面
     */
    private MyFragment myFragment;

    private FragmentManager fragmentManager;

    /**
     * 底部四个Tab
     */
    private View layoutMain;
    private View layoutTab;
    private View layoutTab1;
    private View layoutTab2;
    private View layoutTab3;
    private View layoutTab4;

    /**
     * Tab中的字体图标
     */
    private TextView tvIconTab1;
    private TextView tvIconTab2;
    private TextView tvIconTab3;
    private TextView tvIconTab4;

    /**
     * Tab中的文字
     */
    private TextView tvTitle1;
    private TextView tvTitle2;
    private TextView tvTitle3;
    private TextView tvTitle4;

    private float x=0;
    private float y=0;
    private int currentTab=0;

    public static String videoPeriod= YoukuApiManager.Period.week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        AnimationDrawable a=new AnimationDrawable();
        a.start();

    }

    /**
     * 初始化界面
     */
    private void initview(){

        layoutMain=findViewById(R.id.activity_main);
        layoutTab=findViewById(R.id.layout_tab);
        layoutTab1=findViewById(R.id.layout_tab1);
        layoutTab2=findViewById(R.id.layout_tab2);
        layoutTab3=findViewById(R.id.layout_tab3);
        layoutTab4=findViewById(R.id.layout_tab4);

        tvIconTab1=(TextView) findViewById(R.id.tv_icon_main_tab1);
        tvIconTab2=(TextView)findViewById(R.id.tv_icon_main_tab2);
        tvIconTab3=(TextView)findViewById(R.id.tv_icon_main_tab3);
        tvIconTab4=(TextView)findViewById(R.id.tv_icon_main_tab4);

        tvIconTab1.setTypeface(getTypeface());
        tvIconTab2.setTypeface(getTypeface());
        tvIconTab3.setTypeface(getTypeface());
        tvIconTab4.setTypeface(getTypeface());

        tvIconTab1.setText("\ue63A");
        tvIconTab2.setText("\ue635");
        tvIconTab3.setText("\ue619");
        tvIconTab4.setText("\ue636");

        tvTitle1=(TextView)findViewById(R.id.tv_main_title1);
        tvTitle2=(TextView)findViewById(R.id.tv_main_title2);
        tvTitle3=(TextView)findViewById(R.id.tv_main_title3);
        tvTitle4=(TextView)findViewById(R.id.tv_main_title4);

        layoutTab1.setOnClickListener(this);
        layoutTab2.setOnClickListener(this);
        layoutTab3.setOnClickListener(this);
        layoutTab4.setOnClickListener(this);

        fragmentManager=getSupportFragmentManager();
        showFrame(0);

    }



    /**
     * 返回字体
     * @return
     */
    private Typeface iconfont;
    public Typeface getTypeface(){
        if(iconfont==null){
            iconfont=Typeface.createFromAsset(this.getAssets(),"iconfont/iconfont.ttf");
        }
        return iconfont;
    }

    /**
     * 显示其中一个界面
     * @param choise
     */
    private void showFrame(int choise) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        cleanChoise();
        hideAllFragment(transaction);
        switch (choise) {
            case 0:
                currentTab=0;
                tvIconTab1.setTextColor(getResources().getColor(R.color.black));
                tvTitle1.setTextColor(getResources().getColor(R.color.black));
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    transaction
                            .add(R.id.main_frame, newsFragment);
                } else {
                    transaction.show(newsFragment);
                }
                break;
            case 1:
                currentTab=1;
                tvIconTab2.setTextColor(getResources().getColor(R.color.black));
                tvTitle2.setTextColor(getResources().getColor(R.color.black));
                if (videoFragment == null) {
                    videoFragment = new VideoFragment();
                    transaction
                            .add(R.id.main_frame, videoFragment);
                } else {
                    transaction.show(videoFragment);
                }
                break;
            case 2:
                currentTab=2;
                tvIconTab3.setTextColor(getResources().getColor(R.color.black));
                tvTitle3.setTextColor(getResources().getColor(R.color.black));
                if (imageFragment == null) {
                    imageFragment = new ImageFragment();
                    transaction
                            .add(R.id.main_frame, imageFragment);
                } else {
                    transaction.show(imageFragment);
                }
                break;
            case 3:
                currentTab=3;
                tvIconTab4.setTextColor(getResources().getColor(R.color.black));
                tvTitle4.setTextColor(getResources().getColor(R.color.black));
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction
                            .add(R.id.main_frame, myFragment);
                } else {
                    transaction.show(myFragment);
                }
                break;
        }

        transaction.commit();
    }

    /**
     * 清除原先的选择
     */
    private void cleanChoise() {

        tvIconTab1.setTextColor(getResources().getColor(R.color.white));
        tvIconTab2.setTextColor(getResources().getColor(R.color.white));
        tvIconTab3.setTextColor(getResources().getColor(R.color.white));
        tvIconTab4.setTextColor(getResources().getColor(R.color.white));

        tvTitle1.setTextColor(getResources().getColor(R.color.white));
        tvTitle2.setTextColor(getResources().getColor(R.color.white));
        tvTitle3.setTextColor(getResources().getColor(R.color.white));
        tvTitle4.setTextColor(getResources().getColor(R.color.white));
    }

    /**
     * 隐藏所有界面
     * @param transaction
     */
    private void hideAllFragment(FragmentTransaction transaction) {
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (videoFragment != null) {
            transaction.hide(videoFragment);
        }
        if (imageFragment != null) {
            transaction.hide(imageFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }

    }

    private boolean tabIsShow=true;
    public void hideAllTab(){
        if(currentTab==3){
            return;
        }
        if((layoutTab!=null)&&tabIsShow){
            Animator animator= AnimatorInflater.loadAnimator(MainActivity.this,R.animator.slide_down);
            animator.setTarget(layoutTab);
            animator.start();
            tabIsShow=false;
        }
    }

    public void showAllTab(){
        if(currentTab==3){
            return;
        }
        if((layoutTab!=null)&&(!tabIsShow)){
            Animator animator= AnimatorInflater.loadAnimator(MainActivity.this,R.animator.slide_up);
            animator.setTarget(layoutTab);
            animator.start();
            tabIsShow=true;
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.layout_tab1:
                showFrame(0);
                break;
            case R.id.layout_tab2:
                showFrame(1);
                break;
            case R.id.layout_tab3:
                showFrame(2);
                break;
            case R.id.layout_tab4:
                showFrame(3);
                break;
        }
    }

    /**
     * 双击退出程序
     */
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.show(getApplicationContext(), "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                for (Activity activity : MyApplication.activityList) {
                    activity.finish();
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 事件分发的时候，通过触摸动作的种类控制是否隐藏底部菜单栏，以腾出更大的屏幕显示空间
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x=event.getX();
                y=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if((event.getY()-y)> Constants.ErrorOfTouch){//下滑
                    showAllTab();
                }else if((y-event.getY())>Constants.ErrorOfTouch){//上滑
                    hideAllTab();
                }
                break;
            case MotionEvent.ACTION_UP:
                x=event.getX();
                y=event.getY();
                break;
        }

        for(MyTouchListener listener:onTouchListeners){
            listener.onMyTouchListener(event);
        }
        return super.dispatchTouchEvent(event);
    }


    /**
     * 自定义触摸监听接口，用于传递触摸事件给Fragment
     */
    public interface MyTouchListener{
        void onMyTouchListener(MotionEvent event);
    }
    private ArrayList<MyTouchListener> onTouchListeners=new ArrayList<>(10);
    public void registerMyTouchListenr(MyTouchListener touchListener){
        onTouchListeners.add(touchListener);
    }
    public void unRegisterMyTouchListener(MyTouchListener touchListener){
        onTouchListeners.remove(touchListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode==Activity.RESULT_OK){
            if(requestCode==MyFragment.SELECT_IMAGE_ACTION_CODE){
                String filepath= FileUtil.getPath(MainActivity.this,intent.getData());
                myFragment.setAvatar(filepath);
            }else{
                ToastUtil.show(this,"修改失败！");
            }
        }
    }

    public NewsFragment getNewsFragment(){
        return newsFragment;
    }

}






































