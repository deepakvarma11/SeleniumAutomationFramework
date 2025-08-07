package pageObject;

public interface ILoginPage {

    LoginPage openLoginPage();

    LoginPage enterUsername(String username);

    LoginPage enterPassword(String password);

    void clickLoginButton();

    void login(String username, String password);

    String getErrorMessage();
}
