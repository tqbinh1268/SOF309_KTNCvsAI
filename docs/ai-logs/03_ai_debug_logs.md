# 🤖 NHẬT KÝ MINH CHỨNG ỨNG DỤNG AI: GIAI ĐOẠN 3 — XỬ LÝ LỖI & DEBUG (Y6.3)
> **Dự án:** Poly Coffee Management  
> **Môn học:** SOF309 — Kiểm thử nâng cao cùng AI  
> **Sinh viên thực hiện:** Trần Quang Bình  
> **Giai đoạn:** Đưa Stack Trace cho AI phân tích nguyên nhân lỗi và đề xuất giải pháp khắc phục (Root Cause Analysis & Bug Fixing)

---

## 1. NGỮ CẢNH CẤP CHO AI (RAG CONTEXT)
* **Tình huống lỗi 1 (Unit Test):** Khi chạy `mvn test` ở giai đoạn Tag `v1.2-auth-test-red`, ca kiểm thử số điện thoại không hợp lệ bị thất bại.
* **Tình huống lỗi 2 (Selenium UI Test):** Khi chạy Selenium trên môi trường không đầu (Headless mode) hoặc mạng tải chậm, xuất hiện lỗi `NoSuchElementException: no such element: Unable to locate element: {"method":"css selector","selector":"#welcome-banner"}`.
* **Stack Trace trích xuất từ Terminal console nạp vào AI.**

---

## 2. CÂU LỆNH PROMPT ĐÃ GỬI (PROMPT SUBMITTED)
```markdown
Tôi đang chạy bộ kiểm thử tự động cho dự án Poly Coffee (Java 17, Maven, JUnit 5, Selenium 4.20) và gặp phải 2 lỗi sau:

### LỖI 1 (JUnit 5 Failure):
[ERROR] Failures: 
[ERROR]   AuthServiceTest.testRegister_InvalidPhone_ShouldThrowException:68 
Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.

### LỖI 2 (Selenium UI Flaky Failure):
org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {"method":"css selector","selector":"#welcome-banner"}
  (Session info: chrome-headless-shell=124.0.6367.60)
  at com.poly.coffee.ui.pages.LoginPage.getWelcomeMessage(LoginPage.java:42)
  at com.poly.coffee.ui.tests.LoginUiTest.testLoginSuccess(LoginUiTest.java:31)

Nhiệm vụ của bạn:
1. Giải thích nguyên nhân gốc rễ (Root Cause) của từng lỗi trên.
2. Đề xuất đoạn mã sửa lỗi chính xác cho cả tầng Service (Java) và tầng Page Object (Selenium).
3. Đề xuất giải pháp ngăn chặn lỗi tương tự xảy ra trong tương lai (Best Practices).
```

---

## 3. KẾT QUẢ THÔ DO AI SINH RA (RAW AI OUTPUT)
AI đã phân tích Stack Trace và đưa ra chẩn đoán:
1. **Phân tích Lỗi 1:**
   - *Nguyên nhân:* Phương thức `register()` trong `AuthService.java` chưa kiểm tra biểu thức chính quy (Regex) của trường `phone`. Dẫn đến khi truyền số điện thoại sai định dạng (chữ cái hoặc không đủ 10 số), hàm không ném ngoại lệ `IllegalArgumentException` như kỳ vọng của `assertThrows()`.
   - *Đề xuất code:* Bổ sung điều kiện kiểm tra regex:
     ```java
     if (phone == null || !phone.matches("^0\\d{9}$")) {
         throw new IllegalArgumentException("Số điện thoại không hợp lệ! (Phải đủ 10 số và bắt đầu bằng số 0)");
     }
     ```
2. **Phân tích Lỗi 2:**
   - *Nguyên nhân:* Kiểm thử UI chạy theo cơ chế bất đồng bộ (DOM rendering delay). Khi trình duyệt chuyển hướng hoặc xử lý JavaScript sau khi click submit, element `#welcome-banner` chưa kịp xuất hiện trong DOM thì Selenium đã gọi lệnh `driver.findElement()`, dẫn tới `NoSuchElementException`.
   - *Đề xuất code:* Loại bỏ `Thread.sleep()`, áp dụng mô hình **Explicit Wait** (`WebDriverWait` kết hợp `ExpectedConditions.visibilityOfElementLocated`).

---

## 4. ĐÁNH GIÁ & HOÀN THIỆN CỦA CON NGƯỜI (HUMAN REFINEMENT)
Sinh viên đã áp dụng tư duy phản biện (Human-in-the-Loop) để tinh chỉnh:
1. **Tinh chỉnh Regex an toàn:** Ngoài regex `^0\\d{9}$`, sinh viên bổ sung thêm bước `.trim()` loại bỏ khoảng trắng thừa đầu cuối trước khi validate để tránh bẫy nhập liệu người dùng.
2. **Chuẩn hóa WebDriverWait tập trung:** Thay vì khai báo `WebDriverWait` rải rác trong từng hàm của Page Object như AI đề xuất, sinh viên đã refactor và chuyển toàn bộ cơ chế chờ tường minh vào class cha `BasePage.java` (hàm `waitForVisibility(By locator, Duration timeout)`) để tái sử dụng sạch sẽ cho toàn bộ các Page khác (`LoginPage`, `RegisterPage`, `ProductPage`).
3. **Thêm cơ chế tự động chụp ảnh màn hình khi có lỗi:** Tích hợp `TakesScreenshot` vào `BaseUiTest` để lưu file PNG vào thư mục `screenshots/` mỗi khi test case thất bại, giúp CI/CD dễ dàng điều tra lỗi từ xa.
