package com.poly.coffee.services;

import com.poly.coffee.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * =========================================================================
 * KIỂM THỬ ĐƠN VỊ (UNIT TEST VỚI JUNIT 5) - CHUẨN ASSIGNMENT SOF309
 * 
 * Đơn giản - Tường minh - Dễ hiểu cho sinh viên.
 * Không cần đọc file CSV phức tạp, không cần DAO/Interface.
 * 
 * Giảng viên: ThS. Trần Quang Bình (Thầy Mười Giáo - OMG)
 * =========================================================================
 */
public class AuthServiceTest {

    private AuthService authService;

    @BeforeEach
    public void setUp() {
        authService = new AuthService();
        authService.clear(); // Reset dữ liệu trước mỗi test case
    }

    // =========================================================================
    // 1. KIỂM THỬ CHỨC NĂNG ĐĂNG KÝ (REGISTER)
    // =========================================================================

    @Test
    @Tag("smoke")
    @DisplayName("TC01 - Đăng ký thành công với thông tin đầy đủ và hợp lệ")
    public void testRegister_Success() {
        User user = authService.register(
                "Trần Quang Bình", "binhtq7", "Pass@123", "Pass@123", "0901234567"
        );

        assertNotNull(user, "User đăng ký thành công không được null");
        assertNotNull(user.getId(), "User phải được cấp ID tự tăng");
        assertEquals("binhtq7", user.getUsername(), "Username phải trùng khớp");
        assertEquals(1, authService.count(), "Hệ thống phải có đúng 1 user");
    }

    @Test
    @DisplayName("TC02 - Báo lỗi khi để trống Họ và tên")
    public void testRegister_EmptyFullname_ThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            authService.register("", "binhtq7", "Pass@123", "Pass@123", "0901234567");
        });

        assertEquals("Vui lòng nhập họ và tên!", ex.getMessage());
    }

    @Test
    @DisplayName("TC03 - Báo lỗi khi để trống Tên đăng nhập")
    public void testRegister_EmptyUsername_ThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            authService.register("Trần Quang Bình", "   ", "Pass@123", "Pass@123", "0901234567");
        });

        assertEquals("Vui lòng nhập tên đăng nhập!", ex.getMessage());
    }

    @Test
    @DisplayName("TC04 - Báo lỗi khi mật khẩu dưới 6 ký tự")
    public void testRegister_ShortPassword_ThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            authService.register("Trần Quang Bình", "binhtq7", "12345", "12345", "0901234567");
        });

        assertEquals("Mật khẩu phải từ 6 ký tự trở lên!", ex.getMessage());
    }

    @Test
    @DisplayName("TC05 - Báo lỗi khi Mật khẩu xác nhận không khớp")
    public void testRegister_PasswordNotMatch_ThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            authService.register("Trần Quang Bình", "binhtq7", "Pass@123", "Pass@999", "0901234567");
        });

        assertEquals("Mật khẩu xác nhận không khớp!", ex.getMessage());
    }

    @Test
    @DisplayName("TC06 - Báo lỗi khi Số điện thoại không đủ 10 số hoặc không bắt đầu bằng số 0")
    public void testRegister_InvalidPhone_ThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            authService.register("Trần Quang Bình", "binhtq7", "Pass@123", "Pass@123", "090123");
        });

        assertTrue(ex.getMessage().contains("Số điện thoại không hợp lệ"));
    }

    @Test
    @DisplayName("TC07 - Báo lỗi khi Đăng ký trùng Tên đăng nhập đã tồn tại")
    public void testRegister_DuplicateUsername_ThrowsException() {
        // Nạp sẵn user gốc
        authService.register("Trần Quang Bình", "binhtq7", "Pass@123", "Pass@123", "0901234567");

        // Đăng ký lại cùng username
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            authService.register("Người Dùng Khác", "BINHTQ7", "NewPass@123", "NewPass@123", "0987654321");
        });

        assertTrue(ex.getMessage().contains("đã tồn tại"));
    }

    // =========================================================================
    // 2. KIỂM THỬ HÀM THAM SỐ HÓA (@ParameterizedTest VỚI @CsvSource TRỰC TIẾP)
    // Giúp sinh viên nắm kỹ thuật Data-Driven Testing mà không cần tạo file ngoài
    // =========================================================================

    @ParameterizedTest(name = "[{index}] Đăng ký lỗi: {0}")
    @CsvSource({
            "'Họ tên rỗng', '', 'user1', 'Pass@123', 'Pass@123', '0901234567'",
            "'Username rỗng', 'Nguyễn A', '', 'Pass@123', 'Pass@123', '0901234567'",
            "'Mật khẩu ngắn', 'Nguyễn A', 'user1', '123', '123', '0901234567'",
            "'Mật khẩu lệch', 'Nguyễn A', 'user1', 'Pass@123', 'Pass@Khac', '0901234567'",
            "'SĐT sai định dạng', 'Nguyễn A', 'user1', 'Pass@123', 'Pass@123', '12345'"
    })
    @DisplayName("DDT: Kiểm tra các trường hợp dữ liệu đăng ký không hợp lệ")
    public void testRegister_InvalidData_Parameterized(
            String caseName, String fullname, String username, 
            String password, String confirmPassword, String phone) {

        assertThrows(IllegalArgumentException.class, () -> {
            authService.register(fullname, username, password, confirmPassword, phone);
        });
    }

    // =========================================================================
    // 3. KIỂM THỬ CHỨC NĂNG ĐĂNG NHẬP (LOGIN)
    // =========================================================================

    @Test
    @Tag("smoke")
    @DisplayName("TC08 - Đăng nhập thành công đúng username và password")
    public void testLogin_Success() {
        // Tạo sẵn tài khoản
        authService.register("Trần Quang Bình", "binhtq7", "Pass@123", "Pass@123", "0901234567");

        // Thực hiện đăng nhập
        User loggedUser = authService.login("binhtq7", "Pass@123");

        assertNotNull(loggedUser, "Đăng nhập thành công trả về User không được null");
        assertEquals("binhtq7", loggedUser.getUsername());
    }

    @Test
    @DisplayName("TC09 - Báo lỗi khi Đăng nhập sai mật khẩu")
    public void testLogin_WrongPassword_ThrowsException() {
        authService.register("Trần Quang Bình", "binhtq7", "Pass@123", "Pass@123", "0901234567");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            authService.login("binhtq7", "WrongPass@999");
        });

        assertEquals("Tài khoản hoặc mật khẩu không chính xác!", ex.getMessage());
    }

    @Test
    @DisplayName("TC10 - Báo lỗi khi Đăng nhập với tài khoản chưa tồn tại")
    public void testLogin_UserNotFound_ThrowsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            authService.login("user_khong_ton_tai", "Pass@123");
        });

        assertEquals("Tài khoản hoặc mật khẩu không chính xác!", ex.getMessage());
    }

    @Test
    @DisplayName("TC11 - Báo lỗi khi để trống username hoặc password khi đăng nhập")
    public void testLogin_EmptyInput_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> authService.login("", "Pass@123"));
        assertThrows(IllegalArgumentException.class, () -> authService.login("binhtq7", ""));
    }
}
