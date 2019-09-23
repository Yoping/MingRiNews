package com.yubin.news.model.youkuApi;

/**
 * Created by YUBIN on18-2-7 上午11:27
 * Last modified 18-2-7 上午11:27
 *
 */

public class YoukuCommentBean {

    private int imageUrl;
    private String name;
    private int upCount;
    private String comment;
    private String time;
    private int replyCount;

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUpCount() {
        return upCount;
    }

    public void setUpCount(int upCount) {
        this.upCount = upCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }


}
