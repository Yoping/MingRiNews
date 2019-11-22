package com.yubin.news.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yubin.news.R;
import com.yubin.news.model.palyAndroidApi.NewsBean;
import com.yubin.news.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KnowlegeItemAdapter extends RecyclerView.Adapter<KnowlegeItemAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<NewsBean> datalist=new ArrayList<>();
    public KnowlegeItemAdapter(Activity context, List<NewsBean> datalist){
        this.context=context;
        this.inflater=LayoutInflater.from(context);
        this.datalist=datalist;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview=inflater.inflate(R.layout.item_common_knowledge,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(rootview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(datalist.get(position).getTitle());
        holder.tvTime.setText(TimeUtil.changeTimeStampToDate(datalist.get(position).getPublishTime()));
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivImage;
        private TextView tvTitle;
        private TextView tvTime;
        MyViewHolder(View view){
            super(view);
            ivImage=view.findViewById(R.id.iv_item_common_news);
            tvTitle=view.findViewById(R.id.tv_item_commom_news);
            tvTime=view.findViewById(R.id.tv_item_commom_news_time);
        }
    }
}
