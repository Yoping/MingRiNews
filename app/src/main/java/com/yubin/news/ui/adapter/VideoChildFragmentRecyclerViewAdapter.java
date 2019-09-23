package com.yubin.news.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.model.youkuApi.YoukuVideoBean;
import com.yubin.news.ui.activity.VideoDetailActivity;
import com.yubin.news.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUBIN on 2017/5/5.
 */

public class VideoChildFragmentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<YoukuVideoBean> datalist;

    /**
     * 点赞图标状态链表
     */
    private List<Boolean> hasUp=new ArrayList<>();

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


    /**
     * 点击事件接口，将adapter条目中的点击事件传递到Activity中
     */
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public VideoChildFragmentRecyclerViewAdapter(Context context, List<YoukuVideoBean> datalist) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.datalist = datalist;
        for(int i=0;i<datalist.size();i++){
            hasUp.add(i,false);
        }
    }

    /**
     * 更新数据
     * @param datalist
     */
    public void setData(List<YoukuVideoBean> datalist){
        this.datalist=datalist;
        //添加更多数据的同时添加相应的点赞记录链表
        for(int i=hasUp.size();i<datalist.size();i++){
            hasUp.add(i,false);
        }
        notifyDataSetChanged();
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = layoutInflater.inflate(R.layout.item_f_video_child, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(rootview);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        String imageUrl=datalist.get(position).getBigThumbnail();
        //https的地址无法取到数据，改用http获取
        imageUrl=imageUrl.replace("https","http");
        Glide.with(context).load(imageUrl).into(((MyViewHolder)holder).ivImage);
        ((MyViewHolder)holder).tvTitle.setText(datalist.get(position).getTitle());
        ((MyViewHolder)holder).tvTime.setText(TimeUtil.changeIntTimeToString(datalist.get(position).getDuration()));
        ((MyViewHolder)holder).tvWatchNum.setText(datalist.get(position).getView_count()+"");
        ((MyViewHolder)holder).tvUpNum.setText(datalist.get(position).getUp_count()+"");
        ((MyViewHolder)holder).tvCommentNum.setText(datalist.get(position).getComment_count()+"");

        ((MyViewHolder)holder).tvIconWatchNum.setTypeface(getTypeface());
        ((MyViewHolder)holder).tvIconUpNum.setTypeface(getTypeface());
        ((MyViewHolder)holder).tvIconCommentNum.setTypeface(getTypeface());

        /**
         *显示点赞状态
         */
        if(hasUp.get(position)){
            ((MyViewHolder)holder).tvIconUpNum.setTextColor(context.getResources().getColor(R.color.red));
        }else{
            ((MyViewHolder)holder).tvIconUpNum.setTextColor(context.getResources().getColor(R.color.grey));
        }

        ((MyViewHolder)holder).tvIconUpNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 设置点赞图标的点击事件
                 */
                if(hasUp.get(position)){
                    ((MyViewHolder)holder).tvIconUpNum.setTextColor(context.getResources().getColor(R.color.grey));
                    hasUp.set(position,false);
                }else{
                    ((MyViewHolder)holder).tvIconUpNum.setTextColor(context.getResources().getColor(R.color.red));
                    hasUp.set(position,true);
                }
            }
        });


        ((MyViewHolder)holder).layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(v,position);
                }

                Intent intent=new Intent(context, VideoDetailActivity.class);
                intent.putExtra(Constants.YOUKU_VIDEO_ID,datalist.get(position).getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }


    /**
     * 视频条目ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        private View layout;
        private ImageView ivImage;
        private TextView tvTitle;
        private TextView tvTime;
        private TextView tvWatchNum;
        private TextView tvIconWatchNum;
        private TextView tvUpNum;
        private TextView tvIconUpNum;
        private TextView tvCommentNum;
        private TextView tvIconCommentNum;


        public MyViewHolder(View view) {
            super(view);
            layout=view;
            ivImage=(ImageView)view.findViewById(R.id.iv_item_f_video_child_image);
            tvTitle = (TextView) view.findViewById(R.id.tv_item_f_video_child_title);
            tvTime = (TextView) view.findViewById(R.id.tv_item_f_video_child_time);
            tvWatchNum=(TextView)view.findViewById(R.id.tv_item_f_video_child_watch_num);
            tvIconWatchNum=(TextView)view.findViewById(R.id.tv_icon_item_f_video_child_watch_num);
            tvUpNum=(TextView)view.findViewById(R.id.tv_item_f_video_child_up_num);
            tvIconUpNum=(TextView)view.findViewById(R.id.tv_icon_item_f_video_child_up_num);
            tvCommentNum=(TextView)view.findViewById(R.id.tv_item_f_video_child_comment_num);
            tvIconCommentNum=(TextView)view.findViewById(R.id.tv_icon_item_f_video_child_comment_num);
        }
    }
}
