package tests;

import constants.Functions;
import constants.TestType;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.ILoginPage;
import pageObject.LoginPage;

@Epic("Verify Login Functionality")
@Feature("Login Functionality")
public class LoginTest extends BaseTest {

    @Step("Login positive testcase")
    @Test(groups = {Functions.LOGIN, TestType.POSITIVE}, description = "Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void login_TC01() {
        LOGGER.info("login_TC01 Login with valid credentials");
        ILoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();
        loginPage.login("standard_user", "secret_sauce");
    }

    @Step("Login negative testcase")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {Functions.LOGIN, TestType.NEGATIVE}, description = "Login with invalid credentials")
    public void login_TC02() {
        ILoginPage loginPage = new LoginPage();
        loginPage.openLoginPage();
        loginPage.login("locked_out_user", "secret_s");
        LOGGER.info("Error message: {}", loginPage.getErrorMessage());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }
}
