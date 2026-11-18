# 🤖 NHẬT KÝ MINH CHỨNG ỨNG DỤNG AI: GIAI ĐOẠN 5 — TỐI ƯU & REVIEW CODE (Y6.5)
> **Dự án:** Poly Coffee Management  
> **Môn học:** SOF309 — Kiểm thử nâng cao cùng AI  
> **Sinh viên thực hiện:** Trần Quang Bình  
> **Giai đoạn:** Áp dụng AI thực hiện Code Review toàn diện, đánh giá Clean Code, SonarQube Standards và tối ưu hiệu năng kiểm thử

---

## 1. NGỮ CẢNH CẤP CHO AI (RAG CONTEXT)
* **Toàn bộ mã nguồn dự án Poly Coffee:**
  - Tầng Model: `User.java`, `Product.java`.
  - Tầng Service: `AuthService.java`, `ProductService.java`.
  - Tầng Kiểm thử Unit: `AuthServiceTest.java`, `ProductServiceTest.java`, `SmokeTestSuite.java`, `AllTestsSuite.java`.
  - Tầng Tự động hóa UI: `BasePage.java`, `LoginPage.java`, `RegisterPage.java`, `ProductPage.java`, `BaseUiTest.java`, `LoginUiTest.java`, `ProductUiTest.java`.
* **Mục tiêu:** Rà soát chất lượng mã nguồn trước khi bàn giao và đóng gói Assignment.

---

## 2. CÂU LỆNH PROMPT ĐÃ GỬI (PROMPT SUBMITTED)
```markdown
Bạn là một Technical Lead & QA Architect kỳ cựu.
Hãy tiến hành quy trình Automated Code Review cho toàn bộ mã nguồn của dự án Poly Coffee (Java 17, Maven, JUnit 5, Selenium POM).

Hãy đánh giá và cho điểm theo 5 tiêu chí sau:
1. Tuân thủ Nguyên tắc Clean Code & Chuẩn đặt tên Java (Naming Conventions).
2. Thiết kế kiến trúc kiểm thử (Tách biệt Concerns giữa Unit Test và UI Test).
3. Độ tin cậy và giải phóng tài nguyên (Resource Leakage: WebDriver lifecycle, Memory).
4. Khả năng bảo trì và tái sử dụng (Mô hình Page Object Model, DRY principle).
5. Các lỗ hổng tiềm ẩn (Potential Code Smells & Bugs).

Đưa ra các khuyến nghị tối ưu cụ thể kèm mã nguồn mẫu.
```

---

## 3. KẾT QUẢ THÔ DO AI SINH RA (RAW AI OUTPUT)
AI đã phân tích và phản hồi báo cáo Code Review chi tiết:

* **Điểm mạnh (Strengths):**
  - Cấu trúc thư mục chuẩn mực theo kiến trúc Maven tiêu chuẩn (`src/main/java` vs `src/test/java`).
  - Áp dụng triệt để mô hình Page Object Model (POM) tách biệt rạch ròi giữa Page Element/Action và Test Assertions.
  - Sử dụng linh hoạt các tính năng cao cấp của JUnit 5: `@ParameterizedTest`, `@CsvSource`, `@Tag("smoke")`, `@SelectClasses`.
  - Chạy UI test bằng Chrome Headless giúp tối ưu thời gian thực thi đáng kể (dưới 7 giây cho toàn bộ 28 tests).

* **Các điểm cảnh báo và đề xuất cải tiến (Areas for Improvement):**
  1. *Resource Leakage rủi ro cao:* Cần đảm bảo `driver.quit()` luôn được gọi trong khối `@AfterEach` hoặc thông qua phương thức kế thừa từ `BaseUiTest`, tránh treo process `chromedriver` chạy ngầm chiếm dụng RAM của hệ điều hành.
  2. *Hardcoded Strings:* Các thông điệp lỗi và CSS Selector đang nằm rải rác trong code; nên khai báo thành các hằng số `private static final By ...` hoặc `ErrorCode`.
  3. *Thread-Safe trong Data Storage:* `AuthService` và `ProductService` đang dùng `List<User>` và `List<Product>` dạng `ArrayList` thông thường. Nếu chạy test song song (`parallel execution`), có thể xảy ra `ConcurrentModificationException`. Nên đề xuất dùng `CopyOnWriteArrayList` hoặc `Collections.synchronizedList`.

---

## 4. ĐÁNH GIÁ & HOÀN THIỆN CỦA CON NGƯỜI (HUMAN REFINEMENT)
Sinh viên đã tiếp thu chọn lọc các khuyến nghị của AI và tiến hành refactor:
1. **Quản lý Vòng đời WebDriver an toàn tuyệt đối:**
   - Trong `BaseUiTest.java`, sinh viên thiết lập hàm `tearDown()` với cờ kiểm tra `if (driver != null) { driver.quit(); driver = null; }` được đánh dấu annotation `@AfterEach` để đảm bảo 100% session trình duyệt được giải phóng sau mỗi ca test, kể cả khi test case gặp sự cố bất ngờ.
2. **Chuẩn hóa Hằng số Định danh trong Page Object:**
   - Trong các class Page (`LoginPage`, `RegisterPage`, `ProductPage`), 100% các selector `By.id`, `By.cssSelector` đều được đóng gói thành các hằng số `private static final By ...` ở đầu file theo đúng chuẩn mực thiết kế OOP.
3. **Giữ cấu trúc tinh gọn theo ngữ cảnh học phần:**
   - Với gợi ý cấu hình Thread-safe cho Service, sinh viên nhận định dự án kiểm thử học phần SOF309 chạy đơn luồng (single-thread sequential test suites) nên việc dùng `ArrayList` tiêu chuẩn giúp mã nguồn sáng sủa, dễ hiểu cho sinh viên, không gây phức tạp hóa kiến trúc (tránh over-engineering).
