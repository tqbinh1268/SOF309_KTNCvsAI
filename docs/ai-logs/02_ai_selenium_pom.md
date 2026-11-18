# 🤖 NHẬT KÝ MINH CHỨNG ỨNG DỤNG AI: GIAI ĐOẠN 2 — XÂY DỰNG SELENIUM POM (Y6.2)
> **Dự án:** Poly Coffee Management  
> **Môn học:** SOF309 — Kiểm thử nâng cao cùng AI  
> **Sinh viên thực hiện:** Trần Quang Bình  
> **Giai đoạn:** Thiết kế mô hình Page Object Model (POM) và Test Script với Gen AI / Cursor AI

---

## 1. NGỮ CẢNH CẤP CHO AI (RAG CONTEXT)
* **Nguồn tài liệu:** [docs/SPECIFICATIONS.md](../SPECIFICATIONS.md) (Mục `BR-UI-01` đến `BR-UI-03`).
* **Mô tả cấu trúc:**
  - Trang Đăng nhập (`login.html`): `#username`, `#password`, `#btn-login`, `#error-message`, `#success-message`.
  - Trang Quản lý Sản phẩm (`products.html`): `#name`, `#price`, `#quantity`, `#category`, `#btn-add-product`, `#product-table`.
  - Yêu cầu kỹ thuật: Tách biệt hoàn toàn giữa **Locators / Actions (Page Objects)** và **Assertions / Test Execution (Test Classes)**. Cần cơ chế chụp ảnh màn hình (Screenshot) tự động khi test fail và hỗ trợ chế độ Headless cho CI/CD.

---

## 2. CÂU LỆNH PROMPT ĐÃ GỬI (PROMPT SUBMITTED)
```markdown
Bạn là một Chuyên gia Tự động hóa Kiểm thử Web (Selenium Automation Expert).
Tôi có các trang web HTML của dự án Poly Coffee với các phần tử ID như sau:
- login.html: username, password, btn-login, error-message, success-message.
- products.html: name, price, quantity, category, btn-add-product, product-table.

Hãy giúp tôi:
1. Thiết kế lớp cơ sở BasePage.java chứa WebDriver, WebDriverWait và phương thức takeScreenshot().
2. Xây dựng các lớp Page Object: LoginPage.java, ProductPage.java theo chuẩn POM.
3. Viết class kiểm thử JUnit 5: LoginUiTest.java và ProductUiTest.java cấu hình ChromeOptions hỗ trợ --headless=new cho CI/CD GitHub Actions.
```

---

## 3. KẾT QUẢ THÔ DO AI SINH RA (RAW AI OUTPUT)
* AI đã sinh ra bộ khung `BasePage` chứa `wait.until(ExpectedConditions.visibilityOfElementLocated())`.
* Đóng gói các hàm nghiệp vụ: `login(username, password)` và `addProduct(name, price, quantity, category)`.
* Sinh các class kiểm thử `LoginUiTest` và `ProductUiTest` với các assertion kiểm tra thông báo lỗi.

---

## 4. ĐÁNH GIÁ & HOÀN THIỆN CỦA CON NGƯỜI (HUMAN REFINEMENT)
Sinh viên đã rà soát và thực hiện các điều chỉnh chất lượng cao:
1. **Tối ưu hóa đường dẫn tĩnh:** Điều chỉnh phương thức `getPageUrl(pageName)` dùng `Paths.get("...").toUri().toString()` giúp test chạy được độc lập 100% bằng giao thức `file://`, không bị phụ thuộc vào port mạng của máy và chạy siêu nhanh trên mọi máy ảo Ubuntu.
2. **Cấu hình chống crash trình duyệt:** Thêm các flags quan trọng cho Chrome: `--no-sandbox`, `--disable-dev-shm-usage`, `--remote-allow-origins=*` để tránh lỗi `SessionNotCreatedException` khi đưa lên GitHub Actions.
3. **Phân loại nhãn kiểm thử:** Gán `@Tag("ui")` cho toàn bộ các class kiểm thử giao diện để dễ dàng phân tách với `@Tag("smoke")` và Unit Test backend.
