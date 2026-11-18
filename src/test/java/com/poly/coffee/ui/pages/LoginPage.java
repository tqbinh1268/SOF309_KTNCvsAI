package com.poly.coffee.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * =========================================================================
 * LOGIN PAGE OBJECT (Mô hình Page Object Model)
 * Ánh xạ tới trang: /login.html
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
public class LoginPage extends BasePage {

    // 1. Locators định vị các phần tử giao diện theo BR-UI-02
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("btn-login");
    private final By errorMessage = By.id("error-message");
    private final By successMessage = By.id("success-message");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    /**
     * Hành động nghiệp vụ: Đăng nhập tổng hợp
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
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
