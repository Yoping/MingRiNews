package com.yubin.news.model.toutiaoApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YUBIN on18-2-6 上午9:34
 * Last modified 17-6-1 下午5:45
 *
 */

/**
 * Created by YUBIN on 2017/5/25.
 */

public class TouTiaoNewsBeanCopy {

    private ToutiaoLogExtraBean log_extra;
    private ToutiaoLogPd log_pb;
    private int read_count;
    private String media_name;
    private int ban_comment;
    @SerializedName("abstract")
    private String abstract2;
    private List<ToutiaoSubImageBean> image_list;
    private boolean has_video;
    private int article_type;
    private String tag;
    private ToutiaoForwardInfo forward_info;
    private int has_m3u8_video;
    private String keywords;
    private String rid;
    private boolean show_portrait_article;
    private int user_verified;

    private int aggr_type;
    private int cell_type;
    private int article_sub_type;
    private int bury_count;
    private String title;
    private int ignore_web_transform;
    private int source_icon_style;
    private int tip;
    private int hot;
    private String share_url;
    private int has_mp4_video;
    private String source;
    private int comment_count;
    private String article_url;
    private List<ToutiaoFilterWords> filter_words;

    private int share_count;
    private int publish_time;
    private List<ToutiaoAction> action_list;
    private int gallary_style;
    private int gallary_image_count;
    private int cell_layout_style;
    private long tag_id;
    private ToutiaoActionExtra action_extra;
    private int video_style;
    private String verified_content;
    private String display_url;
    private List<ToutiaoSubImageBean> large_image_list;
    private long item_id;
    private boolean is_subject;
    private boolean show_portrait;
    private int repin_count;

    private int cell_flag;
    private ToutiaoNewsUserInfoBean user_info;
    private String source_open_url;
    private int level;
    private int like_count;
    private int digg_count;
    private int behot_time;
    private String article_alt_url;
    private long cursor; //可能为Long类型
    private String url;
    private int preload_web;
    private int user_repin;
    private boolean has_image;
    private int item_version;
    private ToutiaoMediaInfoBean media_info;
    private long group_id;
    private ToutiaoSubImageBean middle_image;

    public ToutiaoLogExtraBean getLog_extra() {
        return log_extra;
    }

    public void setLog_extra(ToutiaoLogExtraBean log_extra) {
        this.log_extra = log_extra;
    }

    public ToutiaoLogPd getLog_pb() {
        return log_pb;
    }

    public void setLog_pb(ToutiaoLogPd log_pb) {
        this.log_pb = log_pb;
    }

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
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

    public String getAbstract2() {
        return abstract2;
    }

    public void setAbstract2(String abstract2) {
        this.abstract2 = abstract2;
    }

