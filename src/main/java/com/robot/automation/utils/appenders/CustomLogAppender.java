package com.robot.automation.utils.appenders;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CustomLogAppender extends AppenderBase<ILoggingEvent> {
    private static List<String> logs = new CopyOnWriteArrayList<>();

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        System.out.println(this.getClass().getSimpleName() + " " + iLoggingEvent.getLevel().levelStr + " "
                + new Date(iLoggingEvent.getTimeStamp())
                + " " + iLoggingEvent.getMessage() + " " + iLoggingEvent.getCallerData()[0].getLineNumber());
        logs.add(iLoggingEvent.getMessage()+"\n");
    }


    public List<String> getLogsList() {
        return logs;
    }

}
