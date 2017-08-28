package com.xq.mvprxre.generalframework.ui.movie;

import com.xq.mvprxre.generalframework.base.mvp.IModel;
import com.xq.mvprxre.generalframework.bean.Movie;
import com.xq.mvprxre.generalframework.bean.Movies;
import com.xq.mvprxre.generalframework.http.ApiService;
import com.xq.mvprxre.generalframework.http.RetrofitHelper;

import io.reactivex.Observable;

/**
 * @author 小侨
 * @time 2017/7/21  10:30
 * @desc movie页面Model层
 */

public class MovieModel implements IModel, MovieContract.IModel {

    @Override
    public Observable<Movies> downloadTop250() {
        ApiService apiService = RetrofitHelper.getInstance().getApiService();
        return apiService.getMovies250();
    }

    @Override
    public Observable<Movie> downloadMovieInfo(int id) {
        ApiService apiService = RetrofitHelper.getInstance().getApiService();
        return apiService.getMoviesById(id);
    }
}
