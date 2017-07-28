package com.xq.mvprxremd.generalframework.base.mvp;

/**
 * @author 小侨
 * @time 2017/7/21  10:17
 * @desc Presenter层接口/约束类
 */

public interface IPresenter {

    void attachView(IView view);

    void detachView();
}
