package io.github.yangxiaolei.demo;

import android.util.Log;

/**
 * Created by yanglei on 2017/7/3.
 */

class AndroidLogger implements Logger {

    private String mTag;
    public AndroidLogger(String tag) {
        this.mTag = tag;
    }

    @Override
    public void verbose(String text) {
        Log.v(mTag, text);
    }

    @Override
    public void info(String text) {
        Log.i(mTag, text);
    }

    @Override
    public void warn(String text) {
        Log.w(mTag, text);
    }

    @Override
    public void debug(String text) {
        Log.d(mTag, text);
    }

    @Override
    public void error(String text) {
        Log.e(mTag, text);
    }

    @Override
    public void error(String text, Throwable throwable) {
        Log.e(mTag, text, throwable);
    }
}
