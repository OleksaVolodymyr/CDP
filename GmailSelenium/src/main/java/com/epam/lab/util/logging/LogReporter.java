package com.epam.lab.util.logging;

import com.epam.lab.util.client.WebServiceClient;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

public class LogReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {
        LogUtils.getLogs().forEach((k, v) -> System.out.println(WebServiceClient.sendLogs(v)));
    }
}
