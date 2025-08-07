package pageObject;

import java.util.List;

public interface IProductPage {

    void addToCartProduct(String productName);

    void clickCartButton();

    void clickMenuButton();

    String getShoppingCartItemsNumber();

    List<String> getInventoryList();
}
