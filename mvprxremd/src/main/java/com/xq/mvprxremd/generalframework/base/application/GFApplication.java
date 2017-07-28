package com.xq.mvprxremd.generalframework.base.application;

import android.app.Application;
import android.content.Context;

/**
 * @author 小侨
 * @time 2017/7/26  16:27
 * @desc 自定义 Application
 */

public class GFApplication extends Application {

    // TODO: 在项目中，我们在一些工具类采用了单例模式，其生命周期和整个应用程序相同，
    // 并且可能直接或者间接的需要Context引用来进行获取资源的操作。那么我们需要一个全局Context也就是Application。
    private static Context mApplicationContext;

    // TODO: 在Application中的onCreate()方法里去初始化各种全局的变量数据是一种比较推荐的做法。
    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
    }

    public static Context getContext() {
        return mApplicationContext;
    }

    // TODO: Application全局只有一个，它本身就已经是单例了，无需再用单例模式去为它做多重实例保护了。
}
