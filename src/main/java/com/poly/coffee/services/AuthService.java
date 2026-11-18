package com.poly.coffee.services;

import com.poly.coffee.models.User;
import java.util.ArrayList;
import java.util.List;

/**
 * =========================================================================
 * DỊCH VỤ XÁC THỰC NGƯỜI DÙNG (AuthService) - BẢN DEV BÀN GIAO BAN ĐẦU
 * (Chưa có Unit Test, có bẫy lỗi logic để Tester phát hiện)
 * 
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
public class AuthService {

    private final List<User> userList = new ArrayList<>();
    private long idCounter = 1;

    /**
     * Đăng ký tài khoản mới.
     * Ném IllegalArgumentException nếu dữ liệu không hợp lệ.
     */
    public User register(String fullname, String username, String password, String confirmPassword, String phone) {
        // 1. Kiểm tra bỏ trống họ tên
        if (fullname == null || fullname.trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập họ và tên!");
        }

        // 2. Kiểm tra bỏ trống username
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập tên đăng nhập!");
        }

        // 3. Kiểm tra bỏ trống mật khẩu
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập mật khẩu!");
        }

        // 4. Kiểm tra độ dài mật khẩu tối thiểu 6 ký tự
        if (password.length() < 6) {
            throw new IllegalArgumentException("Mật khẩu phải từ 6 ký tự trở lên!");
        }

        // 5. Kiểm tra mật khẩu xác nhận
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Mật khẩu xác nhận không khớp!");
        }

        // 6. Kiểm tra số điện thoại (BẪY LỖI SƯ PHẠM: Dev chỉ check rỗng, quên kiểm tra regex 10 số đầu 0!)
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập số điện thoại!");
        }

        // 7. Kiểm tra trùng lặp tên đăng nhập
        String cleanUsername = username.trim().toLowerCase();
        boolean isDuplicate = userList.stream()
                .anyMatch(u -> u.getUsername().equalsIgnoreCase(cleanUsername));
        if (isDuplicate) {
            throw new IllegalArgumentException("Tên đăng nhập '" + username + "' đã tồn tại!");
        }

        // Tạo User và thêm vào danh sách
        User newUser = new User(idCounter++, fullname.trim(), cleanUsername, password, phone.trim(), "CUSTOMER");
        userList.add(newUser);
        return newUser;
    }

    /**
     * Đăng nhập hệ thống.
     * Ném IllegalArgumentException nếu sai tài khoản / mật khẩu.
     */
    public User login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập tên đăng nhập!");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập mật khẩu!");
        }

        String cleanUsername = username.trim().toLowerCase();
        return userList.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(cleanUsername) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tài khoản hoặc mật khẩu không chính xác!"));
    }

    /**
     * Xóa sạch dữ liệu (dùng cho setUp() trong Unit Test).
     */
    public void clear() {
        userList.clear();
        idCounter = 1;
    }

    /**
     * Đếm tổng số lượng user hiện có.
     */
    public int count() {
        return userList.size();
    }

    /**
     * Lấy toàn bộ danh sách user.
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(userList);
    }
}
