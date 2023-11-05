package manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    Logger logger = LoggerFactory.getLogger(TestNGListener.class);
    private long timeStart, timeFinish;

    @Override
    public void onTestStart(ITestResult result) {
        timeStart = System.currentTimeMillis();
        logger.info("stared test interface: " + result.getName());
        ITestListener.super.onTestStart(result);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        timeFinish = System.currentTimeMillis();
        logger.info("test success interface: " + result.getName() + "duration: " +(timeFinish - timeStart));
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        timeFinish = System.currentTimeMillis();
        logger.info("test failure interface: " + result.getName() + "duration: " +(timeFinish - timeStart));
        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        timeFinish = System.currentTimeMillis();
        logger.info("test skipped interface: " + result.getName() + " duration: " +(timeFinish - timeStart));
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logger.info("test out of time: " + result.getName());
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("test start interface: " + context.getName());
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("all code executed: passed tests - " + context.getPassedTests());
        logger.info("all code executed: failed tests - " + context.getFailedTests());
        ITestListener.super.onFinish(context);
    }
}
