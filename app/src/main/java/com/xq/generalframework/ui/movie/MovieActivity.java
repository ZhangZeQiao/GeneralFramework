package com.xq.generalframework.ui.movie;

import android.support.v7.widget.RecyclerView;

import com.xq.generalframework.R;
import com.xq.generalframework.base.mvp.BaseActivity;
import com.xq.generalframework.base.mvp.IView;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @author 小侨
 * @time 2017/7/20  15:36
 * @desc movie页面的View
 */

public class MovieActivity extends BaseActivity<IView, MoviePresenter> {

    private PtrFrameLayout mStoreHousePtrFrame;
    private RecyclerView mRvMovieList;

    @Override
    protected int setContentView() {
        return R.layout.activity_movie;
    }

    @Override
    protected void initView() {
        mStoreHousePtrFrame = (PtrFrameLayout) findViewById(R.id.store_house_ptr_frame);
        mRvMovieList = (RecyclerView) findViewById(R.id.rv_movie_list);
    }

    @Override
    protected MoviePresenter initPresenter() {
        return new MoviePresenter();
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData() {
        mPresenter.showData();
    }

    @Override
    public void showMessage() {

    }
}
