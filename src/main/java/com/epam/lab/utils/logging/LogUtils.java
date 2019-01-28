package com.epam.lab.utils.logging;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogUtils {

    private static Map<Long, List<ITestLog>> logs;

    static {
        logs = new ConcurrentHashMap<>();
    }

    public static synchronized void putLog(ITestLog log) {
        logs.computeIfAbsent(Thread.currentThread().getId(), k -> new ArrayList<>()).add(log);
    }

    public static synchronized List<ITestLog> getLogsFromCurrentThread() {
        return logs.get(Thread.currentThread().getId());
    }

    public static synchronized void report() {
        logs.forEach((k, v) -> v.forEach(System.out::println));
    }

    public static synchronized void cleanLogs() {
        for (Long key : logs.keySet()) {
            logs.remove(key);
        }
    }
}
