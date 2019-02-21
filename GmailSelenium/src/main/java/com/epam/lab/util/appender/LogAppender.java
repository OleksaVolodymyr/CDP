package com.epam.lab.util.appender;


import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.epam.lab.util.logging.ITestLog;
import com.epam.lab.util.logging.LogUtils;
import com.epam.lab.util.logging.TestLog;


public class LogAppender extends AppenderBase<LoggingEvent> {

    @Override
    protected void append(LoggingEvent loggingEvent) {
        ITestLog log = new TestLog().setDate(loggingEvent.getTimeStamp())
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
