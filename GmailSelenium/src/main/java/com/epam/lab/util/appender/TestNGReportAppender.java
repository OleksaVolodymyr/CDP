package com.epam.lab.util.appender;


import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.testng.Reporter;

public class TestNGReportAppender extends AppenderBase<LoggingEvent> {

    @Override
    protected void append(final LoggingEvent event) {
        Reporter.log("<p>" + eventToString(event) + "</p>");
    }

    private String eventToString(final LoggingEvent event) {
        final StringBuilder result = new StringBuilder(event.getFormattedMessage());
        return result.toString();
    }
}
