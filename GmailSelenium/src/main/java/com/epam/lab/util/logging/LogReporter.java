package com.epam.lab.util.logging;

import com.epam.lab.util.client.WebServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

public class LogReporter implements IReporter {
    private static final Logger LOG = LoggerFactory.getLogger(LogReporter.class);

    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {
        LogUtils.getLogs().forEach((k, v) -> LOG.info(WebServiceClient.sendLogs(v).toString()));
    }
}
