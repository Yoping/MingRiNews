package com.yubin.news.model.toutiaoApi;

/**
 * Created by YUBIN on 2017/5/11.
 */

public class ToutiaoGalleryBean {

        private String sub_abstract;
    private String sub_title;
    private ToutiaoSubImageBean sub_image;

    public String getSub_abstract() {
        return sub_abstract;
    }

    public void setSub_abstract(String sub_abstract) {
        this.sub_abstract = sub_abstract;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public ToutiaoSubImageBean getSub_image() {
        return sub_image;
    }

    public void setSub_image(ToutiaoSubImageBean sub_image) {
        this.sub_image = sub_image;
    }


}
