package com.xq.mvp.generalframework.base;

import com.xq.mvp.generalframework.base.mvp.IModel;
import com.xq.mvp.generalframework.base.mvp.IPresenter;
import com.xq.mvp.generalframework.base.mvp.IView;

/**
 * @author 小侨
 * @time 2017/7/21  10:11
 * @desc BasePresenter基类
 */

public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter {

    protected V mView;
    protected M mModel;

    public BasePresenter() {
        mModel = initModel();
    }

    protected abstract M initModel();

    @Override
    public void attachView(IView view) {
        mView = (V) view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
