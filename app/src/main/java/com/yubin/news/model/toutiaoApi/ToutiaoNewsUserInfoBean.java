package com.yubin.news.model.toutiaoApi;

/**
 * Created by YUBIN on 2017/5/25.
 */

public class ToutiaoNewsUserInfoBean {

    private String verified_content;
    private String avatar_url;
    private long user_id;//int 或者Long类型
    private String name;
    private int follower_count;
    private boolean follow;
    private ToutiaoNewsUserAuthInfoBean user_auth_info;
    private boolean user_verified;
    private String description;

    public String getVerified_content() {
        return verified_content;
    }

    public void setVerified_content(String verified_content) {
        this.verified_content = verified_content;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(int follower_count) {
        this.follower_count = follower_count;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public ToutiaoNewsUserAuthInfoBean getUser_auth_info() {
        return user_auth_info;
    }

    public void setUser_auth_info(ToutiaoNewsUserAuthInfoBean user_auth_info) {
        this.user_auth_info = user_auth_info;
    }

    public boolean isUser_verified() {
        return user_verified;
    }

    public void setUser_verified(boolean user_verified) {
        this.user_verified = user_verified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
