package com.xq.mvprxre.generalframework.ui.movie;

import com.xq.mvprxre.generalframework.base.BasePresenter;
import com.xq.mvprxre.generalframework.bean.Movie;
import com.xq.mvprxre.generalframework.bean.Movies;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 小侨
 * @time 2017/7/21  10:25
 * @desc movie页面Presenter层
 */

public class MoviePresenter extends BasePresenter<MovieActivity, MovieModel> implements MovieContract.IPresenter {

    // MoviePresenter<MovieActivity>已经在MovieActivity中onCreate时绑定了
    // MoviePresenter<MovieModel>已经在MoviePresenter新建时调用initModel()方法绑定了
    @Override
    protected MovieModel initModel() {
        return new MovieModel();
    }

    @Override
    public void getTop250() {
        mModel.downloadTop250()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movies>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showLoading();

                        // TODO: 2017/7/21 这里不用写做 mView != null 判断，是因为在 BaseActivity已经做了 onDestroy() 时解绑了
                    }

                    @Override
                    public void onNext(Movies movies) {
                        mView.showTop250(movies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void getMovieInfo(int id) {
        mModel.downloadMovieInfo(id)
                .subscribeOn(Schedulers.io()) // 另开一个线程来下载（指定一个观察者在哪个调度器上观察这个Observable）
                .observeOn(AndroidSchedulers.mainThread()) // 下载完回到主线程（指定Observable自身在哪个调度器上执行）
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(Movie movie) {
                        mView.showMovieInfo(movie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }
}
