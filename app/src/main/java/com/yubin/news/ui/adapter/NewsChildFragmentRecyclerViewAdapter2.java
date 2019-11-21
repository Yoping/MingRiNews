package com.yubin.news.ui.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.model.toutiaoApi.TouTiaoNewsBean;
import com.yubin.news.ui.activity.ImageDetailActivity;
import com.yubin.news.ui.activity.NewsDetailActivity;
import com.yubin.news.utils.LogUtil;

import java.util.List;

/**
 * 今日头条新闻列表适配器
 * Created by YUBIN on 2017/5/5.
 */

public class NewsChildFragmentRecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<TouTiaoNewsBean> datalist;

    private static int viewType_GalleryImage = 1;
    private static int viewType_BigImage = 5;
    private static int viewType_SmallImage = 2;
    private static int viewType_Content = 3;
    private static int viewType_MiddleImage = 4;
    private static int viewType_VideoImage = 6;

    private static int viewType_placeholder=0;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public NewsChildFragmentRecyclerViewAdapter2(Context context, List<TouTiaoNewsBean> datalist) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.datalist = datalist;
    }

    public void setData(List<TouTiaoNewsBean> datalist) {
        this.datalist = datalist;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = layoutInflater.inflate(R.layout.item_f_news_child2, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(rootview);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (getItemViewType(position) == viewType_placeholder) {
            ((MyViewHolder) holder).tvLine.setVisibility(View.VISIBLE);
            ((MyViewHolder)holder).layoutNews.setVisibility(View.GONE);
            return;
        }

        ((MyViewHolder) holder).layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, position);
                }

                if (getItemViewType(position) == viewType_GalleryImage) {
                    String url = datalist.get(position).getDisplay_url();
                    String groupId = url.substring(url.indexOf("group/") + 6, url.lastIndexOf("/"));
                    LogUtil.i("groupId=" + groupId);
                    Intent intent = new Intent(context, ImageDetailActivity.class);
                    intent.putExtra(Constants.TOUTIAO_IMAGE_GROUP_ID, groupId);
                    context.startActivity(intent);
                } else {
                    Constants.GoToNewsUrl = datalist.get(position).getUrl();
                    LogUtil.i("GoToUrl=" + Constants.GoToNewsUrl);
                    Intent intent = new Intent(context, NewsDetailActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        ((MyViewHolder) holder).tvTitle.setText(datalist.get(position).getTitle());
        ((MyViewHolder) holder).tvTime.setText(datalist.get(position).getSource() + "   " + datalist.get(position).getPublish_time());

        if (getItemViewType(position) == viewType_GalleryImage) {
            ((MyViewHolder) holder).ivBigImage.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).tvBigImageNum.setVisibility(View.VISIBLE);

            /**
             * 很奇怪，大图里面的也是小图，所以还是优先取图片列表里面的吧
             */
            if (datalist.get(position).getImage_list() != null) {
                int imageNum = datalist.get(position).getImage_list().size();
                if (imageNum >= 1) {
                    Glide.with(context).load(datalist.get(position).getImage_list().get(0).getUrl()).into(((MyViewHolder) holder).ivBigImage);
                }
                return;
            }

            if (datalist.get(position).getLarge_image_list() != null) {
                int imageNum = datalist.get(position).getLarge_image_list().size();
                if (imageNum >= 1) {
                    Glide.with(context).load(datalist.get(position).getLarge_image_list().get(0).getUrl()).into(((MyViewHolder) holder).ivBigImage);
                }
                return;
            }
            if (datalist.get(position).getMiddle_image() != null) {
                if (!TextUtils.isEmpty(datalist.get(position).getMiddle_image().getUrl())) {
                    Glide.with(context).load(datalist.get(position).getMiddle_image().getUrl()).into(((MyViewHolder) holder).ivBigImage);
                }
                return;
            }

        } else if (getItemViewType(position) == viewType_SmallImage) {
            ((MyViewHolder) holder).layoutSmallImage.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).ivImage1.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).ivImage2.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).ivImage3.setVisibility(View.VISIBLE);
            int imageNum = datalist.get(position).getImage_list().size();
            if (imageNum == 1) {
                Glide.with(context).load(datalist.get(position).getImage_list().get(0).getUrl()).into(((MyViewHolder) holder).ivImage1);
                ((MyViewHolder) holder).ivImage2.setVisibility(View.INVISIBLE);
                ((MyViewHolder) holder).ivImage3.setVisibility(View.INVISIBLE);
                ((MyViewHolder) holder).tvContent.setVisibility(View.GONE);
            } else if (imageNum == 2) {
                Glide.with(context).load(datalist.get(position).getImage_list().get(0).getUrl()).into(((MyViewHolder) holder).ivImage1);
                Glide.with(context).load(datalist.get(position).getImage_list().get(1).getUrl()).into(((MyViewHolder) holder).ivImage2);
                ((MyViewHolder) holder).ivImage3.setVisibility(View.INVISIBLE);
                ((MyViewHolder) holder).tvContent.setVisibility(View.GONE);
            } else if (imageNum >= 3) {
                Glide.with(context).load(datalist.get(position).getImage_list().get(0).getUrl()).into(((MyViewHolder) holder).ivImage1);
                Glide.with(context).load(datalist.get(position).getImage_list().get(1).getUrl()).into(((MyViewHolder) holder).ivImage2);
                Glide.with(context).load(datalist.get(position).getImage_list().get(2).getUrl()).into(((MyViewHolder) holder).ivImage3);
                ((MyViewHolder) holder).tvContent.setVisibility(View.GONE);
            }
        } else if (getItemViewType(position) == viewType_BigImage) {


            ((MyViewHolder) holder).layoutSmallImage.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).ivImage2.setVisibility(View.INVISIBLE);
            ((MyViewHolder) holder).ivImage3.setVisibility(View.INVISIBLE);
            ((MyViewHolder) holder).tvContent.setVisibility(View.GONE);
            Glide.with(context).load(datalist.get(position).getLarge_image_list().get(0).getUrl()).into(((MyViewHolder) holder).ivImage1);


