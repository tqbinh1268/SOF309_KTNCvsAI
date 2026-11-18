# 🧪 BẢNG KỊCH BẢN KIỂM THỬ CHI TIẾT (TEST CASES MATRIX)
> **Dự án:** Poly Coffee Management  
> **Môn học:** SOF309 — Kiểm thử nâng cao cùng AI  
> **Giảng viên hướng dẫn:** ThS. Trần Quang Bình (Thầy Mười Giáo — OMG · `binhtq7@fpt.edu.vn`)  
> **Phương pháp thiết kế:** Phân vùng tương đương (EP) & Phân tích giá trị biên (BVA)  
> **Ánh xạ nghiệp vụ:** [docs/SPECIFICATIONS.md](SPECIFICATIONS.md)

---

## 🔐 PHẦN 1. TEST CASES PHÂN HỆ XÁC THỰC (AUTHENTICATION)

### A. Kịch bản Đăng ký tài khoản (`AuthService.register`)

| Test_ID | Quy tắc (Rule) | Kỹ thuật | Input (fullname, username, pass, confirmPass, phone) | Kết quả mong đợi (Expected Result) | Ngoại lệ mong đợi (Exception) | Loại Test |
| :---: | :--- | :---: | :--- | :--- | :--- | :---: |
| **TC01** | `BR-AUTH-01` | EP (Valid) | `"Trần Quang Bình", "binhtq7", "Pass@123", "Pass@123", "0901234567"` | `User != null`, `id > 0`, `role = "CUSTOMER"` | `None` | Happy Path |
| **TC02** | `BR-AUTH-01.1` | EP (Invalid)| `"", "binhtq7", "Pass@123", "Pass@123", "0901234567"` | Ném lỗi: Họ tên không được để trống | `IllegalArgumentException` | Negative |
| **TC03** | `BR-AUTH-01.2` | EP (Invalid)| `"Trần Quang Bình", "", "Pass@123", "Pass@123", "0901234567"` | Ném lỗi: Tên đăng nhập không được để trống | `IllegalArgumentException` | Negative |
| **TC04** | `BR-AUTH-01.3` | BVA (Boundary)| `"Trần Quang Bình", "binhtq7", "12345", "12345", "0901234567"` | Ném lỗi: Mật khẩu phải từ 6 ký tự trở lên (5 ký tự) | `IllegalArgumentException` | Negative (Biên) |
| **TC05** | `BR-AUTH-01.4` | EP (Invalid)| `"Trần Quang Bình", "binhtq7", "Pass@123", "Pass@999", "0901234567"` | Ném lỗi: Mật khẩu xác nhận không khớp | `IllegalArgumentException` | Negative |
| **TC06** | `BR-AUTH-01.5` | BVA (Boundary)| `"Trần Quang Bình", "binhtq7", "Pass@123", "Pass@123", "090123456"` | Ném lỗi: SĐT không hợp lệ (chỉ có 9 số) | `IllegalArgumentException` | Negative (Biên) |
| **TC07** | `BR-AUTH-01.6` | EP (Duplicate)| `"Trần Quang Bình", "BINHTQ7", "Pass@123", "Pass@123", "0901234567"` | Ném lỗi: Tên đăng nhập đã tồn tại (Case-insensitive) | `IllegalArgumentException` | Negative |

#### Bảng Kiểm thử Tham số hóa (@ParameterizedTest + @CsvSource):
| Test_ID | Input Test Data (fullname, username, pass, confirmPass, phone) | Kịch bản kiểm tra biên | Kết quả mong đợi |
| :---: | :--- | :--- | :--- |
| **DDT01**| `"", "user01", "123456", "123456", "0901112222"` | Họ tên rỗng | Báo lỗi Họ tên |
| **DDT02**| `"Nguyen A", "", "123456", "123456", "0901112222"` | Username rỗng | Báo lỗi Username |
| **DDT03**| `"Nguyen A", "user03", "12345", "12345", "0901112222"` | Mật khẩu 5 ký tự ($Min-1$) | Báo lỗi Mật khẩu ngắn |
| **DDT04**| `"Nguyen A", "user04", "123456", "654321", "0901112222"` | Mật khẩu xác nhận khác | Báo lỗi Không khớp mật khẩu |
| **DDT05**| `"Nguyen A", "user05", "123456", "123456", "09011122"` | SĐT 8 số (Thiếu độ dài) | Báo lỗi Định dạng SĐT |

---

### B. Kịch bản Đăng nhập hệ thống (`AuthService.login`)

| Test_ID | Quy tắc (Rule) | Kỹ thuật | Input (username, password) | Pre-condition | Kết quả mong đợi | Ngoại lệ mong đợi | Loại Test |
| :---: | :--- | :---: | :--- | :--- | :--- | :--- | :---: |
| **TC08** | `BR-AUTH-02` | EP (Valid) | `"binhtq7", "Pass@123"` | Đã đăng ký `binhtq7` | Trả về đối tượng `User` hợp lệ | `None` | Happy Path |
| **TC09** | `BR-AUTH-02.4`| EP (Invalid)| `"binhtq7", "WrongPass@999"` | Đã đăng ký `binhtq7` | Ném lỗi: Tài khoản hoặc mật khẩu không chính xác | `IllegalArgumentException` | Negative |
| **TC10** | `BR-AUTH-02.4`| EP (Invalid)| `"user_khong_ton_tai", "Pass@123"` | Chưa đăng ký | Ném lỗi: Tài khoản hoặc mật khẩu không chính xác | `IllegalArgumentException` | Negative |
| **TC11** | `BR-AUTH-02.1`| EP (Boundary)| `"", "Pass@123"` | Bất kỳ | Ném lỗi: Vui lòng nhập tên đăng nhập | `IllegalArgumentException` | Negative |

---

## ☕ PHẦN 2. TEST CASES PHÂN HỆ QUẢN LÝ SẢN PHẨM (PRODUCT CRUD)

| Test_ID | Chức năng (Method) | Quy tắc (Rule) | Kỹ thuật | Input Data | Kết quả mong đợi | Ngoại lệ mong đợi | Loại Test |
| :---: | :--- | :--- | :---: | :--- | :--- | :--- | :---: |
| **CRUD01** | `create()` | `BR-PROD-01` | EP (Valid) | `"Cà phê sữa đá", 29000.0, 100, "Coffee"` | `Product != null`, `id > 0`, lưu vào RAM | `None` | Happy Path |
| **CRUD02** | `create()` | `BR-PROD-01.1` | EP (Invalid)| `"", 29000.0, 100, "Coffee"` | Báo lỗi: Tên sản phẩm không được để trống | `IllegalArgumentException` | Negative |
| **CRUD03** | `create()` | `BR-PROD-01.2` | BVA (Boundary)| `"Trà đào", 0.0, 50, "Tea"` | Báo lỗi: Giá sản phẩm phải lớn hơn 0 | `IllegalArgumentException` | Negative (Biên) |
| **CRUD04** | `update()` | `BR-PROD-03` | EP (Valid) | `id = 1, "Cà phê sữa đặc biệt", 35000.0, 80, "Coffee"` | Cập nhật thành công thông tin sản phẩm | `None` | Happy Path |
| **CRUD05** | `delete()` | `BR-PROD-04` | EP (Valid) | `id = 1` | Xóa sản phẩm khỏi danh sách, `delete() == true` | `None` | Happy Path |
| **CRUD06** | `getAll()` | `BR-PROD-02.1` | EP (Valid) | Không có tham số | Trả về danh sách chứa toàn bộ sản phẩm | `None` | Happy Path |
