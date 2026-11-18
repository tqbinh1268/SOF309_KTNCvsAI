# ☕ SOF309: KIỂM THỬ NÂNG CAO CÙNG AI
[![CI Automation Test](https://github.com/tqbinh1268/SOF309_KTNCvsAI/actions/workflows/test.yml/badge.svg)](https://github.com/tqbinh1268/SOF309_KTNCvsAI/actions/workflows/test.yml)
> **Mã nguồn Demo Kiểm thử Đơn giản & Chuẩn hóa Assignment cho Sinh viên FPT Polytechnic**  
> Giảng viên hướng dẫn: **ThS. Trần Quang Bình (Thầy Mười Giáo — OMG · `binhtq7@fpt.edu.vn`)**  
> Dự án: **Poly Coffee Management**

---

## 🎯 ĐẶC TẢ THEO YÊU CẦU ASSIGNMENT SOF309
Hệ thống được thiết kế theo tiêu chí **Tối Giản - Trực Quan - Đúng Chuẩn Sư Phạm**:
- Không dùng Interface/DAO/DTO cồng kềnh, sinh viên dễ dàng đọc hiểu luồng nghiệp vụ.
- Dùng `IllegalArgumentException` chuẩn của Java.
- Dữ liệu lưu trong `List` RAM (reset sau mỗi test bằng `clear()`).

### 📚 Tài Liệu & Hồ Sơ Dự Án:
* 📋 **[Hồ sơ Đặc tả Nghiệp vụ SRS (SPECIFICATIONS.md)](file:///Users/ongmuoigiao/Library/CloudStorage/GoogleDrive-ongmuoigiao@gmail.com/My%20Drive/M%C3%B4n%20H%E1%BB%8Dc/SOF309%20Ki%E1%BB%83m%20th%E1%BB%AD%20n%C3%A2ng%20cao%20c%C3%B9ng%20AI/Code%20Demo/SOF309_KTNCvsAI/docs/SPECIFICATIONS.md)**: Danh mục yêu cầu có đánh chỉ mục (`BR-AUTH-01`, `BR-PROD-01`...) dùng làm ngữ cảnh chuẩn cho kỹ thuật RAG với Gen AI (Yêu cầu Y2).
* 📑 **[Kế hoạch Kiểm thử Tự động (TEST_PLAN.md)](file:///Users/ongmuoigiao/Library/CloudStorage/GoogleDrive-ongmuoigiao@gmail.com/My%20Drive/M%C3%B4n%20H%E1%BB%8Dc/SOF309%20Ki%E1%BB%83m%20th%E1%BB%AD%20n%C3%A2ng%20cao%20c%C3%B9ng%20AI/Code%20Demo/SOF309_KTNCvsAI/docs/TEST_PLAN.md)**: Kế hoạch kiểm thử ngắn gọn xác định mục tiêu, phạm vi in-scope/out-of-scope và công cụ kiểm thử.
* 🧪 **[Bảng Kịch bản Kiểm thử Chi tiết (TEST_CASES.md)](file:///Users/ongmuoigiao/Library/CloudStorage/GoogleDrive-ongmuoigiao@gmail.com/My%20Drive/M%C3%B4n%20H%E1%BB%8Dc/SOF309%20Ki%E1%BB%83m%20th%E1%BB%AD%20n%C3%A2ng%20cao%20c%C3%B9ng%20AI/Code%20Demo/SOF309_KTNCvsAI/docs/TEST_CASES.md)**: 22 kịch bản kiểm thử bao phủ toàn bộ Business Rules, áp dụng Phân vùng tương đương (EP) và Phân tích giá trị biên (BVA).

#### 🤖 Trọn Bộ 5 Nhật Ký Minh Chứng Ứng Dụng AI (Yêu Cầu Y6 - Chuẩn Điểm A):
* 1️⃣ **[Giai đoạn 1: Thiết kế Test Plan & Test Cases (01_ai_test_design.md)](file:///Users/ongmuoigiao/Library/CloudStorage/GoogleDrive-ongmuoigiao@gmail.com/My%20Drive/M%C3%B4n%20H%E1%BB%8Dc/SOF309%20Ki%E1%BB%83m%20th%E1%BB%AD%20n%C3%A2ng%20cao%20c%C3%B9ng%20AI/Code%20Demo/SOF309_KTNCvsAI/docs/ai-logs/01_ai_test_design.md)**: Áp dụng RAG nạp SRS để sinh test case biên và phân vùng tương đương.
* 2️⃣ **[Giai đoạn 2: Lập trình Selenium POM (02_ai_selenium_pom.md)](file:///Users/ongmuoigiao/Library/CloudStorage/GoogleDrive-ongmuoigiao@gmail.com/My%20Drive/M%C3%B4n%20H%E1%BB%8Dc/SOF309%20Ki%E1%BB%83m%20th%E1%BB%AD%20n%C3%A2ng%20cao%20c%C3%B9ng%20AI/Code%20Demo/SOF309_KTNCvsAI/docs/ai-logs/02_ai_selenium_pom.md)**: Sinh cấu trúc Page Object Model và kịch bản tương tác web headless.
* 3️⃣ **[Giai đoạn 3: Phân tích Stack Trace & Debug (03_ai_debug_logs.md)](file:///Users/ongmuoigiao/Library/CloudStorage/GoogleDrive-ongmuoigiao@gmail.com/My%20Drive/M%C3%B4n%20H%E1%BB%8Dc/SOF309%20Ki%E1%BB%83m%20th%E1%BB%AD%20n%C3%A2ng%20cao%20c%C3%B9ng%20AI/Code%20Demo/SOF309_KTNCvsAI/docs/ai-logs/03_ai_debug_logs.md)**: Nạp log lỗi cho AI tìm nguyên nhân gốc rễ và đề xuất bản vá code.
* 4️⃣ **[Giai đoạn 4: Thiết lập CI/CD GitHub Actions (04_ai_github_actions.md)](file:///Users/ongmuoigiao/Library/CloudStorage/GoogleDrive-ongmuoigiao@gmail.com/My%20Drive/M%C3%B4n%20H%E1%BB%8Dc/SOF309%20Ki%E1%BB%83m%20th%E1%BB%AD%20n%C3%A2ng%20cao%20c%C3%B9ng%20AI/Code%20Demo/SOF309_KTNCvsAI/docs/ai-logs/04_ai_github_actions.md)**: Sinh workflow YAML tự động chạy test trên Ubuntu runner và xuất artifacts.
* 5️⃣ **[Giai đoạn 5: Tối ưu & Review Code (05_ai_code_review.md)](file:///Users/ongmuoigiao/Library/CloudStorage/GoogleDrive-ongmuoigiao@gmail.com/My%20Drive/M%C3%B4n%20H%E1%BB%8Dc/SOF309%20Ki%E1%BB%83m%20th%E1%BB%AD%20n%C3%A2ng%20cao%20c%C3%B9ng%20AI/Code%20Demo/SOF309_KTNCvsAI/docs/ai-logs/05_ai_code_review.md)**: Rà soát Clean Code, giải phóng tài nguyên WebDriver và đánh giá kiến trúc.

---

## 🏷️ BẢN ĐỒ MILESTONES (TIME-TRAVEL DEMO)
Giảng viên và sinh viên có thể quay ngược thời gian repository về đúng từng thời điểm bài học bằng lệnh `git checkout <tag>`:

| Mốc Demo | Lệnh Checkout | Mô tả Trạng thái Code |
| :--- | :--- | :--- |
| **Giai đoạn 0: Đặc tả** | `git checkout v0.1-spec-init` | Chỉ có hồ sơ SRS, tài liệu kiến trúc, chưa có code. |
| **Giai đoạn 0.2: RAG Test**| `git checkout v0.2-test-design-y2` | Bổ sung Test Plan, Test Cases và AI Log 1. |
| **Giai đoạn 1: Dev Auth** | `git checkout v1.1-auth-dev` | Dev vừa viết xong `AuthService.java`, chưa có unit test. |
| **Giai đoạn 2: Red Test** | `git checkout v1.2-auth-test-red` | Tester viết `AuthServiceTest.java` ➔ Chạy test bị **FAIL** để minh họa Tester bắt bug. |
| **Giai đoạn 3: Green Test**| `git checkout v1.3-auth-test-green`| Dev fix sạch bug, chạy `mvn test` **Pass 16/16**. |
| **Giai đoạn 4: Product CRUD**| `git checkout v2.2-product-test-green`| Hoàn thành CRUD Sản phẩm, chạy `mvn test` **Pass 22/22**. |
| **Giai đoạn 5: Selenium POM**| `git checkout v3.0-selenium-pom-pass`| Hoàn thành Automation Test Web UI (POM), chạy `mvn test` **Pass 28/28**. |
| **Giai đoạn 6: CI/CD Cloud** | `git checkout v4.0-cicd-green` | Tự động hóa CI/CD GitHub Actions chạy Pass xanh trên Cloud. |
| **Hoàn thành Assignment**| `git checkout v5.0-assignment-complete`| Đầy đủ 6 yêu cầu (Y1 - Y6) & 5 bộ AI Logs chuẩn điểm A. |
| **Mới nhất (Latest)** | `git checkout main` | Nhánh chính chứa toàn bộ mã nguồn cập nhật mới nhất. |

```
src/
├── main/java/com/poly/coffee/
│   ├── models/
│   │   ├── User.java          (Người dùng: id, fullname, username, password, phone, role)
│   │   └── Product.java       (Sản phẩm: id, name, price, quantity, category)
│   └── services/
│       ├── AuthService.java   (Đăng ký & Đăng nhập)
│       └── ProductService.java(CRUD Sản phẩm: Create, Read, Update, Delete)
└── test/java/com/poly/coffee/services/
    ├── AuthServiceTest.java   (16 Unit tests JUnit 5: @Test & @CsvSource)
    └── ProductServiceTest.java(6 Unit tests CRUD JUnit 5)
```

---

## 🧪 BẢNG KIỂM THỬ ĐƠN VỊ (UNIT TEST VỚI JUNIT 5)

### 1. Module Xác thực (`AuthServiceTest.java`) - 16 Test Cases
* **Đăng ký (Register):**
  * `TC01`: Đăng ký thành công với thông tin hợp lệ (`User != null`, `id > 0`).
  * `TC02`: Báo lỗi khi để trống Họ và tên.
  * `TC03`: Báo lỗi khi để trống Tên đăng nhập.
  * `TC04`: Báo lỗi khi Mật khẩu dưới 6 ký tự.
  * `TC05`: Báo lỗi khi Mật khẩu xác nhận không khớp.
  * `TC06`: Báo lỗi khi Số điện thoại sai định dạng (không đủ 10 số / không bắt đầu số 0).
  * `TC07`: Báo lỗi khi Trùng tên đăng nhập đã tồn tại trong hệ thống.
  * `DDT`: Kiểm thử tham số hóa `@ParameterizedTest` với `@CsvSource` trực tiếp trong code (5 kịch bản biên).
* **Đăng nhập (Login):**
  * `TC08`: Đăng nhập thành công đúng `username` & `password`.
  * `TC09`: Báo lỗi khi Đăng nhập sai mật khẩu.
  * `TC10`: Báo lỗi khi Tài khoản không tồn tại.
  * `TC11`: Báo lỗi khi để trống trường thông tin đăng nhập.

### 2. Module CRUD Sản phẩm (`ProductServiceTest.java`) - 6 Test Cases
* `CRUD01`: Thêm mới sản phẩm thành công (Create).
* `CRUD02`: Bắt lỗi khi Tên sản phẩm để trống.
* `CRUD03`: Bắt lỗi khi Giá sản phẩm <= 0.
* `CRUD04`: Cập nhật thông tin sản phẩm (Update).
* `CRUD05`: Xóa sản phẩm theo ID (Delete).
* `CRUD06`: Lấy danh sách toàn bộ sản phẩm (Read).

### 3. Module Kiểm Thử Giao Diện Web (`LoginUiTest.java`, `ProductUiTest.java`) - 6 Test Cases (Selenium POM)
* `UI_LOG_01`: Đăng nhập thành công với tài khoản hợp lệ.
* `UI_LOG_02`: Báo lỗi khi nhập sai mật khẩu trên form web.
* `UI_LOG_03`: Báo lỗi khi để trống tên đăng nhập.
* `UI_PROD_01`: Thêm mới sản phẩm thành công trên bảng Web và cập nhật số hàng.
* `UI_PROD_02`: Báo lỗi khi để trống tên sản phẩm trên form.
* `UI_PROD_03`: Báo lỗi khi giá sản phẩm <= 0 trên form web.

---

## 💻 HƯỚNG DẪN CHẠY TEST
Chạy toàn bộ 28 test cases (22 Unit Tests + 6 Selenium UI Tests) trên Terminal:
```bash
mvn test
```
* **Kết quả:** `Tests run: 28, Failures: 0, Errors: 0, Skipped: 0` -> **BUILD SUCCESS (100% Passed)**!
