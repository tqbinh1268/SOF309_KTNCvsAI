package com.poly.coffee.models;

/**
 * Thực thể Người dùng (User) của hệ thống Poly Coffee.
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 */
public class User {
    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String phone;
    private String role; // "CUSTOMER" hoặc "ADMIN"

    public User() {
    }

    public User(Long id, String fullname, String username, String password, String phone, String role) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
