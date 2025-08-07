package pageObject;

import org.openqa.selenium.WebElement;
import utils.PropertyReader;
import utils.waitUtils.WaitUtil;

import static utils.driverUtils.DriverManagement.getDriver;
import static utils.elementUtils.ElementUtil.*;

public class LoginPage implements ILoginPage {

    private String username = "user-name";
    private String password = "password";
    private String loginButton = "login-button";
    private String errorMessage = "//h3[@data-test='error']";

    public LoginPage openLoginPage() {
        getDriver().get(PropertyReader.getPropertyValue("url"));
        return this;
    }

    public LoginPage enterUsername(String username) {
        WebElement userName = WaitUtil
                .waitForElementToBeVisible(findById(this.username, "username"));
        find(userName, "username").sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        findById(this.password, "password").sendKeys(password);
        return this;
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void clickLoginButton() {
        findById(this.loginButton, "login btn").click();
    }

    public String getErrorMessage() {
        return findByXpath(this.errorMessage, "error message").getText();
    }
}
