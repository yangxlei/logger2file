package io.github.yangxiaolei.sample;

import android.app.Application;

import io.github.yangxiaolei.demo.LoggerFactory;

/**
 * Created by yanglei on 2017/7/3.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerFactory.init(this, true);
    }
}
