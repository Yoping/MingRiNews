package com.yubin.news.ui.adapter;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.http.imageLoader.ImageLoader;
import com.yubin.news.model.toutiaoApi.ToutiaoImageStoryBean;
import com.yubin.news.ui.activity.ImageDetailActivity;
import com.yubin.news.utils.GlideUtil;
import com.yubin.news.utils.LogUtil;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/5.
 */

public class ImageFgRecyViewAdapter extends RecyclerView.Adapter<ImageFgRecyViewAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<ToutiaoImageStoryBean> datalist;


    /**
     * 点击事件接口，将adapter条目中的点击事件传递到Activity中
     */
    public interface OnItemClickListener {
        //单击事件
        void onItemClick(View view, int position);

        //长按事件
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ImageFgRecyViewAdapter(Context context, List<ToutiaoImageStoryBean> datalist) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.datalist = datalist;
    }

    /**
     * 更新数据
     *
     * @param datalist
     */
    public void setData(List<ToutiaoImageStoryBean> datalist) {
        this.datalist = datalist;
        for (int i = 0; i < datalist.size(); i++) {
            LogUtil.i(" datalist i=" + i + "  " + datalist.get(i).getTitle());
        }
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = layoutInflater.inflate(R.layout.item_f_image, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(rootview);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        LogUtil.i("position=" + position + "  " + datalist.get(position).getTitle());

        GlideUtil.load(context, datalist.get(position).getImage_url(), holder.ivImage);
//        ImageLoader.getInstance(context).displayBitmap(holder.ivImage,datalist.get(position).getImage_url());
        holder.tvTitle.setText(datalist.get(position).getTitle() + "(" + datalist.get(position).getGallary_image_count() + ")张");

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }
                //跳到图片组详情页面并传递图片组ID
                Intent intent = new Intent(context, ImageDetailActivity.class);
                intent.putExtra(Constants.TOUTIAO_IMAGE_GROUP_ID, datalist.get(position).getGroup_id());
                context.startActivity(intent);
            }
        });

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemLongClick(v, position);
                }
                return false;
            }
        });


    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private View layout;
        private TextView tvTitle;
        private ImageView ivImage;

        public MyViewHolder(View view) {
            super(view);
            layout = view;
            tvTitle = (TextView) view.findViewById(R.id.tv_item_f_image_title);
            ivImage = (ImageView) view.findViewById(R.id.iv_item_f_image_image);
        }
    }
}
