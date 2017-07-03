package io.github.yangxiaolei.demo;

/**
 * Created by yanglei on 2017/7/3.
 */
public interface Logger {

    void verbose(String text);

    void info(String text);

    void warn(String text);

    void debug(String text);

    void error(String text);

    void error(String text, Throwable throwable);
}
