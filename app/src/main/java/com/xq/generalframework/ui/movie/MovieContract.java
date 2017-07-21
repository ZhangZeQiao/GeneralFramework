package com.xq.generalframework.ui.movie;

/**
 * @author 小侨
 * @time 2017/7/21  10:23
 * @desc movie页面合约类
 */

public class MovieContract {

    /**
     * V视图层
     */
    interface IView {

        void showLoading();

        void hideLoading();

        void showData(String s);

    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IPresenter {

        void getData();

    }

    /**
     * M逻辑(业务)处理层
     */
    interface IModel {
        String getData();
    }
}
