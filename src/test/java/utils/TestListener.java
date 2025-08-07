package utils;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.driverUtils.DriverManagement;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveTextLog(result.getMethod().getMethodName() + " failed!");
        saveTextLog(result.getThrowable().getMessage() + " error occurred!");
        attachScreenshotToAllure();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    private void captureScreenshot(ITestResult result) {
        File screenshot = ((TakesScreenshot) DriverManagement.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            // Save the screenshot to a desired location
            FileUtils.copyFile(screenshot, new File("target/screenshots/" + result.getName() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void attachScreenshotToAllure() {
        byte[] screenshotBytes = ((TakesScreenshot) DriverManagement.getDriver()).getScreenshotAs(OutputType.BYTES);
        new ScreenshotHelper().attachScreenshot(screenshotBytes);
    }

    private void saveTextLog(String message) {
        new ScreenshotHelper().attachRequestDetails(message);
    }
}
