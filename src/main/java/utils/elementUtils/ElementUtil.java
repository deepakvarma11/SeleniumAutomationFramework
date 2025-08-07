package utils.elementUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static utils.driverUtils.DriverManagement.getDriver;

public class ElementUtil {

    public static Element findById(String id, String variableName) {
        return new Element(getDriver().findElement(By.id(id)), variableName);
    }

    public static Element findByXpath(String xpath, String variableName) {
        return new Element(getDriver().findElement(By.xpath(xpath)), variableName);
    }

    public static Element findByClassName(String className, String variableName) {
        return new Element(getDriver().findElement(By.className(className)), variableName);
    }

    public static Element findByName(String name, String variableName) {
        return new Element(getDriver().findElement(By.name(name)), variableName);
    }

    public static List<Element> findElementsByXpath(String xpath, String variableName) {
        return getDriver()
                .findElements(By.xpath(xpath)).stream()
                .map(ele -> new Element(ele, variableName)).collect(Collectors.toList());
    }

    public static Element find(WebElement element, String variableName) {
        return new Element(element, variableName);
    }


}
