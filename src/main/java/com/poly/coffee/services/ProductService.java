package com.poly.coffee.services;

import com.poly.coffee.models.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * =========================================================================
 * DỊCH VỤ QUẢN LÝ SẢN PHẨM (ProductService) - CRUD ĐƠN GIẢN CHUẨN ASSIGNMENT
 * Phục vụ kiểm thử CRUD sản phẩm (Thêm, Sửa, Xóa, Tìm kiếm).
 * 
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
public class ProductService {

    private final List<Product> productList = new ArrayList<>();
    private long idCounter = 1;

    /**
     * Thêm mới sản phẩm (Create).
     */
    public Product addProduct(String name, double price, int quantity, String category) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống!");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Giá sản phẩm phải lớn hơn 0!");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Số lượng tồn kho không được âm!");
        }

        Product product = new Product(idCounter++, name.trim(), price, quantity, category != null ? category.trim() : "Khác");
        productList.add(product);
        return product;
    }

    /**
     * Cập nhật thông tin sản phẩm (Update).
     */
    public Product updateProduct(Long id, String name, double price, int quantity, String category) {
        Product existing = findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Không tìm thấy sản phẩm với ID: " + id);
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống!");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Giá sản phẩm phải lớn hơn 0!");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Số lượng tồn kho không được âm!");
        }

        existing.setName(name.trim());
        existing.setPrice(price);
        existing.setQuantity(quantity);
        existing.setCategory(category != null ? category.trim() : existing.getCategory());
        return existing;
    }

    /**
     * Xóa sản phẩm theo ID (Delete).
     */
    public boolean deleteProduct(Long id) {
        Product existing = findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Không tìm thấy sản phẩm với ID: " + id);
        }
        return productList.remove(existing);
    }

    /**
     * Tìm sản phẩm theo ID (Read).
     */
    public Product findById(Long id) {
        if (id == null) return null;
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Lấy toàn bộ danh sách sản phẩm.
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }

    public int count() {
        return productList.size();
    }

    public void clear() {
        productList.clear();
        idCounter = 1;
    }
}
