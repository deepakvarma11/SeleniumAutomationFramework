package utils.driverUtils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.LogFactory;
import utils.PropertyReader;
import utils.waitUtils.WaitUtil;

import java.util.Objects;

public class DriverManagement implements IdriverManagement {

    private static final Logger logger = LogFactory.getLogger(DriverManagement.class);
    private static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    public void startDriver() {
        if (Objects.isNull(tdriver.get())) {
            String browser = getBrowserType();
            logger.info("Starting the driver for browser: {}", browser);
            MutableCapabilities capabilities = Mutability.getCapabilities(browser);

            switch (browser.toLowerCase()) {
                case "chrome":
                    tdriver.set(new ChromeDriver((ChromeOptions) capabilities));
                    break;
                case "edge":
                    tdriver.set(new EdgeDriver((EdgeOptions) capabilities));
                    break;
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }

            maximizeWindow();
            WaitUtil.implicitWait();
        }
    }

    @Override
    public void closeDriver() {
        WebDriver driver = tdriver.get();
        if (driver != null) {
            logger.info("Closing the driver");
            driver.quit();
            tdriver.remove();
            logger.info("Driver closed");
        }
    }

    public static WebDriver getDriver() {
        return tdriver.get();
    }

    public void maximizeWindow() {
        tdriver.get().manage().window().maximize();
        logger.info("Maximizing the window");
    }

    public String getBrowserType() {
        return System.getProperty("browser", PropertyReader.getPropertyValue("browser"));
    }

    public void openUrl(String url) {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.get(url);
            logger.info("Opening URL: {}", url);
        }
    }
}
