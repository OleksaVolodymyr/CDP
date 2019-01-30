package com.cdp.model;

import org.codehaus.jackson.annotate.JsonProperty;


import java.util.List;


public class Logs {

    @JsonProperty("logs")
    private List<TestLog> logsList;

    public List<TestLog> getLogsList() {
        return logsList;
    }

    public void setLogsList(List<TestLog> logsList) {
        this.logsList = logsList;
    }
}
