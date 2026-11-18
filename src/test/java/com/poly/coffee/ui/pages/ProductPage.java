package com.poly.coffee.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * =========================================================================
 * PRODUCT PAGE OBJECT (Mô hình Page Object Model)
 * Ánh xạ tới trang: /products.html
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
public class ProductPage extends BasePage {

    // Locators định vị các phần tử giao diện theo BR-UI-03
    private final By nameInput = By.id("name");
    private final By priceInput = By.id("price");
    private final By quantityInput = By.id("quantity");
    private final By categorySelect = By.id("category");
    private final By addProductButton = By.id("btn-add-product");
    private final By errorMessage = By.id("error-message");
    private final By successMessage = By.id("success-message");
    private final By productTableRows = By.cssSelector("#product-list tr");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void addProduct(String name, double price, int quantity, String category) {
        type(nameInput, name);
        type(priceInput, String.valueOf(price));
        type(quantityInput, String.valueOf(quantity));
        find(categorySelect).sendKeys(category);
        click(addProductButton);
    }

    public int getProductCount() {
        return driver.findElements(productTableRows).size();
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }
}