    public List<ToutiaoSubImageBean> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<ToutiaoSubImageBean> image_list) {
        this.image_list = image_list;
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

    public ToutiaoForwardInfo getForward_info() {
        return forward_info;
    }

    public void setForward_info(ToutiaoForwardInfo forward_info) {
        this.forward_info = forward_info;
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

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public boolean isShow_portrait_article() {
        return show_portrait_article;
    }

    public void setShow_portrait_article(boolean show_portrait_article) {
        this.show_portrait_article = show_portrait_article;
    }

    public int getUser_verified() {
        return user_verified;
    }

    public void setUser_verified(int user_verified) {
        this.user_verified = user_verified;
    }

    public int getAggr_type() {
        return aggr_type;
    }

    public void setAggr_type(int aggr_type) {
        this.aggr_type = aggr_type;
    }

    public int getCell_type() {
        return cell_type;
    }

    public void setCell_type(int cell_type) {
        this.cell_type = cell_type;
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

    public int getIgnore_web_transform() {
        return ignore_web_transform;
    }

    public void setIgnore_web_transform(int ignore_web_transform) {
        this.ignore_web_transform = ignore_web_transform;
    }

    public int getSource_icon_style() {
        return source_icon_style;
    }

    public void setSource_icon_style(int source_icon_style) {
        this.source_icon_style = source_icon_style;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getHas_mp4_video() {
        return has_mp4_video;
    }

    public void setHas_mp4_video(int has_mp4_video) {
        this.has_mp4_video = has_mp4_video;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public List<ToutiaoFilterWords> getFilter_words() {
        return filter_words;
    }

    public void setFilter_words(List<ToutiaoFilterWords> filter_words) {
        this.filter_words = filter_words;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public int getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(int publish_time) {
        this.publish_time = publish_time;
    }

    public List<ToutiaoAction> getAction_list() {
        return action_list;
    }

    public void setAction_list(List<ToutiaoAction> action_list) {
        this.action_list = action_list;
    }

    public int getGallary_image_count() {
        return gallary_image_count;
    }

    public void setGallary_image_count(int gallary_image_count) {
        this.gallary_image_count = gallary_image_count;
    }

    public int getCell_layout_style() {
        return cell_layout_style;
    }

    public void setCell_layout_style(int cell_layout_style) {
        this.cell_layout_style = cell_layout_style;
    }

    public long getTag_id() {
        return tag_id;
    }

    public void setTag_id(long tag_id) {
        this.tag_id = tag_id;
    }

    public ToutiaoActionExtra getAction_extra() {
        return action_extra;
    }

    public void setAction_extra(ToutiaoActionExtra action_extra) {
        this.action_extra = action_extra;
    }

    public int getVideo_style() {
        return video_style;
    }

    public void setVideo_style(int video_style) {
        this.video_style = video_style;
    }

    public String getVerified_content() {
        return verified_content;
    }

    public void setVerified_content(String verified_content) {
        this.verified_content = verified_content;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
    }

    public List<ToutiaoSubImageBean> getLarge_image_list() {
        return large_image_list;
    }

    public void setLarge_image_list(List<ToutiaoSubImageBean> large_image_list) {
        this.large_image_list = large_image_list;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public boolean is_subject() {
        return is_subject;
    }

    public void setIs_subject(boolean is_subject) {
        this.is_subject = is_subject;
    }

    public boolean isShow_portrait() {
        return show_portrait;
    }

    public void setShow_portrait(boolean show_portrait) {
        this.show_portrait = show_portrait;
    }

    public int getRepin_count() {
        return repin_count;
    }

    public void setRepin_count(int repin_count) {
        this.repin_count = repin_count;
    }

    public int getCell_flag() {
        return cell_flag;
    }

    public void setCell_flag(int cell_flag) {
        this.cell_flag = cell_flag;
    }

    public ToutiaoNewsUserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(ToutiaoNewsUserInfoBean user_info) {
        this.user_info = user_info;
    }

    public String getSource_open_url() {
        return source_open_url;
    }

    public void setSource_open_url(String source_open_url) {
        this.source_open_url = source_open_url;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
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

    public String getArticle_alt_url() {
        return article_alt_url;
    }

    public void setArticle_alt_url(String article_alt_url) {
        this.article_alt_url = article_alt_url;
    }

    public long getCursor() {
        return cursor;
    }

    public void setCursor(long cursor) {
        this.cursor = cursor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPreload_web() {
        return preload_web;
    }

    public void setPreload_web(int preload_web) {
        this.preload_web = preload_web;
    }

    public int getUser_repin() {
        return user_repin;
    }

    public void setUser_repin(int user_repin) {
        this.user_repin = user_repin;
    }

    public boolean isHas_image() {
        return has_image;
    }

    public void setHas_image(boolean has_image) {
        this.has_image = has_image;
    }

    public int getItem_version() {
        return item_version;
    }

    public void setItem_version(int item_version) {
        this.item_version = item_version;
    }

    public ToutiaoMediaInfoBean getMedia_info() {
        return media_info;
    }

    public void setMedia_info(ToutiaoMediaInfoBean media_info) {
        this.media_info = media_info;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public ToutiaoSubImageBean getMiddle_image() {
        return middle_image;
    }

    public void setMiddle_image(ToutiaoSubImageBean middle_image) {
        this.middle_image = middle_image;
    }
    public int getGallary_style() {
        return gallary_style;
    }

    public void setGallary_style(int gallary_style) {
        this.gallary_style = gallary_style;
    }



}
