package com.yubin.news.model.toutiaoApi;

/**
 * Created by YUBIN on 2017/5/11.
 */

public class ToutiaoMediaInfoBean {
    private String auth_info;
    private String name;
    private String avatar_url;
    private long media_id;
    private Boolean user_verified;
//    private String user_auth_info;
    public String getAuth_info() {
        return auth_info;
    }

    public void setAuth_info(String auth_info) {
        this.auth_info = auth_info;
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

    public long getMedia_id() {
        return media_id;
    }

    public void setMedia_id(long media_id) {
        this.media_id = media_id;
    }

    public Boolean getUser_verified() {
        return user_verified;
    }

    public void setUser_verified(Boolean user_verified) {
        this.user_verified = user_verified;
    }

//    public String getUser_auth_info() {
//        return user_auth_info;
//    }
//
//    public void setUser_auth_info(String user_auth_info) {
//        this.user_auth_info = user_auth_info;
//    }


}
