package com.epam.lab.listener;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {

	private static final Logger LOG = Logger.getLogger(CustomTestListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		LOG.info(String.format("Test : %s , Method : %s, Started in : %s  ", result.getName(), result.getMethod(),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result.getStartMillis())));

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

}
