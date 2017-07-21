package com.xq.mvprxre.generalframework.http;

import com.xq.mvprxre.generalframework.bean.Movie;
import com.xq.mvprxre.generalframework.bean.Movies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author 小侨
 * @time 2017/7/21  14:44
 * @desc 数据接口类
 */

public interface ApiService {

    // 豆瓣250：http://api.douban.com/v2/movie/top250
    @GET("v2/movie/top250")
    Observable<Movies> getMovies250();


    // 条目信息：http://api.douban.com/v2/movie/subject/1764789
    @GET("v2/movie/subject/{id}")
    Observable<Movie> getMoviesById(@Path("id") int id);
}
