package com.xq.mvprxremd.generalframework.ui.movie;

import com.xq.mvprxremd.generalframework.bean.Movie;
import com.xq.mvprxremd.generalframework.bean.Movies;

import io.reactivex.Observable;

/**
 * @author 小侨
 * @time 2017/7/21  10:23
 * @desc movie页面合约类：用于约束/定义movie页面的特有方法
 */

public class MovieContract {

    /**
     * View
     */
    interface IView {

        void showMovies(Movies movies);

        void showMoreMovies(Movies movies);

        void showMovieById(Movie movie);

    }

    /**
     * Prezenter
     */
    interface IPresenter {

        void getMovies(int start, int count);

        void getMoreMovies(int start, int count);

        void getMovieById(int id);

    }

    /**
     * Model
     */
    interface IModel {

        Observable<Movies> downloadMovies(int start, int count);

        Observable<Movies> downloadMoreMovies(int start, int count);

        Observable<Movie> downloadMovieById(int id);

    }
}
