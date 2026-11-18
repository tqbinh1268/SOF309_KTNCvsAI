# 🤖 NHẬT KÝ MINH CHỨNG ỨNG DỤNG AI: GIAI ĐOẠN 1 — THIẾT KẾ TEST (Y6.1)
> **Dự án:** Poly Coffee Management  
> **Môn học:** SOF309 — Kiểm thử nâng cao cùng AI  
> **Sinh viên thực hiện:** Trần Quang Bình  
> **Giai đoạn:** Thiết kế Test Plan & Test Cases áp dụng kỹ thuật RAG với Gen AI

---

## 1. NGỮ CẢNH CẤP CHO AI (RAG CONTEXT)
* **Nguồn tài liệu:** File [docs/SPECIFICATIONS.md](../SPECIFICATIONS.md).
* **Nội dung tóm tắt nạp vào Prompt:**
  - Mô hình thực thể: `User` (id, fullname, username, password, phone, role) và `Product` (id, name, price, quantity, category).
  - Toàn bộ ma trận Business Rules: `BR-AUTH-01` (6 ràng buộc Đăng ký), `BR-AUTH-02` (4 ràng buộc Đăng nhập), `BR-PROD-01` đến `BR-PROD-04` (CRUD Sản phẩm).

---

## 2. CÂU LỆNH PROMPT ĐÃ GỬI (PROMPT SUBMITTED)
```markdown
Bạn là một Chuyên gia Kiểm thử Tự động Cao cấp (QA Automation Lead).
Dưới đây là tài liệu đặc tả nghiệp vụ (SRS) của dự án Poly Coffee:
[Trích dẫn nội dung từ docs/SPECIFICATIONS.md]

Nhiệm vụ của bạn:
1. Hãy áp dụng 2 kỹ thuật thiết kế kiểm thử hộp đen chuẩn mực: 
   - Kỹ thuật Phân vùng tương đương (Equivalence Partitioning).
   - Kỹ thuật Phân tích giá trị biên (Boundary Value Analysis).
2. Xây dựng Kế hoạch kiểm thử ngắn gọn (Test Plan).
3. Lập Bảng kịch bản kiểm thử chi tiết (Test Cases) cho cả 2 module: Xác thực và CRUD Sản phẩm.
4. Đề xuất ít nhất 5 ca kiểm thử tham số hóa (@ParameterizedTest + @CsvSource) cho các trường hợp kiểm tra biên.
```

---

## 3. KẾT QUẢ THÔ DO AI SINH RA (RAW AI OUTPUT)
AI đã phân tích tài liệu và sinh ra:
* Một khung Test Plan chuẩn bao gồm mục tiêu, phạm vi in-scope, out-of-scope, môi trường Java 17 và JUnit 5.
* Danh sách 16 Test Cases cho Module Xác thực (TC01 - TC11 và 5 ca DDT).
* Danh sách 6 Test Cases cho Module Sản phẩm (CRUD01 - CRUD06).
* Nhận diện được điểm nhạy cảm biên của Mật khẩu ($Min = 6$, kiểm tra tại $5$) và Số điện thoại ($10$ chữ số, kiểm tra tại $9$).

---

## 4. ĐÁNH GIÁ & HOÀN THIỆN CỦA CON NGƯỜI (HUMAN REFINEMENT)
Sinh viên đã rà soát và thực hiện các điều chỉnh quan trọng sau:
1. **Bổ sung kiểm thử Case-Insensitive:** AI ban đầu chỉ check trùng username theo chuỗi thường. Con người bổ sung ca test `BINHTQ7` vs `binhtq7` (`TC07`) để đảm bảo tính an toàn tài khoản.
2. **Chuẩn hóa Thông báo Ngoại lệ:** Điều chỉnh thông điệp ném lỗi trong code test khớp 100% với đặc tả tiếng Việt sư phạm (ví dụ: *"Số điện thoại không hợp lệ! (Phải đủ 10 số và bắt đầu bằng số 0)"* thay vì thông điệp tiếng Anh chung chung do AI đề xuất).
3. **Định dạng bảng Markdown chuẩn:** Chuyển đổi cấu trúc bảng sang format rõ ràng gồm Test_ID, Rule, Input, Expected Result, Exception Type để dễ dàng import vào báo cáo đồ án.
