package com.yubin.news.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youku.cloud.player.PlayerListener;
import com.youku.cloud.player.VideoDefinition;
import com.youku.cloud.player.YoukuPlayerView;
import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.model.youkuApi.YoukuCommentBean;
import com.yubin.news.ui.adapter.CommentRecycleViewAdapter;
import com.yubin.news.ui.customview.DividerItemDecoration;
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
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private CommentRecycleViewAdapter adapter;
//    private List<String> datalist=new ArrayList<>();
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

//        pullLoadMoreRecyclerView=(PullLoadMoreRecyclerView)findViewById(R.id.recyclerview_video_detail);
//        getData();
//
//        adapter=new CommentRecycleViewAdapter(VideoDetailActivity.this,datalist);
//        pullLoadMoreRecyclerView.setAdapter(adapter);
//        pullLoadMoreRecyclerView.setLinearLayout();
//        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
//        pullLoadMoreRecyclerView.setPushRefreshEnable(false);
//
//        pullLoadMoreRecyclerView.addItemDecoration(new DividerItemDecoration(VideoDetailActivity.this, LinearLayout.VERTICAL));
//
//
//
//
//        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onLoadMore() {
////                handler.postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        initdata();
////                        adapter.notifyDataSetChanged();
////                        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
////                    }
////                },500);
//
////                if(datalist.size()>150){
////                    ToastUtil.show(VideoDetailActivity.this,"没有更多的数据了...");
////                    pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
////                    return;
////                }
////                getData();
////                adapter.notifyDataSetChanged();
////                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//
//
//
//            }
//        });
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
