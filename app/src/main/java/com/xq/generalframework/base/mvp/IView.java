package com.xq.generalframework.base.mvp;

/**
 * @author 小侨
 * @time 2017/7/20  15:32
 * @desc View层的共有属性
 */

public interface IView {

    /**
     * 全局的显示加载框
     */
    void showLoading(String msg);

    /**
     * 全局的隐藏加载框
     */
    void hideLoading();

    /**
     * 显示数据到View上
     **/
    void showData();

    /**
     * 提示用户,提升友好交互
     **/
    void showMessage();
}
