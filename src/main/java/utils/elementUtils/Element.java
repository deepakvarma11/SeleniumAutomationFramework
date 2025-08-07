package utils.elementUtils;

import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import utils.LogFactory;

import java.util.List;

public class Element implements WebElement {

    private static final Logger logger = LogFactory.getLogger(Element.class);

    private WebElement webElement;
    private final String variableName;

    public Element(WebElement element, String variableName) {
        this.webElement = element;
        this.variableName = variableName;
    }

    public void sendKeys(String str) {
        logger.info("Sending keys to : {}", variableName);
        webElement.sendKeys(str);
    }

    @Override
    public void click() {
        logger.info("clicking : {}", variableName);
        webElement.click();
    }

    @Override
    public void submit() {
        logger.info("Clicked submit button");
        webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        logger.info("Sending keys: {} to element: {}", String.join(", ", keysToSend), variableName);
        webElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        logger.info("Clearing text from element: {}", variableName);
        webElement.clear();
    }

    @Override
    public String getTagName() {
        logger.info("Getting tag name of element: {}", variableName);
        return webElement.getTagName();
    }

    @Override
    public @Nullable String getAttribute(String name) {
        logger.info("Getting attribute '{}' from element: {}", name, variableName);
        return webElement.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        logger.info("Checking if element: {} is selected", variableName);
        return webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        logger.info("Checking if element: {} is enabled", variableName);
        return webElement.isEnabled();
    }

    @Override
    public String getText() {
        logger.info("Getting text from element: {}", variableName);
        return webElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if element: {} is displayed", variableName);
        return webElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return webElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        return webElement.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }
}
