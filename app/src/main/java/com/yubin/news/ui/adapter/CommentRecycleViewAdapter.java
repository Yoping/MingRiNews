package com.yubin.news.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yubin.news.R;
import com.yubin.news.model.youkuApi.YoukuCommentBean;
import com.yubin.news.ui.customview.CustomImageView;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/9.
 */

public class CommentRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 视频信息View类型标志
     */
    private int HeadView=0;
    /**
     * 评论信息View类型标志
     */
    private int ContentView=1;
    private LayoutInflater layoutInflater;
    private Context context;
//    private List<String> datalist;
    private List<YoukuCommentBean> datalist;

    /**
     * 返回字体
     * @return
     */
    private Typeface iconfont;
    private Typeface getTypeface(){
        if(iconfont==null){
            iconfont=Typeface.createFromAsset(context.getAssets(),"iconfont/iconfont.ttf");
        }
        return iconfont;
    }

    public CommentRecycleViewAdapter(Context context,List<YoukuCommentBean> datalist){
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
        this.datalist=datalist;
    }
//    public CommentRecycleViewAdapter(Context context,List<String> datalist){
//        this.context=context;
//        this.layoutInflater=LayoutInflater.from(context);
//        this.datalist=datalist;
//    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /**
         * 根据view类型加载不同的页面
         */
        if(viewType==HeadView){
            View headview=layoutInflater.inflate(R.layout.custom_comment_head_view,parent,false);
            MyHeadViewHolder headViewHolder=new MyHeadViewHolder(headview);
            return  headViewHolder;
        }else{
            View view=layoutInflater.inflate(R.layout.item_comment,parent,false);
            MyViewHolder myViewHolder=new MyViewHolder(view);
            return myViewHolder;
        }

//        View view=layoutInflater.inflate(R.layout.item_comment,parent,false);
//        MyViewHolder myViewHolder=new MyViewHolder(view);
//        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /**
         * 根据View类型分别加载相应的数据
         */
        if(getItemViewType(position)==HeadView){
            ((MyHeadViewHolder)holder).tvUpIcon.setTypeface(getTypeface());
            ((MyHeadViewHolder)holder).tvDownIcon.setTypeface(getTypeface());
            ((MyHeadViewHolder)holder).tvFavoriteIcon.setTypeface(getTypeface());
        }else{
        ((MyViewHolder)holder).ivAuthorHead.setBackgroundResource(datalist.get(position).getImageUrl());
        ((MyViewHolder)holder).tvAuthorName.setText(datalist.get(position).getName());
        ((MyViewHolder)holder).tvUpCount.setText(datalist.get(position).getUpCount()+"");
        ((MyViewHolder)holder).tvComment.setText(datalist.get(position).getComment());
        ((MyViewHolder)holder).tvTime.setText(datalist.get(position).getTime());
        ((MyViewHolder)holder).tvReplyCount.setText(datalist.get(position).getReplyCount()+"  回复");
        ((MyViewHolder)holder).tvIconUp.setTypeface(getTypeface());
        }

//        ((MyViewHolder)holder).ivAuthorHead.setBackgroundResource(datalist.get(position).getImageUrl());
//        ((MyViewHolder)holder).tvAuthorName.setText(datalist.get(position).getName());
//        ((MyViewHolder)holder).tvUpCount.setText(datalist.get(position).getUpCount()+"");
//        ((MyViewHolder)holder).tvComment.setText(datalist.get(position).getComment());
//        ((MyViewHolder)holder).tvTime.setText(datalist.get(position).getTime());
//        ((MyViewHolder)holder).tvReplyCount.setText(datalist.get(position).getReplyCount()+"  回复");
//        ((MyViewHolder)holder).tvIconUp.setTypeface(getTypeface());

//        ((MyViewHolder)holder).ivAuthorHead.setBackgroundResource(MyTestUtil.getAImage());
//        ((MyViewHolder)holder).tvAuthorName.setText(MyTestUtil.getAName());
//        ((MyViewHolder)holder).tvUpCount.setText(MyTestUtil.random.nextInt(126)+"");
//        ((MyViewHolder)holder).tvComment.setText(WorkerUtil.getYuleMessage());
//        ((MyViewHolder)holder).tvTime.setText("2017-02-9 12:15");
//        ((MyViewHolder)holder).tvReplyCount.setText(MyTestUtil.random.nextInt(20)+"  回复");
//        ((MyViewHolder)holder).tvIconUp.setTypeface(getTypeface());

    }

    @Override
    public int getItemViewType(int position) {
        /**
         * 根据Item的Position判断View类型
         */
        if(position==0){
            return HeadView;
        }else {
            return ContentView;
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    /**
     * 自定义视频信息的ViewHolder
     */
    class MyHeadViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvViewCount;
        private TextView tvUpCount;
        private TextView tvDownCount;
        private TextView tvFavorite;
        private TextView tvAuthorName;
        private TextView tvAttention;

        private TextView tvUpIcon;
        private TextView tvDownIcon;
        private TextView tvFavoriteIcon;

        private CustomImageView ivAuthorHead;

        public MyHeadViewHolder(View view){
            super(view);
            tvTitle=(TextView)view.findViewById(R.id.tv_c_comment_head_view_title);
            tvViewCount=(TextView)view.findViewById(R.id.tv_c_comment_head_view_view_count);
            tvUpCount=(TextView)view.findViewById(R.id.tv_c_comment_head_view_up_count);
            tvDownCount=(TextView)view.findViewById(R.id.tv_c_comment_head_view_down_count);
            tvFavorite=(TextView)view.findViewById(R.id.tv_c_comment_head_view_favorite);
            tvAuthorName=(TextView)view.findViewById(R.id.tv_c_comment_head_view_author_name);
            tvAttention=(TextView)view.findViewById(R.id.tv_c_comment_head_view_attention);

            tvUpIcon=(TextView) view.findViewById(R.id.tv_icon_c_comment_head_view_up_count);
            tvDownIcon=(TextView) view.findViewById(R.id.tv_icon_c_comment_head_view_down_count);
            tvFavoriteIcon=(TextView) view.findViewById(R.id.tv_icon_c_comment_head_view_favorite);

            ivAuthorHead=(CustomImageView) view.findViewById(R.id.iv_circle_c_comment_head_view_author_head);
        }
    }

    /**
     * 评论条目信息的ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder{
        private CustomImageView ivAuthorHead;
        private TextView tvAuthorName;
        private TextView tvUpCount;
        private TextView tvIconUp;
        private TextView tvComment;
        private TextView tvTime;
        private TextView tvReplyCount;
        public MyViewHolder(View view){
            super(view);
            ivAuthorHead=(CustomImageView)view.findViewById(R.id.iv_item_comment_author_head);
            tvAuthorName=(TextView)view.findViewById(R.id.tv_item_comment_author_name);
            tvUpCount=(TextView)view.findViewById(R.id.tv_item_comment_up_count);
            tvIconUp=(TextView)view.findViewById(R.id.tv_icon_item_comment_up);
            tvComment=(TextView)view.findViewById(R.id.tv_item_comment_content);
            tvTime=(TextView)view.findViewById(R.id.tv_item_comment_time);
            tvReplyCount=(TextView)view.findViewById(R.id.tv_item_comment_reply_count);
        }
    }
}



























