# 🤖 NHẬT KÝ MINH CHỨNG ỨNG DỤNG AI: GIAI ĐOẠN 4 — THIẾT LẬP CI/CD GITHUB ACTIONS (Y6.4)
> **Dự án:** Poly Coffee Management  
> **Môn học:** SOF309 — Kiểm thử nâng cao cùng AI  
> **Sinh viên thực hiện:** Trần Quang Bình  
> **Giai đoạn:** Thiết lập Pipeline CI/CD tự động hóa kiểm thử với GitHub Actions (Yêu cầu Y5 & Y6.4)

---

## 1. NGỮ CẢNH CẤP CHO AI (RAG CONTEXT)
* **Dự án:** Maven Java 17, JUnit 5, Selenium WebDriver 4.
* **Yêu cầu từ đề bài Assignment Y5:**
  - Tự động chạy test khi `push` hoặc tạo `pull_request` vào nhánh `main`.
  - Hỗ trợ chạy riêng nhóm Smoke Test (`SmokeTestSuite.java`).
  - Chạy toàn bộ 28 tests (Unit test + Selenium UI test ở chế độ headless).
  - Tự động upload báo cáo kiểm thử Surefire Reports và ảnh chụp màn hình lên mục Artifacts của GitHub.

---

## 2. CÂU LỆNH PROMPT ĐÃ GỬI (PROMPT SUBMITTED)
```markdown
Bạn là một Kỹ sư DevOps & QA Automation chuyên nghiệp.
Hãy viết một file workflow GitHub Actions (.github/workflows/test.yml) hoàn chỉnh cho dự án Maven Java 17 với các yêu cầu:
1. Chạy trên runner ubuntu-latest.
2. Cài đặt Java 17 với bộ nhớ đệm cache maven.
3. Chạy bước kiểm tra Smoke Test Suite trước.
4. Chạy toàn bộ bộ test tự động (mvn test) bao gồm cả Selenium UI tests.
5. Upload toàn bộ báo cáo target/surefire-reports/ lên GitHub Artifacts với cờ if: always().
6. Giải thích chi tiết từng step trong file YAML.
```

---

## 3. KẾT QUẢ THÔ DO AI SINH RA (RAW AI OUTPUT)
AI đã sinh ra cấu trúc workflow sử dụng:
* `actions/checkout@v4` và `actions/setup-java@v4`.
* Cấu hình caching Maven tự động để tăng tốc độ build.
* Sử dụng `actions/upload-artifact@v4` để đóng gói thư mục `target/surefire-reports/`.

---

## 4. ĐÁNH GIÁ & HOÀN THIỆN CỦA CON NGƯỜI (HUMAN REFINEMENT)
Sinh viên đã rà soát và bổ sung các điểm cải tiến thực chiến:
1. **Bổ sung kích hoạt thủ công (`workflow_dispatch`):** Giúp giảng viên hoặc sinh viên có thể bấm nút "Run workflow" trực tiếp trên giao diện GitHub Web để demo mà không cần phải push commit mới.
2. **Bổ sung bước Upload Screenshots:** Thêm riêng một artifact `ui-test-screenshots` để lưu trữ các ảnh chụp màn hình do Selenium sinh ra trong thư mục `screenshots/` phục vụ việc chấm bài trực quan.
3. **Tách biệt 2 bước Test:** Chạy `SmokeTestSuite` trước để kiểm tra nhanh (Fail Fast), sau đó mới chạy Full Suite toàn diện 28 tests.
