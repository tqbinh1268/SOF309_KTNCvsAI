package com.poly.coffee.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * =========================================================================
 * TEST SUITE 2: SMOKE TEST SUITE (KIỂM THỬ KHÓI / KIỂM THỬ NHANH)
 * Chỉ lọc và chạy các ca kiểm thử mang tag @Tag("smoke") (các luồng trọng yếu:
 * Đăng ký OK, Đăng nhập OK, Thêm sản phẩm OK).
 * 
 * Mục đích: Chạy cực nhanh (1 giây) trên GitHub Actions CI/CD (Yêu cầu Y5 Assignment).
 * 
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
@Suite
@SuiteDisplayName("⚡ SMOKE TEST SUITE - KIỂM THỬ NHANH CÁC LUỒNG TRỌNG YẾU")
@SelectPackages("com.poly.coffee.services")
@IncludeTags("smoke")
public class SmokeTestSuite {
    // Chỉ chạy các test method được gắn nhãn @Tag("smoke")
}