//            ((MyViewHolder) holder).ivBigImage.setVisibility(View.VISIBLE);
//            Glide.with(context).load(datalist.get(position).getLarge_image_list().get(0).getUrl()).into(((MyViewHolder) holder).ivBigImage);
        } else if (getItemViewType(position) == viewType_MiddleImage) {

            ((MyViewHolder) holder).layoutSmallImage.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).ivImage2.setVisibility(View.INVISIBLE);
            ((MyViewHolder) holder).ivImage3.setVisibility(View.INVISIBLE);
            ((MyViewHolder) holder).tvContent.setVisibility(View.GONE);
            Glide.with(context).load(datalist.get(position).getMiddle_image().getUrl()).into(((MyViewHolder) holder).ivImage1);

//            ((MyViewHolder) holder).ivBigImage.setVisibility(View.VISIBLE);
//            Glide.with(context).load(datalist.get(position).getMiddle_image().getUrl()).into(((MyViewHolder) holder).ivBigImage);
        } else if (getItemViewType(position) == viewType_VideoImage) {
            ((MyViewHolder) holder).ivBigImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(datalist.get(position).getBackground().getVideo().getCovers()).into(((MyViewHolder) holder).ivBigImage);
        } else {
            ((MyViewHolder) holder).tvContent.setVisibility(View.VISIBLE);
            ((MyViewHolder) holder).tvContent.setText(datalist.get(position).getAbstract2());

        }





    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }

    @Override
    public int getItemViewType(int position) {

        try{
            if(datalist.get(position).getTitle().equals("占位符")){
                return viewType_placeholder;
            }
        }catch (Exception e){
            e.printStackTrace();
            return viewType_placeholder;
        }


        try {
            //有相册的话显示大图
            if (datalist.get(position).getGallary_style() == 1) {
                return viewType_GalleryImage;
            }
            //有小图的话显示小图(小图可能有张，优先显示)
            if (datalist.get(position).getImage_list() != null) {
                int imageNum = datalist.get(position).getImage_list().size();
                if (imageNum >= 1) {
                    return viewType_SmallImage;
                }
            }

            //有中等图的话显示一张中等图
            if (datalist.get(position).getMiddle_image() != null) {
                if (!TextUtils.isEmpty(datalist.get(position).getMiddle_image().getUrl())) {
                    return viewType_MiddleImage;
                }
            }

            //有大图的话显示一张大图
            if (datalist.get(position).getLarge_image_list() != null) {
                if (!TextUtils.isEmpty(datalist.get(position).getLarge_image_list().get(0).getUrl())) {
                    return viewType_BigImage;
                }
            }

            if (datalist.get(position).getBackground()!= null) {
                if (datalist.get(position).getBackground().getVideo() != null) {
                    if (!TextUtils.isEmpty(datalist.get(position).getBackground().getVideo().getCovers())) {
                        return viewType_VideoImage;
                    }
                }

            }


            //否则一律显示新闻内容
            return viewType_Content;

        } catch (Exception ex) {
            ex.printStackTrace();
            return viewType_Content;
        }


    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private View layout;
        private View layoutSmallImage;
        private View layoutNews;
        private TextView tvLine;
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvTime;
        private TextView tvBigImageNum;
        private ImageView ivImage1;
        private ImageView ivImage2;
        private ImageView ivImage3;
        private ImageView ivBigImage;

        public MyViewHolder(View view) {
            super(view);
            layout = view;
            layoutSmallImage = view.findViewById(R.id.layout_item_f_news_child2_small_image);
            layoutNews=view.findViewById(R.id.layout_item_f_news_child2);
            tvLine = (TextView) view.findViewById(R.id.tv_item_f_news_child2_line);
            tvTitle = (TextView) view.findViewById(R.id.tv_item_f_news_child2_title);
            tvContent = (TextView) view.findViewById(R.id.tv_item_f_news_child2_content);
            tvTime = (TextView) view.findViewById(R.id.tv_item_f_news_child2_time);
            tvBigImageNum = (TextView) view.findViewById(R.id.tv_item_f_news_child2_big_image_num);
            ivImage1 = (ImageView) view.findViewById(R.id.iv_item_f_news_child2_image1);
            ivImage2 = (ImageView) view.findViewById(R.id.iv_item_f_news_child2_image2);
            ivImage3 = (ImageView) view.findViewById(R.id.iv_item_f_news_child2_image3);
            ivBigImage = (ImageView) view.findViewById(R.id.iv_item_f_news_child2_big_image);
        }
    }
}
