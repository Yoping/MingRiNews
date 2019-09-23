package com.yubin.news.model.youkuApi;

import java.util.List;

/**
 * Created by YUBIN on 2017/5/15.
 */

public class YoukuCategoryBean {

    private String id;
    private String term;
    private String label;
    private String lang;
    private List<YoukuGenreBean> genres;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<YoukuGenreBean> getGenres() {
        return genres;
    }

    public void setGenres(List<YoukuGenreBean> genres) {
        this.genres = genres;
    }


}
