package com.xq.mvprxremd.generalframework.ui.movie;

import com.xq.mvprxremd.generalframework.base.mvp.IModel;
import com.xq.mvprxremd.generalframework.bean.Movie;
import com.xq.mvprxremd.generalframework.bean.Movies;
import com.xq.mvprxremd.generalframework.http.ApiService;
import com.xq.mvprxremd.generalframework.http.RetrofitHelper;

import io.reactivex.Observable;

/**
 * @author 小侨
 * @time 2017/7/21  10:30
 * @desc movie页面Model层
 */

public class MovieModel implements IModel, MovieContract.IModel {

    @Override
    public Observable<Movies> downloadMovies(int start, int count) {
        ApiService apiService = RetrofitHelper.getInstance().getApiService();
        return apiService.getMoreMovies(start, count);
    }

    @Override
    public Observable<Movies> downloadMoreMovies(int start, int count) {
        ApiService apiService = RetrofitHelper.getInstance().getApiService();
        return apiService.getMoreMovies(start, count);
    }

    @Override
    public Observable<Movie> downloadMovieById(int id) {
        ApiService apiService = RetrofitHelper.getInstance().getApiService();
        return apiService.getMoviesById(id);
    }
}
