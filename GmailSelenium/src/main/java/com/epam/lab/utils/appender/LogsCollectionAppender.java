package com.epam.lab.utils.appender;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.epam.lab.utils.logging.ITestLog;
import com.epam.lab.utils.logging.LogUtils;
import com.epam.lab.utils.logging.TestLog;

public class LogsCollectionAppender extends AppenderBase<LoggingEvent> {

    private ITestLog log;

    @Override
    protected void append(LoggingEvent loggingEvent) {
        log = new TestLog().setDate(loggingEvent.getTimeStamp())
                .setLevel(loggingEvent.getLevel().toString())
                .setThreadName(loggingEvent.getThreadName())
                .setClassName(loggingEvent.getCallerData()[0].getClassName())
                .setLineNumber(loggingEvent.getCallerData()[0].getLineNumber())
                .setMethodName(loggingEvent.getCallerData()[0].getMethodName())
                .setMessage(loggingEvent.getMessage())
                .build();
        LogUtils.putLog(log);
    }


}
