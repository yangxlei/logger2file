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
    public static void init(Context context, boolean sync2File) {
        sLogDir = context.getDir("Logger", Context.MODE_PRIVATE);
        sSyncFile = sync2File;
    }

    public static Logger getLogger(String tag) {
        if (sLoggerCache.get(tag) != null) {
            return sLoggerCache.get(tag);
        }

        Logger logger;
        synchronized (sLoggerCache) {
            if (sSyncFile) {
                File logFile = new File(sLogDir, tag);
                logger = new LoggerDelegate(new AndroidLogger(tag), new FileLogger(logFile));
            } else {
                logger = new LoggerDelegate(new AndroidLogger(tag));
            }
            sLoggerCache.put(tag, logger);
        }
        return logger;
    }
}
