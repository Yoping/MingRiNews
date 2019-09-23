package com.yubin.news.model.toutiaoApi;

/**
 * Created by YUBIN on 2017/5/11.
 */

public class ToutiaoUserInfo {

    private int user_id;
    private String description;
    private String name;
    private String avatar_url;
    private Boolean user_verified;
    private String user_auth_info;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Boolean getUser_verified() {
        return user_verified;
    }

    public void setUser_verified(Boolean user_verified) {
        this.user_verified = user_verified;
    }

    public String getUser_auth_info() {
        return user_auth_info;
    }

    public void setUser_auth_info(String user_auth_info) {
        this.user_auth_info = user_auth_info;
    }




}
