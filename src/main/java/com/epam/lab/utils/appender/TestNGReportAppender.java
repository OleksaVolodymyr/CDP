package com.epam.lab.utils.appender;

import ch.qos.logback.core.AppenderBase;
import org.slf4j.event.LoggingEvent;
import org.testng.Reporter;

public class TestNGReportAppender extends AppenderBase<LoggingEvent> {

    @Override
    protected void append(final LoggingEvent event) {
        Reporter.log("<p>" + eventToString(event) + "</p>");
    }

    private String eventToString(final LoggingEvent event) {
        final StringBuilder result = new StringBuilder("");

        return result.toString();
    }
}
