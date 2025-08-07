package tests;

import constants.Functions;
import constants.TestType;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.IProductPage;
import pageObject.LoginPage;
import pageObject.ProductPage;

@Epic("Verify Product Functionality")
@Feature("Product Functionality")
public class ProductTest extends BaseTest {

    @Step("Adding product to cart positive testcase")
    @Test(groups = {Functions.PRODUCTS, TestType.POSITIVE}, description = "Add product to cart")
    public void addProductToCart() {
        new LoginPage().openLoginPage().login("standard_user", "secret_sauce");
        IProductPage productPage = new ProductPage();
        productPage.getInventoryList().stream().forEach(LOGGER::info);
        productPage.addToCartProduct("Sauce Labs Backpack");
        LOGGER.info(productPage.getShoppingCartItemsNumber());
        Assert.assertEquals(productPage.getShoppingCartItemsNumber(), "1", "Product was not added to cart");
    }
}
