# 📋 KẾ HOẠCH KIỂM THỬ TỰ ĐỘNG (AUTOMATION TEST PLAN)
> **Dự án:** Poly Coffee Management  
> **Môn học:** SOF309 — Kiểm thử nâng cao cùng AI  
> **Giảng viên hướng dẫn:** ThS. Trần Quang Bình (Thầy Mười Giáo — OMG · `binhtq7@fpt.edu.vn`)  
> **Yêu cầu Assignment:** Y2 — Thiết kế Kế hoạch & Kịch bản kiểm thử áp dụng Gen AI + RAG

---

## 🎯 1. MỤC TIÊU KIỂM THỬ (TEST OBJECTIVES)
1. Đảm bảo toàn bộ các quy tắc nghiệp vụ trong [docs/SPECIFICATIONS.md](SPECIFICATIONS.md) (`BR-AUTH` và `BR-PROD`) được kiểm chứng tự động.
2. Ngăn ngừa hồi quy (Regression Testing) thông qua bộ Unit Test JUnit 5 và Selenium UI Test.
3. Đạt tỷ lệ bao phủ kiểm thử (Code Coverage) tối thiểu **85%** cho tầng Service.
4. Tích hợp kiểm thử liên tục vào pipeline CI/CD GitHub Actions với thời gian chạy tối ưu.

---

## 🔍 2. PHẠM VI KIỂM THỬ (TEST SCOPE)

### Trong phạm vi (In-Scope):
* **Kiểm thử Đơn vị Backend (Unit Test - JUnit 5):**
  - Chức năng Đăng ký tài khoản (`AuthService.register`).
  - Chức năng Đăng nhập hệ thống (`AuthService.login`).
  - Toàn bộ nghiệp vụ CRUD Sản phẩm (`ProductService.create`, `getAll`, `findById`, `update`, `delete`).
* **Kiểm thử Giao diện Web (Automation Test UI - Selenium WebDriver 4):**
  - Tự động điền form Đăng ký, Đăng nhập, kiểm tra thông báo lỗi.
  - Quản lý danh sách sản phẩm trên trình duyệt Chrome.
* **Kiểm thử Tích hợp Liên tục (CI/CD - GitHub Actions):**
  - Tự động kích hoạt khi có commit/PR trên máy chủ Linux Ubuntu ảo (`--headless=new`).

### Ngoài phạm vi (Out-of-Scope):
* Kiểm thử hiệu năng chịu tải hàng triệu người dùng đồng thời (Load/Stress Test).
* Kiểm thử bảo mật xâm nhập nâng cao (Penetration Testing).

---

## 🛠️ 3. MÔI TRƯỜNG & CÔNG CỤ (TEST ENVIRONMENT & TOOLS)

| Thành phần | Công nghệ / Phiên bản | Mục đích sử dụng |
| :--- | :--- | :--- |
| **Ngôn ngữ** | Java 17 (LTS) | Nền tảng thực thi ứng dụng và test |
| **Quản lý dự án** | Apache Maven 3.9+ | Quản lý phụ thuộc và chạy build test |
| **Unit Test Framework**| JUnit Jupiter 5.10.2 | Khung viết và chạy test cases đơn vị |
| **Kiểm thử Tham số hóa**| JUnit Params (`@CsvSource`) | Data-Driven Testing (DDT) cho dữ liệu biên |
| **Test Suites** | JUnit Platform Suite 1.10.2 | Gom nhóm test (`AllTestsSuite`, `SmokeTestSuite`) |
| **UI Automation** | Selenium WebDriver 4.18.1 | Tự động hóa tương tác trình duyệt |
| **Trình duyệt** | Google Chrome & ChromeDriver | Môi trường hiển thị web (hỗ trợ Headless) |
| **CI/CD Platform** | GitHub Actions (`ubuntu-latest`)| Máy chủ ảo chạy kiểm thử tự động trên Cloud |
| **Công cụ Gen AI** | Gemini / ChatGPT / Cursor AI | Trợ lý thiết kế test, debug và review code |

---

## 📐 4. KỸ THUẬT THIẾT KẾ KIỂM THỬ (TEST DESIGN TECHNIQUES)
1. **Kỹ thuật Phân vùng tương đương (Equivalence Partitioning):**
   - Phân chia tập dữ liệu đầu vào thành các lớp: Lớp Hợp lệ (Valid Class) và Lớp Bất hợp lệ (Invalid Class).
   - Mỗi lớp chỉ cần chọn 1 ca kiểm thử đại diện.
2. **Kỹ thuật Phân tích giá trị biên (Boundary Value Analysis - BVA):**
   - Tập trung kiểm thử các điểm nhạy cảm: Giá trị nhỏ nhất ($Min$), Dưới cận ($Min-1$), Trên cận ($Min+1$), Bằng 0, Số âm, Rỗng.
   - Áp dụng triệt để cho độ dài Mật khẩu ($\ge 6$), Số điện thoại (10 chữ số), Giá sản phẩm ($> 0$), Số lượng tồn kho ($\ge 0$).

---

## 🚦 5. TIÊU CHÍ DỪNG & NGHIỆM THU (PASS / FAIL CRITERIA)
* **Tiêu chí ĐẠT (Pass):** 100% các Test Cases trong bộ kiểm thử chạy thành công (`BUILD SUCCESS`), không có ca kiểm thử nào bị lỗi (Errors = 0, Failures = 0).
* **Tiêu chí KHÔNG ĐẠT (Fail):** Bất kỳ ca kiểm thử nào ném ngoại lệ không mong đợi hoặc sai giá trị Assert. Phải ghi nhận lỗi, bàn giao lại Dev sửa và chạy lại chu kỳ kiểm thử.
