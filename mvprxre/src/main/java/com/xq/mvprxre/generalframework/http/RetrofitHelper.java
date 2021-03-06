package com.xq.mvprxre.generalframework.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 小侨
 * @time 2017/7/21  14:52
 * @desc RetrofitHelper类
 * 主要用来构造retrofit对象以及得到接口类对象
 */

public class RetrofitHelper {

    private static final RetrofitHelper mRetrofitHelper = new RetrofitHelper();
    private Retrofit mRetrofit;

    private RetrofitHelper() {
        setRetrofit();
    }

    public static RetrofitHelper getInstance(){
        return mRetrofitHelper;
    }

    private void setRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/")
                .addConverterFactory(GsonConverterFactory.create()) // 添加 gson关联
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加 Rxjava关联
                .build();
    }

    public ApiService getApiService() {
        ApiService apiService = mRetrofit.create(ApiService.class);
        return apiService;
    }
}
