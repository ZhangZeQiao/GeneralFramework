package com.xq.mvprxre.generalframework.base;

import com.xq.mvprxre.generalframework.base.mvp.IModel;
import com.xq.mvprxre.generalframework.base.mvp.IPresenter;
import com.xq.mvprxre.generalframework.base.mvp.IView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author 小侨
 * @time 2017/7/21  10:11
 * @desc BasePresenter基类
 */

public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter {

    protected V mView;
    protected M mModel;
    // 用于取消订阅：http://blog.csdn.net/tyrantu1989/article/details/69053816
    // RxJava 2.0 CompositeDisposable取代CompositeSubscription
    protected CompositeDisposable mCompositeDisposable;

    public BasePresenter() {
        mModel = initModel();
        mCompositeDisposable = new CompositeDisposable();
    }

    protected abstract M initModel();

    @Override
    public void attachView(IView view) {
        mView = (V) view;
    }

    @Override
    public void detachView() {
        // activity销毁的时候，切断所有订阅关系
        mCompositeDisposable.clear();
        mView = null;
    }
}
