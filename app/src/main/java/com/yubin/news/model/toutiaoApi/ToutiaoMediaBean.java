package com.yubin.news.model.toutiaoApi;

/**
 * Created by YUBIN on 2017/5/11.
 */

public class ToutiaoMediaBean {

    private ToutiaoPraiseDataBean praise_data;
    private String description;
    /**
     * 该内容是一个对象，但是数据却是String类型
     */
    private String pgc_custom_menu;
    private ToutiaoAuthorsBean authors;
    private boolean can_be_praised;
    private String name;
    private int creator_id;
    private String avatar_url;
    private boolean v;
    private int id;
    private String auth_info;
    private boolean show_pgc_component;
    private boolean user_verified;
    private String user_auth_info;

    public ToutiaoPraiseDataBean getPraise_data() {
        return praise_data;
    }

    public void setPraise_data(ToutiaoPraiseDataBean praise_data) {
        this.praise_data = praise_data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPgc_custom_menu() {
        return pgc_custom_menu;
    }

    public void setPgc_custom_menu(String pgc_custom_menu) {
        this.pgc_custom_menu = pgc_custom_menu;
    }

    public ToutiaoAuthorsBean getAuthors() {
        return authors;
    }

    public void setAuthors(ToutiaoAuthorsBean authors) {
        this.authors = authors;
    }

    public boolean isCan_be_praised() {
        return can_be_praised;
    }

    public void setCan_be_praised(boolean can_be_praised) {
        this.can_be_praised = can_be_praised;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public boolean isV() {
        return v;
    }

    public void setV(boolean v) {
        this.v = v;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuth_info() {
        return auth_info;
    }

    public void setAuth_info(String auth_info) {
        this.auth_info = auth_info;
    }

    public boolean isShow_pgc_component() {
        return show_pgc_component;
    }

    public void setShow_pgc_component(boolean show_pgc_component) {
        this.show_pgc_component = show_pgc_component;
    }

    public boolean isUser_verified() {
        return user_verified;
    }

    public void setUser_verified(boolean user_verified) {
        this.user_verified = user_verified;
    }

    public String getUser_auth_info() {
        return user_auth_info;
    }

    public void setUser_auth_info(String user_auth_info) {
        this.user_auth_info = user_auth_info;
    }




}
