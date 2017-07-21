package com.xq.mvprxre.generalframework.ui.movie;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xq.mvprxre.R;
import com.xq.mvprxre.generalframework.base.BaseActivity;
import com.xq.mvprxre.generalframework.bean.Movie;
import com.xq.mvprxre.generalframework.bean.Movies;

/**
 * @author 小侨
 * @time 2017/7/21  10:25
 * @desc movie页面View层
 */

public class MovieActivity extends BaseActivity<MoviePresenter> implements MovieContract.IView {

    // 互相关联
    // MovieActivity类中：extends BaseActivity<MoviePresenter>
    // MoviePresenter类中：extends BasePresenter<MovieActivity>

    private TextView mTv;

    @Override
    protected int setContentView() {
        return R.layout.activity_movie;
    }

    @Override
    protected void initView() {
        mTv = (TextView) findViewById(R.id.tv);
    }

    @Override
    protected MoviePresenter initPresenter() {
        return new MoviePresenter();
    }

    @Override
    public void showTop250(Movies movies) {
        mTv.setText("1 :   " + movies.toString());
    }

    @Override
    public void showMovieInfo(Movie movie) {
        mTv.setText("2 :   " + movie.toString());
    }

    @Override
    public void showLoading() {
        showToast("--- 下载中 ---");
    }

    @Override
    public void hideLoading() {
        showToast("+++ 下载完 +++");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void onJumpTo250(View view) {
        mPresenter.getTop250();
    }

    public void onJumpToInfo(View view) {
        mPresenter.getMovieInfo(1764789);
    }
}
