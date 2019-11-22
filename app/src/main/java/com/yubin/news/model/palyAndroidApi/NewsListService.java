package com.yubin.news.model.palyAndroidApi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsListService {
    @GET("https://wanandroid.com/wxarticle/list/{id}/{pageNum}/json")
    Observable<DataBean<NewsListBean>> getCall(@Path("id") int id, @Path("pageNum") int pageNum);
}
