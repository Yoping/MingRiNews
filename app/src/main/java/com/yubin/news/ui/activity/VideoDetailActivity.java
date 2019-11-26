package com.yubin.news.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.youku.cloud.player.PlayerListener;
import com.youku.cloud.player.VideoDefinition;
import com.youku.cloud.player.YoukuPlayerView;
import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.model.youkuApi.YoukuCommentBean;
import com.yubin.news.ui.adapter.CommentRecycleViewAdapter;
import com.yubin.news.ui.customview.CustomTopBar;
import com.yubin.news.utils.LogUtil;
import com.yubin.news.utils.MyTestUtil;
import com.yubin.news.utils.WorkerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUBIN on17-4-29 下午2:20
 * Last modified 17-5-21 下午5:41
 *
 */

public class VideoDetailActivity extends AppCompatActivity {

    /**
     * 优酷视频播放View
     */
    private YoukuPlayerView youkuPlayerView;

    /**
     * 评论列表
     */
    private CommentRecycleViewAdapter adapter;
    private List<YoukuCommentBean> datalist=new ArrayList<>();
    private View layoutHeader;
    private TextView tvUp;

    /**
     * 当前播放视频的ID
     */
    private String videoId="";

    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        Intent intent=getIntent();
        if(intent.hasExtra(Constants.YOUKU_VIDEO_ID)){
            videoId=intent.getStringExtra(Constants.YOUKU_VIDEO_ID);
        }
        initview();

    }


    /**
     * 初始化界面
     */
    private void initview(){
//        layoutHeader=findViewById(R.id.layout_custom_comment_head);
//        tvUp=(TextView)findViewById(R.id.tv_c_comment_head_view_up_count);

        /**
         * 初始化优酷播放器
         */
        youkuPlayerView = (YoukuPlayerView) findViewById(R.id.youku_player_video_detail);
        youkuPlayerView.attachActivity(this);
        youkuPlayerView.setPreferVideoDefinition(VideoDefinition.VIDEO_HD);
        youkuPlayerView.setPlayerListener(new PlayerListener() {
        });
        if(!TextUtils.isEmpty(videoId)){
            youkuPlayerView.playYoukuVideo(videoId);
        }

    }

    /**
     * 初始化测试数据
     */
    private void getData(){
        for(int i=0;i<10;i++){
//            datalist.add("O.O");
            YoukuCommentBean comment=new YoukuCommentBean();
            comment.setImageUrl(MyTestUtil.getAImage());
            comment.setName(MyTestUtil.getAName());
            comment.setUpCount(MyTestUtil.random.nextInt(250));
            comment.setComment(WorkerUtil.getYuleMessage());
            comment.setTime("2017-02-9 12:15");
//            comment.setReplyCount(MyTestUtil.random.nextInt(20));
            comment.setReplyCount(i);
            datalist.add(comment);
        }
//        tvUp.setText(datalist.size()+"");


    }




    @Override
    protected void onPause() {
        super.onPause();
        youkuPlayerView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        youkuPlayerView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youkuPlayerView.onDestroy();
    }

    private float x=0;
    private float y=0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x=event.getX();
                y=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if((event.getY()-y)>Constants.ErrorOfTouch){//下滑
                    LogUtil.i("-----------下滑");
                    showSearchView();
                }else if((y-event.getY())>Constants.ErrorOfTouch){//上滑
                    LogUtil.i("-----------上滑");
                    hideSearchView();
                }
                break;
            case MotionEvent.ACTION_UP:
                x=event.getX();
                y=event.getY();
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private boolean searchLayoutIsShow=true;
    /**
     * 隐藏搜索栏
     */
    public void hideSearchView(){
        LogUtil.i("-----------hideSearchView");
        if((layoutHeader!=null)&&(searchLayoutIsShow)){
//            ObjectAnimator.ofFloat(layoutHeader,"scaleY",1,0).setDuration(300).start();
//            searchLayoutIsShow=false;
            Animator animator= AnimatorInflater.loadAnimator(VideoDetailActivity.this,R.animator.header_hide);
            animator.setTarget(layoutHeader);
            animator.start();
            searchLayoutIsShow=false;
        }
    }

    /**
     * 显示搜索栏
     */
    public void showSearchView(){
        LogUtil.i("-----------showSearchView");
        if((layoutHeader!=null)&&(!searchLayoutIsShow)){
//            ObjectAnimator.ofFloat(layoutHeader,"scaleY",0,1).setDuration(300).start();
//            searchLayoutIsShow=true;
            Animator animator= AnimatorInflater.loadAnimator(VideoDetailActivity.this,R.animator.header_show);
            animator.setTarget(layoutHeader);
            animator.start();
            searchLayoutIsShow=true;
        }
    }
}
