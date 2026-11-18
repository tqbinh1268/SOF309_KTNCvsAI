package com.poly.coffee.ui.tests;

import com.poly.coffee.ui.pages.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * =========================================================================
 * AUTOMATION TEST CHO TRANG QUẢN LÝ SẢN PHẨM (SELENIUM POM)
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
@Tag("ui")
public class ProductUiTest extends BaseUiTest {

    @Test
    @DisplayName("UI_PROD_01: Thêm mới sản phẩm thành công trên giao diện")
    public void testAddProduct_Success() {
        ProductPage productPage = new ProductPage(driver);
        productPage.open(getPageUrl("products.html"));

        int initialCount = productPage.getProductCount();
        productPage.addProduct("Bạc Xỉu Sài Gòn", 32000.0, 50, "Coffee");

        productPage.takeScreenshot("testAddProduct_Success");

        Assertions.assertTrue(productPage.isSuccessMessageDisplayed());
        Assertions.assertEquals("Thêm sản phẩm thành công!", productPage.getSuccessMessage());
        Assertions.assertEquals(initialCount + 1, productPage.getProductCount(), "Số lượng hàng trong bảng phải tăng 1");
    }

    @Test
    @DisplayName("UI_PROD_02: Báo lỗi khi để trống tên sản phẩm")
    public void testAddProduct_EmptyName_ShowsError() {
        ProductPage productPage = new ProductPage(driver);
        productPage.open(getPageUrl("products.html"));

        productPage.addProduct("", 30000.0, 20, "Tea");

        productPage.takeScreenshot("testAddProduct_EmptyName");

        Assertions.assertTrue(productPage.isErrorMessageDisplayed());
        Assertions.assertEquals("Tên sản phẩm không được để trống!", productPage.getErrorMessage());
    }

    @Test
    @DisplayName("UI_PROD_03: Báo lỗi khi giá sản phẩm <= 0")
    public void testAddProduct_InvalidPrice_ShowsError() {
        ProductPage productPage = new ProductPage(driver);
        productPage.open(getPageUrl("products.html"));

        productPage.addProduct("Trà đào cam sả", -5000.0, 10, "Tea");

        productPage.takeScreenshot("testAddProduct_InvalidPrice");

        Assertions.assertTrue(productPage.isErrorMessageDisplayed());
        Assertions.assertEquals("Giá sản phẩm phải lớn hơn 0!", productPage.getErrorMessage());
    }
}
