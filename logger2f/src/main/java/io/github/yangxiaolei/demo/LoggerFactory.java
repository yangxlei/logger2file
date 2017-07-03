package io.github.yangxiaolei.demo;

import android.content.Context;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanglei on 2017/7/3.
 */

public class LoggerFactory {

    private static File sLogDir;
    private static boolean sSyncFile;
    private static Map<String, Logger> sLoggerCache = new HashMap<>();
    private static boolean sDebugMode = true;

    /**
     * 初始化日志配置
     * @param context
     * @param sync2File 是否写文件
     * @param isDebug 非 debug 时，关闭系统 Logger
     */
    public static void init(Context context, boolean sync2File, boolean isDebug) {
        sLogDir = context.getDir("Logger", Context.MODE_PRIVATE);
        sSyncFile = sync2File;
        sDebugMode = isDebug;
    }

    public static Logger getLogger(String tag) {
        if (sLoggerCache.get(tag) != null) {
            return sLoggerCache.get(tag);
        }

        Logger logger;
        synchronized (sLoggerCache) {
            if (sSyncFile) {
                File logFile = new File(sLogDir, tag);
                if (sDebugMode) {
                    logger = new LoggerDelegate(new AndroidLogger(tag), new FileLogger(logFile));
                } else {
                    logger = new LoggerDelegate(new FileLogger(logFile));
                }
            } else {
                if (sDebugMode) {
                    logger = new LoggerDelegate(new AndroidLogger(tag));
                } else {
                    logger = new LoggerDelegate();
                }
            }
            sLoggerCache.put(tag, logger);
        }
        return logger;
    }
}
