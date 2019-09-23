package com.yubin.news.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yubin.news.R;
import com.yubin.news.application.Constants;
import com.yubin.news.model.juheApi.JuheNewsBean;
import com.yubin.news.ui.activity.NewsDetailActivity;
import com.yubin.news.ui.fragment.NewsChildFragment;
import com.yubin.news.utils.LogUtil;

import java.util.List;
import java.util.Random;

/**
 * Created by YUBIN on 2017/5/5.
 */

public class NewsChildFragmentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<JuheNewsBean> datalist;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public NewsChildFragmentRecyclerViewAdapter(Context context, List<JuheNewsBean> datalist) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.datalist = datalist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = layoutInflater.inflate(R.layout.item_f_news_child, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(rootview);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((MyViewHolder) holder).tvTitle.setText(datalist.get(position).getTitle());
        ((MyViewHolder) holder).tvTime.setText(datalist.get(position).getAuthor_name() + "   " + datalist.get(position).getDate());

        //第一张图片
        if (!TextUtils.isEmpty(datalist.get(position).getThumbnail_pic_s())) {
            if(NewsChildFragment.isGetApiData){
                Glide.with(context).load(datalist.get(position).getThumbnail_pic_s()).into(((MyViewHolder) holder).ivImage1);
            }else{
//                ((MyViewHolder) holder).ivImage1.setImageResource(getAImage());
                Glide.with(context).load(getAImageUrl()).into(((MyViewHolder) holder).ivImage1);
            }

        } else {
            ((MyViewHolder) holder).ivImage1.setVisibility(View.INVISIBLE);
        }

        //第二张图片
        if (!TextUtils.isEmpty(datalist.get(position).getThumbnail_pic_s02())) {
            if(NewsChildFragment.isGetApiData){
                Glide.with(context).load(datalist.get(position).getThumbnail_pic_s02()).into(((MyViewHolder) holder).ivImage2);
            }else{
//                ((MyViewHolder) holder).ivImage2.setImageResource(getAImage());
                Glide.with(context).load(getAImageUrl()).into(((MyViewHolder) holder).ivImage2);
            }
        } else {
            ((MyViewHolder) holder).ivImage2.setVisibility(View.INVISIBLE);
        }

        //第三张图片
        if (!TextUtils.isEmpty(datalist.get(position).getThumbnail_pic_s03())) {
            if(NewsChildFragment.isGetApiData){
                Glide.with(context).load(datalist.get(position).getThumbnail_pic_s03()).into(((MyViewHolder) holder).ivImage3);
            }else{
//                ((MyViewHolder) holder).ivImage3.setImageResource(getAImage());
                Glide.with(context).load(getAImageUrl()).into(((MyViewHolder) holder).ivImage3);
            }
        } else {
            ((MyViewHolder) holder).ivImage3.setVisibility(View.INVISIBLE);
        }

        ((MyViewHolder)holder).layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,position);
                Constants.GoToNewsUrl=datalist.get(position).getUrl();
                Intent intent=new Intent(context, NewsDetailActivity.class);
                context.startActivity(intent);
            }
        });


    }

    /**
     * 本地图片测试数据
     */
    private int imageSource[] = {R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4, R.mipmap.image5, R.mipmap.image6,
            R.mipmap.qsmy1, R.mipmap.qsmy2, R.mipmap.qsmy3, R.mipmap.qsmy4, R.mipmap.qsmy5, R.mipmap.qsmy6,
            R.mipmap.news1, R.mipmap.news2, R.mipmap.news3, R.mipmap.news1, R.mipmap.news2, R.mipmap.news3,
            R.mipmap.news1, R.mipmap.news2, R.mipmap.news3, R.mipmap.news1, R.mipmap.news2, R.mipmap.news3};
    private Random random = new Random();

    private int getAImage() {
        int num = random.nextInt(imageSource.length);
        return imageSource[num];
    }


    /**
     * 网络图片测试数据
     */
    private String imageUrl[]={"http://05.imgmini.eastday.com//mobile//20170508//20170508092900_648d3a362e933fc1dd7017a505ab450f_1_mwpm_03200403.png",
    "http://07.imgmini.eastday.com/mobile/20170508/20170508091629_aa5db642b4eff442a253aef471c6d013_1_mwpm_03200403.jpeg",
    "http://04.imgmini.eastday.com/mobile/20170508/20170508090456_6895d0dad29f1d1e1b40301debffc610_4_mwpm_03200403.jpeg",
    "http://09.imgmini.eastday.com/mobile/20170508/20170508084128_61bdca6fe1f1394963d5538c33a7b723_1_mwpm_03200403.jpeg",
    "http://09.imgmini.eastday.com/mobile/20170508/20170508_70f2a314811cb592a6b8ea3a9ae523e2_cover_mwpm_03200403.jpeg",
    "http://07.imgmini.eastday.com/mobile/20170508/20170508082445_f91119ac84fd846540bb680657386971_2_mwpm_03200403.png",
    "http://tupian.enterdesk.com/2014/xll/03/03/1/qinshimingyue5.jpg",
    "http://pic26.nipic.com/20130106/5274058_164719384000_2.jpg",
    "http://pic.sjxyx.com/upload/article/2014/07/1331459160.jpg",
    "http://b-ssl.duitang.com/uploads/blog/201506/06/20150606093905_FjxcX.jpeg",
    "http://imgstore.cdn.sogou.com/app/a/100540002/698267.jpg",
    "http://img1.imgtn.bdimg.com/it/u=4247038801,2593847773&fm=214&gp=0.jpg",
    "http://tupian.enterdesk.com/2014/xll/03/10/2/tianming11.jpg"};

    private String getAImageUrl(){
        int num=random.nextInt(imageUrl.length);
        LogUtil.i(imageUrl[num]);
        return imageUrl[num];

    }




    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public void insertOneNews() {
        LogUtil.i("insertOneNews");
        JuheNewsBean juheNewsBean = new JuheNewsBean();
        juheNewsBean.setAuthor_name("脑残");
        juheNewsBean.setDate("2017.5.5");
        juheNewsBean.setThumbnail_pic_s("hahah");
        juheNewsBean.setThumbnail_pic_s02("shabi");
        juheNewsBean.setThumbnail_pic_s03(null);
        datalist.add(juheNewsBean);
        notifyItemInserted(0);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private View layout;
        private TextView tvTitle;
        private TextView tvTime;
        private ImageView ivImage1;
        private ImageView ivImage2;
        private ImageView ivImage3;

        public MyViewHolder(View view) {
            super(view);
            layout=view;
            tvTitle = (TextView) view.findViewById(R.id.tv_item_f_news_child_title);
            tvTime = (TextView) view.findViewById(R.id.tv_item_f_news_child_time);
            ivImage1 = (ImageView) view.findViewById(R.id.iv_item_f_news_child_image1);
            ivImage2 = (ImageView) view.findViewById(R.id.iv_item_f_news_child_image2);
            ivImage3 = (ImageView) view.findViewById(R.id.iv_item_f_news_child_image3);
        }
    }
}
