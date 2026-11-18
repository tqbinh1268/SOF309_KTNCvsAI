package com.poly.coffee.services;

import com.poly.coffee.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * =========================================================================
 * KIỂM THỬ CRUD SẢN PHẨM (ProductServiceTest) - JUNIT 5
 * Phục vụ module CRUD theo yêu cầu Y1 & Y2 của Assignment SOF309.
 * 
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductService();
        productService.clear();
    }

    @Test
    @Tag("smoke")
    @DisplayName("CRUD01 - Thêm mới sản phẩm thành công")
    public void testAddProduct_Success() {
        Product p = productService.addProduct("Cà phê sữa đá", 29000, 100, "Cà phê");

        assertNotNull(p, "Sản phẩm tạo ra không được null");
        assertNotNull(p.getId(), "Sản phẩm phải có ID tự tăng");
        assertEquals("Cà phê sữa đá", p.getName());
        assertEquals(29000, p.getPrice());
        assertEquals(1, productService.count());
    }

    @Test
    @DisplayName("CRUD02 - Thêm sản phẩm thất bại khi tên để trống")
    public void testAddProduct_EmptyName_ThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            productService.addProduct("", 25000, 50, "Đồ uống");
        });

        assertEquals("Tên sản phẩm không được để trống!", ex.getMessage());
    }

    @Test
    @DisplayName("CRUD03 - Thêm sản phẩm thất bại khi giá nhỏ hơn hoặc bằng 0")
    public void testAddProduct_InvalidPrice_ThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            productService.addProduct("Trà đào", 0, 50, "Trà");
        });

        assertEquals("Giá sản phẩm phải lớn hơn 0!", ex.getMessage());
    }

    @Test
    @DisplayName("CRUD04 - Cập nhật thông tin sản phẩm thành công")
    public void testUpdateProduct_Success() {
        Product p = productService.addProduct("Bạc xỉu", 32000, 40, "Cà phê");

        Product updated = productService.updateProduct(p.getId(), "Bạc xỉu Sài Gòn", 35000, 60, "Cà phê");

        assertEquals("Bạc xỉu Sài Gòn", updated.getName());
        assertEquals(35000, updated.getPrice());
        assertEquals(60, updated.getQuantity());
    }

    @Test
    @DisplayName("CRUD05 - Xóa sản phẩm thành công")
    public void testDeleteProduct_Success() {
        Product p = productService.addProduct("Cà phê đen", 20000, 50, "Cà phê");
        assertEquals(1, productService.count());

        boolean isDeleted = productService.deleteProduct(p.getId());

        assertTrue(isDeleted, "Xóa sản phẩm phải trả về true");
        assertEquals(0, productService.count());
        assertNull(productService.findById(p.getId()));
    }

    @Test
    @DisplayName("CRUD06 - Lấy danh sách sản phẩm")
    public void testGetAllProducts() {
        productService.addProduct("Cà phê 1", 20000, 10, "Cà phê");
        productService.addProduct("Cà phê 2", 25000, 20, "Cà phê");

        List<Product> list = productService.getAllProducts();
        assertEquals(2, list.size());
    }
}
