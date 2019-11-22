package com.yubin.news.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yubin.news.R;
import com.yubin.news.application.BundleKey;
import com.yubin.news.model.palyAndroidApi.NewsBean;
import com.yubin.news.ui.activity.WebActivity;
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
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,WebActivity.class);
                intent.putExtra(BundleKey.NewsUrl,datalist.get(position).getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvTime;
        MyViewHolder(View view){
            super(view);
            tvTitle=view.findViewById(R.id.tv_item_commom_news);
            tvTime=view.findViewById(R.id.tv_item_commom_news_time);
        }
    }
}
