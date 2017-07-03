package io.github.yangxiaolei.demo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanglei on 2017/7/3.
 */

class FileLogger implements Logger {

    private FileHandle mFileHandle;
    private LinkedBlockingQueue<LogElement> mQueue;
    private Thread mLogThread;

    public FileLogger(File file) {
        mFileHandle = new FileHandle(file);
        mQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void verbose(String text) {
        mQueue.offer(new LogElement("VERBOSE", text));
        checkAndStartThread();
    }

    @Override
    public void info(String text) {
        mQueue.offer(new LogElement("INFO", text));
        checkAndStartThread();
    }

    @Override
    public void warn(String text) {
        mQueue.offer(new LogElement("WARN", text));
        checkAndStartThread();
    }

    @Override
    public void debug(String text) {
        mQueue.offer(new LogElement("DEBUG", text));
        checkAndStartThread();
    }

    @Override
    public void error(String text) {
        mQueue.offer(new LogElement("ERROR", text));
        checkAndStartThread();
    }

    @Override
    public void error(String text, Throwable throwable) {
        mQueue.offer(new LogElement("ERROR", text, throwable));
        checkAndStartThread();
    }

    private void checkAndStartThread() {
        if (mLogThread != null) return;
        mLogThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!mFileHandle.isOpen())
                        mFileHandle.open();

                    LogElement element;

                    while ((element = mQueue.poll(5, TimeUnit.SECONDS)) != null) {
                        String log = String.format(Locale.getDefault(), "[%s][%s]--%s", getTimestampText(element.timestamp), element.level, element.text);
                        mFileHandle.append(log);
                        if (element.throwable != null) {
                            mFileHandle.append(element.throwable.toString());
                            StackTraceElement[] trace = element.throwable.getStackTrace();
                            for (StackTraceElement traceElement : trace)
                                mFileHandle.append("\tat " + traceElement);

                            if (element.throwable.getCause() != null) {
                                mFileHandle.append(element.throwable.getCause().toString());
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mFileHandle.close();
                mLogThread = null;
            }
        });
        mLogThread.setName("FileLogger");
        mLogThread.setDaemon(true);
        mLogThread.start();
    }

    private static class LogElement {
        long timestamp;
        String level;
        String text;
        Throwable throwable;

        LogElement(String level, String text) {
            this(level, text, null);
        }

        LogElement(String level, String text, Throwable throwable) {
            this.timestamp = System.currentTimeMillis();
            this.level = level;
            this.text = text;
            this.throwable = throwable;
        }
    }

    private static String getTimestampText(long timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSSZ");
        return format.format(timestamp);
    }
}
