package com.yubin.news.ui.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.model.toutiaoApi.ToutiaoRelatedGalleryBean;
import com.yubin.news.ui.activity.ImageDetailActivity;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/18.
 */

public class RelatedImageFragmentRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<ToutiaoRelatedGalleryBean> datalist;
    public RelatedImageFragmentRecycleViewAdapter(Context context,List<ToutiaoRelatedGalleryBean> datalist){
        this.context=context;
        this.inflater=LayoutInflater.from(context);
        this.datalist=datalist;
    }

    /**
     * 更新数据
     * @param datalist
     */
    public void setData(List<ToutiaoRelatedGalleryBean> datalist){
        this.datalist=datalist;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_f_related_image,null);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Glide加载图片
        Glide.with(context).load(datalist.get(position).getMiddle_image().getUrl()).into(((MyViewHolder)holder).ivImage);
        ((MyViewHolder)holder).tvTitle.setText(datalist.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String groupId= datalist.get(position).getGroup_id()+"";
                Intent intent=new Intent(context, ImageDetailActivity.class);
                intent.putExtra(Constants.TOUTIAO_IMAGE_GROUP_ID,groupId);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private View layout;
        private ImageView ivImage;
        private TextView tvTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            layout=itemView;
            ivImage=(ImageView)itemView.findViewById(R.id.iv_item_f_related_image_image);
            tvTitle=(TextView)itemView.findViewById(R.id.tv_item_f_related_image_title);
        }
    }



}
