package com.epam.lab.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogUtils {

    private static final Logger LOG = LoggerFactory.getLogger(LogUtils.class);

    private static Map<Long, List<ITestLog>> logs;

    static {
        logs = new ConcurrentHashMap<>();
    }

    private LogUtils() {
    }

    public static synchronized void putLog(ITestLog log) {
        logs.computeIfAbsent(Thread.currentThread().getId(), k -> new ArrayList<>()).add(log);
    }

    public static synchronized List<ITestLog> getLogsFromCurrentThread() {
        return logs.get(Thread.currentThread().getId());
    }

    public static synchronized void report() {
        logs.forEach((k, v) -> v.forEach(l -> LOG.info(l.toString())));
    }

    public static Map<Long, List<ITestLog>> getLogs() {
        return logs;
    }

    public static synchronized void cleanLogs() {
        for (Long key : logs.keySet()) {
            logs.remove(key);
        }
    }

}
