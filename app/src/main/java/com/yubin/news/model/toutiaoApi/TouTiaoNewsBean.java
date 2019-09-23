package com.yubin.news.model.toutiaoApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/25.
 */

public class TouTiaoNewsBean {

    private String title;
    private String source;
    private int publish_time;
    private String url;
    private String display_url;
    private List<ToutiaoSubImageBean> image_list;
    private ToutiaoSubImageBean middle_image;
    private int gallary_image_count;
    private List<ToutiaoSubImageBean> large_image_list;
    @SerializedName("abstract")
    private String abstract2;
    private int gallary_style;
    private ToutiaoBackground background;

    public ToutiaoBackground getBackground() {
        return background;
    }

    public void setBackground(ToutiaoBackground background) {
        this.background = background;
    }



    public int getGallary_style() {
        return gallary_style;
    }

    public void setGallary_style(int gallary_style) {
        this.gallary_style = gallary_style;
    }



    public int getGallary_image_count() {
        return gallary_image_count;
    }

    public void setGallary_image_count(int gallary_image_count) {
        this.gallary_image_count = gallary_image_count;
    }

    public List<ToutiaoSubImageBean> getLarge_image_list() {
        return large_image_list;
    }

    public void setLarge_image_list(List<ToutiaoSubImageBean> large_image_list) {
        this.large_image_list = large_image_list;
    }

    public String getAbstract2() {
        return abstract2;
    }

    public void setAbstract2(String abstract2) {
        this.abstract2 = abstract2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(int publish_time) {
        this.publish_time = publish_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
    }

    public List<ToutiaoSubImageBean> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<ToutiaoSubImageBean> image_list) {
        this.image_list = image_list;
    }

    public ToutiaoSubImageBean getMiddle_image() {
        return middle_image;
    }

    public void setMiddle_image(ToutiaoSubImageBean middle_image) {
        this.middle_image = middle_image;
    }





}
