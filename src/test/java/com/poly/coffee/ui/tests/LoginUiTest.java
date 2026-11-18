package com.poly.coffee.ui.tests;

import com.poly.coffee.ui.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * =========================================================================
 * AUTOMATION TEST CHO TRANG ĐĂNG NHẬP (SELENIUM PAGE OBJECT MODEL)
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
@Tag("ui")
public class LoginUiTest extends BaseUiTest {

    @Test
    @DisplayName("UI_LOG_01: Đăng nhập thành công với tài khoản đúng")
    public void testLogin_Success() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(getPageUrl("login.html"));

        loginPage.login("binhtq7", "Pass@123");

        // Chụp ảnh màn hình minh chứng
        loginPage.takeScreenshot("testLogin_Success");

        Assertions.assertTrue(loginPage.isSuccessMessageDisplayed(), "Thông báo thành công phải được hiển thị");
        Assertions.assertEquals("Đăng nhập thành công!", loginPage.getSuccessMessage());
    }

    @Test
    @DisplayName("UI_LOG_02: Báo lỗi khi sai mật khẩu")
    public void testLogin_WrongPassword_ShowsError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(getPageUrl("login.html"));

        loginPage.login("binhtq7", "WrongPass@999");

        loginPage.takeScreenshot("testLogin_WrongPassword");

        Assertions.assertTrue(loginPage.isErrorMessageDisplayed(), "Thông báo lỗi phải hiển thị");
        Assertions.assertEquals("Tài khoản hoặc mật khẩu không chính xác!", loginPage.getErrorMessage());
    }

    @Test
    @DisplayName("UI_LOG_03: Báo lỗi khi để trống tên đăng nhập")
    public void testLogin_EmptyUsername_ShowsError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(getPageUrl("login.html"));

        loginPage.login("", "Pass@123");

        loginPage.takeScreenshot("testLogin_EmptyUsername");

        Assertions.assertTrue(loginPage.isErrorMessageDisplayed());
        Assertions.assertEquals("Vui lòng nhập tên đăng nhập!", loginPage.getErrorMessage());
    }
}
