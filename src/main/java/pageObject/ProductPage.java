package pageObject;

import utils.elementUtils.Element;

import java.util.List;
import java.util.stream.Collectors;

import static utils.elementUtils.ElementUtil.*;

public class ProductPage implements IProductPage {

    private String addToCartProduct = "//div[text()='PRODUCTNAME']/ancestor::div[@class='inventory_item']//button";
    private String shoppingCartButton = "//a[@class='shopping_cart_link']";
    private String menuButton = "bm-burger-button";
    private String inventoryItem = "//div[@class='inventory_item_name']";
    private String shoppingCartItemsNumber = "//span[contains(@class,'shopping_cart_badge')]";

    @Override
    public void addToCartProduct(String productName) {
        String product = addToCartProduct.replace("PRODUCTNAME", productName);
        findByXpath(product, "adding product to cart :" + productName).click();
    }

    @Override
    public void clickCartButton() {
        findByXpath(shoppingCartButton, "shopping cart button").click();
    }

    @Override
    public void clickMenuButton() {
        findByClassName(menuButton, "menu button").click();
    }

    public String getShoppingCartItemsNumber() {
        return findByXpath(shoppingCartItemsNumber, "shopping cart items number").getText();
    }

    public List<String> getInventoryList() {
        List<String> inventory = findElementsByXpath(inventoryItem, "inventory items").stream()
                .map(Element::getText).collect(Collectors.toList());
        return inventory;
    }
}
