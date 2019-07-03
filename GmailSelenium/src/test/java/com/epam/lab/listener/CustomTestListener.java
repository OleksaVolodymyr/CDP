package com.epam.lab.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

import java.text.SimpleDateFormat;

public class CustomTestListener implements ITestListener, IInvokedMethodListener {

    private static final Logger LOG = LoggerFactory.getLogger(CustomTestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("Test : {} , Method : {}, Started in : {}  ", result.getName(), result.getMethod(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getStartMillis()));

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info(String.format("Test : %s , Method : %s passed successful in : %s  ", result.getName(),
                result.getMethod(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getEndMillis())));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.error(String.format("Test : %s , Method : %s failed in : %s  ", result.getName(), result.getMethod(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getEndMillis())));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOG.warn(String.format("Test : %s , Method : %s was skipped ", result.getName(), result.getMethod()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LOG.error(String.format("Test : %s , Method : %s failed but within success precentage in : %s  ",
                result.getName(), result.getMethod(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getEndMillis())));
    }

    @Override
    public void onStart(ITestContext context) {
        LOG.info(String.format("Starting tests : %s, Output directory : %s, Test Suite : %s, Started in %s  ",
                context.getName(), context.getOutputDirectory(), context.getSuite(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(context.getStartDate())));
    }

    @Override
    public void onFinish(ITestContext context) {
        LOG.info(String.format("Finish tests : %s, Output directory : %s, Test Suite : %s, Finished in %s  ",
                context.getName(), context.getOutputDirectory(), context.getSuite(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(context.getEndDate())));

    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
       /* List<ITestLog> logs = LogUtils.getLogsFromCurrentThread();
        LogUtils.cleanLogs();
        if (logs != null && !logs.isEmpty()) {
            logs.forEach(System.out::println);
        }*/
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }
}
