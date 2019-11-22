package com.yubin.news.model.palyAndroidApi;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NewsTermListService {
    @GET("wxarticle/chapters/json")
    Observable<DataBean<List<NewsTermBean>>> getCall();
}
