package com.yubin.news.model.toutiaoApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/18.
 */

public class ToutiaoRelatedGalleryBean {









    private String open_url;
    private String media_name;
    private int ban_comment;
    private boolean is_article;
    @SerializedName("abstract")
    private String abstract2;
    private String display_title;
    private int ban_bury;
    private boolean has_video;
    private int article_type;
    private String tag;
    private int has_m3u8_video;
    private String keywords;
    private int text_count;
    private int has_mp4_video;
    private int aggr_type;
    private int article_sub_type;
    private int bury_count;
    private String title;
    private int comment_count;
    private String share_url;
    private String source;


//    "open_url":"sslocal://detail?aggr_type=1&flags=65536&from_gid=6411500025083494657&gd_label=click_related&groupid=6412963338708402434&item_id=6412969194924540418",
//            "media_name":"小慧读史",
//            "ban_comment":0,
//            "is_article":true,
//            "abstract":"",
//            "display_title":"",
//            "ban_bury":0,
//            "has_video":false,
//            "article_type":0,
//            "tag":"news_military",
//            "has_m3u8_video":0,
//            "keywords":"乔治亚州",
//            "text_count":15,
//            "has_mp4_video":0,
//            "aggr_type":1,
//            "article_sub_type":0,
//            "bury_count":5,
//            "title":"能抵御上万吨级核弹的地下豪宅，末日必备！",
//            "comment_count":4345,
//            "share_url":"http://toutiao.com/group/6412963338708402434/?iid=10109443249&app=news_article",
//            "source":"小慧读史",


    private int hot;
    private String article_url;
    private int repin_count;
    private int publish_time;
    private int group_flags;
    private List<ToutiaoSubImageBean> image_list;
    private long tag_id;
    private long item_id;
    private String display_url;
    private String url;
    private int level;
    private int digg_count;
    private int behot_time;
    private int ban_action;
    private int preload_web;
    private boolean has_image;
    private long group_id;
    private int image_count;
    private ToutiaoSubImageBean middle_image;
//
//            "hot":1,
//            "article_url":"http://toutiao.com/group/6412963338708402434/",
//            "repin_count":7654,
//            "publish_time":1493135839,
//            "group_flags":131074,
//            "image_list":Array[3],
//            "tag_id":6412963338708402000,
//            "item_id":6412969194924541000,
//            "display_url":"http://toutiao.com/group/6412963338708402434/",
//            "url":"http://toutiao.com/group/6412963338708402434/",
//            "level":0,
//            "digg_count":7,
//            "behot_time":1493135843,
//            "ban_action":0,
//            "preload_web":1,
//            "has_image":true,
//            "group_id":6412963338708402000,
//            "image_count":8,
//            "middle_image":Object{...}


    public String getOpen_url() {
        return open_url;
    }

    public void setOpen_url(String open_url) {
        this.open_url = open_url;
    }

    public String getMedia_name() {
        return media_name;
    }

    public void setMedia_name(String media_name) {
        this.media_name = media_name;
    }

    public int getBan_comment() {
        return ban_comment;
    }

    public void setBan_comment(int ban_comment) {
        this.ban_comment = ban_comment;
    }

    public boolean is_article() {
        return is_article;
    }

    public void setIs_article(boolean is_article) {
        this.is_article = is_article;
    }

    public String getAbstract2() {
        return abstract2;
    }

    public void setAbstract2(String abstract2) {
        this.abstract2 = abstract2;
    }

    public String getDisplay_title() {
        return display_title;
    }

    public void setDisplay_title(String display_title) {
        this.display_title = display_title;
    }

    public int getBan_bury() {
        return ban_bury;
    }

    public void setBan_bury(int ban_bury) {
        this.ban_bury = ban_bury;
    }

    public boolean isHas_video() {
        return has_video;
    }

    public void setHas_video(boolean has_video) {
        this.has_video = has_video;
    }

    public int getArticle_type() {
        return article_type;
    }

    public void setArticle_type(int article_type) {
        this.article_type = article_type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getHas_m3u8_video() {
        return has_m3u8_video;
    }

    public void setHas_m3u8_video(int has_m3u8_video) {
        this.has_m3u8_video = has_m3u8_video;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getText_count() {
        return text_count;
    }

    public void setText_count(int text_count) {
        this.text_count = text_count;
    }

    public int getHas_mp4_video() {
        return has_mp4_video;
    }

    public void setHas_mp4_video(int has_mp4_video) {
        this.has_mp4_video = has_mp4_video;
    }

    public int getAggr_type() {
        return aggr_type;
    }

    public void setAggr_type(int aggr_type) {
        this.aggr_type = aggr_type;
    }

    public int getArticle_sub_type() {
        return article_sub_type;
    }

    public void setArticle_sub_type(int article_sub_type) {
        this.article_sub_type = article_sub_type;
    }

    public int getBury_count() {
        return bury_count;
    }

    public void setBury_count(int bury_count) {
        this.bury_count = bury_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public int getRepin_count() {
        return repin_count;
    }

    public void setRepin_count(int repin_count) {
        this.repin_count = repin_count;
    }

    public int getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(int publish_time) {
        this.publish_time = publish_time;
    }

    public int getGroup_flags() {
        return group_flags;
    }

    public void setGroup_flags(int group_flags) {
        this.group_flags = group_flags;
    }

    public List<ToutiaoSubImageBean> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<ToutiaoSubImageBean> image_list) {
        this.image_list = image_list;
    }

    public long getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(int digg_count) {
        this.digg_count = digg_count;
    }

    public int getBehot_time() {
        return behot_time;
    }

    public void setBehot_time(int behot_time) {
        this.behot_time = behot_time;
    }

    public int getBan_action() {
        return ban_action;
    }

    public void setBan_action(int ban_action) {
        this.ban_action = ban_action;
    }

    public int getPreload_web() {
        return preload_web;
    }

    public void setPreload_web(int preload_web) {
        this.preload_web = preload_web;
    }

    public boolean isHas_image() {
        return has_image;
    }

    public void setHas_image(boolean has_image) {
        this.has_image = has_image;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getImage_count() {
        return image_count;
    }

    public void setImage_count(int image_count) {
        this.image_count = image_count;
    }

    public ToutiaoSubImageBean getMiddle_image() {
        return middle_image;
    }

    public void setMiddle_image(ToutiaoSubImageBean middle_image) {
        this.middle_image = middle_image;
    }



}
