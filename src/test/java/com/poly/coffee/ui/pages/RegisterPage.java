package com.poly.coffee.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * =========================================================================
 * REGISTER PAGE OBJECT (Mô hình Page Object Model)
 * Ánh xạ tới trang: /register.html
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
public class RegisterPage extends BasePage {

    // Locators định vị các phần tử giao diện theo BR-UI-01
    private final By fullnameInput = By.id("fullname");
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By confirmPasswordInput = By.id("confirmPassword");
    private final By phoneInput = By.id("phone");
    private final By registerButton = By.id("btn-register");
    private final By errorMessage = By.id("error-message");
    private final By successMessage = By.id("success-message");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void register(String fullname, String username, String password, String confirmPassword, String phone) {
        type(fullnameInput, fullname);
        type(usernameInput, username);
        type(passwordInput, password);
        type(confirmPasswordInput, confirmPassword);
        type(phoneInput, phone);
        click(registerButton);
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
