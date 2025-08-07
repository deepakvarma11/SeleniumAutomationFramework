package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.PropertyReader;
import utils.driverUtils.DriverManagement;
import utils.driverUtils.IdriverManagement;

public class BaseTest {

    protected static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
    private static final ThreadLocal<IdriverManagement> driverManager = new ThreadLocal<>();

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        LOGGER.info("Starting test suite setup");
    }

    @Step("Setting up the driver")
    @BeforeMethod(alwaysRun = true)
    public void setup() {
        LOGGER.info("Starting test method setup");
        driverManager.set(new DriverManagement());
        driverManager.get().startDriver();
        String url = PropertyReader.getPropertyValue("url");
        LOGGER.info("Opening URL: {}", url);
    }

    @Step("Closing the driver after test method")
    @AfterMethod(alwaysRun = true)
    public void cleanup(ITestResult result) {
        if (driverManager.get() != null) {
            driverManager.get().closeDriver();
            driverManager.remove();
            LOGGER.info("Driver closed for test method");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void suiteTearDown() {
        LOGGER.info("Completing test suite tear down");
    }

    protected IdriverManagement getDriverManager() {
        return driverManager.get();
    }

}
