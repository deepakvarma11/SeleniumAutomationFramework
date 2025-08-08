package utils.driverUtils;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import utils.PropertyReader;
import utils.exceptions.BrowserException;

import java.util.Properties;

import static utils.PropertyReader.getPropertyValue;

public class Mutability {

    private Mutability() {
        // Private constructor to prevent instantiation
    }

    private static String headlessProperty = System.getProperty("headless", getPropertyValue("headless"));
    private static boolean headless = Boolean.parseBoolean(headlessProperty);

    public static MutableCapabilities getCapabilities(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                return getChromeCapabilities();
            case "edge":
                return getEdgeCapabilities();
            default:
                throw new BrowserException("Browser not supported: " + browser);
        }
    }

    private static ChromeOptions getChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");
        if ( headless) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    private static EdgeOptions getEdgeCapabilities() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--inprivate");
        if (headless) {
            options.addArguments("--headless=new");
        }
        return options;
    }
}