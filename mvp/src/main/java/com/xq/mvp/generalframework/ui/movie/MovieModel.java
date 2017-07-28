package com.xq.mvp.generalframework.ui.movie;

import com.xq.mvp.generalframework.base.mvp.IModel;

/**
 * @author 小侨
 * @time 2017/7/21  10:30
 * @desc movie页面Model层
 */

public class MovieModel implements IModel, MovieContract.IModel {

    @Override
    public String downloadData() {
        return "abc";
    }
}
