package io.github.yangxiaolei.demo;

/**
 * Created by yanglei on 2017/7/3.
 */

class LoggerDelegate implements Logger {

    private Logger[] mLoggers;
    LoggerDelegate(Logger... loggers) {
        mLoggers = loggers;
    }

    @Override
    public void verbose(String text) {
        for (Logger logger : mLoggers) {
            logger.verbose(text);
        }
    }

    @Override
    public void info(String text) {
        for (Logger logger : mLoggers) {
            logger.info(text);
        }
    }

    @Override
    public void warn(String text) {
        for (Logger logger : mLoggers) {
            logger.warn(text);
        }
    }

    @Override
    public void debug(String text) {
        for (Logger logger : mLoggers) {
            logger.debug(text);
        }
    }

    @Override
    public void error(String text) {
        for (Logger logger : mLoggers) {
            logger.error(text);
        }
    }

    @Override
    public void error(String text, Throwable throwable) {
        for (Logger logger : mLoggers) {
            logger.error(text, throwable);
        }
    }
}
