package com.xq.mvprxremd.generalframework.base.mvp;

/**
 * @author 小侨
 * @time 2017/7/21  10:17
 * @desc View层接口/约束类
 */

public interface IView {

    void showLoading();

    void hideLoading();

    void showToast(String message);
}
