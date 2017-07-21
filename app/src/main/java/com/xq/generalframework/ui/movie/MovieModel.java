package com.xq.generalframework.ui.movie;

import com.xq.generalframework.base.IModel;

/**
 * @author 小侨
 * @time 2017/7/21  10:30
 * @desc movie页面Model层
 */

public class MovieModel implements IModel, MovieContract.IModel {

    @Override
    public String getData() {
        return "abc";
    }
}
