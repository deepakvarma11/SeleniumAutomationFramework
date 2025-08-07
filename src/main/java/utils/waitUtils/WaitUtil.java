package utils.waitUtils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LogFactory;
import utils.PropertyReader;
import utils.driverUtils.DriverManagement;

import java.time.Duration;

public class WaitUtil {

    private static final int wait = Integer.parseInt(PropertyReader.getPropertyValue("wait"));

    public static void implicitWait() {
        DriverManagement.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        LogFactory.logger().info("Implicit wait set to 30 seconds");
    }

    public static WebElement waitForElementToBeVisible(WebElement element) {
        try {
            new WebDriverWait(DriverManagement.getDriver(), Duration.ofSeconds(wait))
                    .until(ExpectedConditions.visibilityOf(element));
            LogFactory.logger().info("Element is visible: " + element);
            return element;
        } catch (Exception e) {
            LogFactory.logger().error("Element not visible after waiting: " + element, e);
            throw e;
        }
    }
}
