package com.xq.generalframework.ui.movie;

import com.xq.generalframework.base.BasePresenter;

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
    public void getData() {
        mView.showData(mModel.getData());
    }
}
