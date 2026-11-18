package com.poly.coffee.suites;

import com.poly.coffee.services.AuthServiceTest;
import com.poly.coffee.services.ProductServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * =========================================================================
 * TEST SUITE 1: FULL REGRESSION TEST SUITE (KIỂM THỬ HỒI QUY TOÀN DIỆN)
 * Chạy toàn bộ 22 test cases của tất cả các Class trong dự án.
 * Dùng trước khi đóng gói Release hoặc nộp bài Assignment.
 * 
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
@Suite
@SuiteDisplayName("🚀 FULL REGRESSION SUITE - CHẠY TOÀN BỘ TEST CASES DỰ ÁN")
@SelectClasses({
        AuthServiceTest.class,
        ProductServiceTest.class
})
public class AllTestsSuite {
    // Class này để trống, JUnit Platform sẽ tự động quét và thực thi các class trên
}
