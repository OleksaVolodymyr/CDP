package com.robot.automation.reporters;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

public class CustomReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {
        //System.out.println(CustomLogAppender.logs);

    }
}
