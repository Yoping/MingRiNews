package com.yubin.news.model.youkuApi;

/**
 * Created by YUBIN on 2017/5/15.
 */

public class YoukuVideoBean {


    private String id;
    private int comment_count;
    private String thumbnail;
    private String link;
    private String published;
    private int down_count;
    private String[] operation_limit;
    private String title;
    private int up_count;
    private String tags;
    private String public_type;
    private int duration;
    private int day_vv;
    private String[] streamtypes;
    private String thumbnail_v2;
    private String bigThumbnail;
    private int favorite_count;
    private String state;
    private String category;
    private YoukuUserBean user;
    private int view_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public int getDown_count() {
        return down_count;
    }

    public void setDown_count(int down_count) {
        this.down_count = down_count;
    }

    public String[] getOperation_limit() {
        return operation_limit;
    }

    public void setOperation_limit(String[] operation_limit) {
        this.operation_limit = operation_limit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUp_count() {
        return up_count;
    }

    public void setUp_count(int up_count) {
        this.up_count = up_count;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPublic_type() {
        return public_type;
    }

    public void setPublic_type(String public_type) {
        this.public_type = public_type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDay_vv() {
        return day_vv;
    }

    public void setDay_vv(int day_vv) {
        this.day_vv = day_vv;
    }

    public String[] getStreamtypes() {
        return streamtypes;
    }

    public void setStreamtypes(String[] streamtypes) {
        this.streamtypes = streamtypes;
    }

    public String getThumbnail_v2() {
        return thumbnail_v2;
    }

    public void setThumbnail_v2(String thumbnail_v2) {
        this.thumbnail_v2 = thumbnail_v2;
    }

    public String getBigThumbnail() {
        return bigThumbnail;
    }

    public void setBigThumbnail(String bigThumbnail) {
        this.bigThumbnail = bigThumbnail;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public YoukuUserBean getUser() {
        return user;
    }

    public void setUser(YoukuUserBean user) {
        this.user = user;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }


}
