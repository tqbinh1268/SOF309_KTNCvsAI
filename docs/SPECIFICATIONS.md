# ☕ ĐẶC TẢ YÊU CẦU NGHIỆP VỤ HỆ THỐNG (SOFTWARE REQUIREMENTS SPECIFICATION - SRS)
> **Dự án:** Poly Coffee Management (Hệ thống Quản lý Quán Cà Phê)  
> **Môn học:** SOF309 — Kiểm thử nâng cao cùng AI (FPT Polytechnic)  
> **Giảng viên hướng dẫn:** ThS. Trần Quang Bình (Thầy Mười Giáo — OMG · `binhtq7@fpt.edu.vn`)  
> **Mục đích:** Tài liệu kim chỉ nam cung cấp ngữ cảnh nghiệp vụ (Business Context) phục vụ kỹ thuật RAG (Retrieval-Augmented Generation) khi phối hợp cùng AI thiết kế Test Plan & Test Cases (Yêu cầu Y2), đồng thời ánh xạ trực tiếp với bảng quản trị dự án **GitHub Project (Kanban)**.

---

## 🏛️ PHẦN I. MA TRẬN ÁNH XẠ 6 YÊU CẦU ĐỀ BÀI ASSIGNMENT (TRACEABILITY MATRIX)

Toàn bộ hệ thống kiểm thử được thiết kế bám sát 100% Đề bài Assignment gốc của FPT Polytechnic:

| Hạng mục đề bài | Tên Yêu Cầu Cốt Lõi | Mã Thành Phần Trong Source Code | Trọng số & Tiêu chí nghiệm thu |
| :---: | :--- | :--- | :--- |
| **Y1** | **Tích hợp Web Application Core** | • Models: `User.java`, `Product.java`<br>• Services: `AuthService.java`, `ProductService.java`<br>• Web UI: `/login.html`, `/register.html`, `/products.html` | Khởi chạy Web không lỗi, form hoạt động bình thường, dữ liệu lưu trong bộ nhớ. |
| **Y2** | **Thiết kế Test Case & Tài liệu (RAG)** | • SRS: `docs/SPECIFICATIONS.md`<br>• Kế hoạch: `docs/TEST_PLAN.md`<br>• Kịch bản: `docs/TEST_CASES.md` | Dùng Gen AI + RAG Context từ SRS, áp dụng Phân vùng tương đương & Phân tích giá trị biên. |
| **Y3** | **Lập trình Unit Test Backend (JUnit 5)** | • `AuthServiceTest.java` (16 tests)<br>• `ProductServiceTest.java` (6 tests)<br>• Suites: `AllTestsSuite.java`, `SmokeTestSuite.java` | 100% Pass (22/22 tests), áp dụng `@ParameterizedTest` + `@CsvSource`, phân nhóm `@Tag("smoke")`. |
| **Y4** | **Automation Test UI với Selenium (POM)** | • Pages: `LoginPage.java`, `RegisterPage.java`, `ProductPage.java`<br>• Tests: `LoginTest.java`, `ProductTest.java` | Áp dụng chuẩn mô hình Page Object Model, tự động điền form, kiểm tra Alert, hỗ trợ `--headless=new`. |
| **Y5** | **Tự động hóa CI/CD với GitHub Actions** | • Workflow: `.github/workflows/test.yml` | Tự động kích hoạt khi `push`/`pull_request`, chạy group smoke, upload báo cáo Test Report lên Artifacts. |
| **Y6** | **Minh chứng Ứng dụng AI (Điểm A)** | • Thư mục: `docs/ai-logs/`<br>(5 giai đoạn: Test Design, POM, Debug, CI/CD, Review) | Đầy đủ 4 trường thông tin: Ngữ cảnh (RAG Context) $\rightarrow$ Prompt $\rightarrow$ Raw Output $\rightarrow$ Human Refinement. |

---

## 📦 PHẦN II. THIẾT KẾ ĐỐI TƯỢNG DỮ LIỆU (DATA MODELS)

### 1. Thực thể Người dùng (`User.java`)
| Trường dữ liệu | Kiểu dữ liệu | Ràng buộc nghiệp vụ | Ý nghĩa |
| :--- | :--- | :--- | :--- |
| `id` | `Long` | Khóa chính, tự tăng $> 0$ | Mã định danh duy nhất của người dùng |
| `fullname` | `String` | Bắt buộc, không để trống | Họ và tên đầy đủ |
| `username` | `String` | Bắt buộc, duy nhất (Unique), không phân biệt hoa/thường | Tên tài khoản đăng nhập |
| `password` | `String` | Bắt buộc, độ dài $\ge 6$ ký tự | Mật khẩu tài khoản |
| `phone` | `String` | Bắt buộc, chuẩn 10 chữ số bắt đầu bằng `0` (`^0\d{9}$`) | Số điện thoại nhận hàng |
| `role` | `String` | Giá trị: `CUSTOMER` (khách hàng) hoặc `ADMIN` (quản trị) | Phân quyền truy cập |

### 2. Thực thể Sản phẩm (`Product.java`)
| Trường dữ liệu | Kiểu dữ liệu | Ràng buộc nghiệp vụ | Ý nghĩa |
| :--- | :--- | :--- | :--- |
| `id` | `Long` | Khóa chính, tự tăng $> 0$ | Mã sản phẩm |
| `name` | `String` | Bắt buộc, không để trống | Tên thức uống (VD: Cà phê đen, Cà phê sữa, Trà đào) |
| `price` | `Double` | Bắt buộc, giá trị $> 0$ | Đơn vị tính: VNĐ |
| `quantity` | `Integer` | Bắt buộc, giá trị $\ge 0$ | Số lượng ly/phần tồn kho |
| `category` | `String` | Bắt buộc (VD: `Coffee`, `Tea`, `Freeze`) | Danh mục đồ uống |

---

## 📜 PHẦN III. MA TRẬN QUY TẮC NGHIỆP VỤ (BUSINESS RULES - RAG INPUT)

### 🔐 1. Phân hệ Xác thực & Tài khoản (Auth Module)

#### [BR-AUTH-01] Đăng ký tài khoản (`AuthService.register`)
* **Đầu vào (Inputs):** `fullname`, `username`, `password`, `confirmPassword`, `phone`.
* **Quy tắc kiểm tra tính hợp lệ (Validation Rules):**
  1. `BR-AUTH-01.1`: `fullname` không được để trống (bỏ qua khoảng trắng đầu cuối). Nếu rỗng $\rightarrow$ Ném `IllegalArgumentException("Vui lòng nhập họ và tên!")`.
  2. `BR-AUTH-01.2`: `username` không được để trống. Nếu rỗng $\rightarrow$ Ném `IllegalArgumentException("Vui lòng nhập tên đăng nhập!")`.
  3. `BR-AUTH-01.3`: `password` phải có độ dài từ **6 ký tự trở lên**. Nếu $< 6$ $\rightarrow$ Ném `IllegalArgumentException("Mật khẩu phải từ 6 ký tự trở lên!")`.
  4. `BR-AUTH-01.4`: `confirmPassword` phải trùng khớp 100% với `password`. Nếu khác $\rightarrow$ Ném `IllegalArgumentException("Mật khẩu xác nhận không khớp!")`.
  5. `BR-AUTH-01.5`: `phone` phải chuẩn định dạng số điện thoại Việt Nam (10 chữ số, bắt đầu số `0` — Regex: `^0\d{9}$`). Nếu sai $\rightarrow$ Ném `IllegalArgumentException("Số điện thoại không hợp lệ! (Phải đủ 10 số và bắt đầu bằng số 0)")`.
  6. `BR-AUTH-01.6`: `username` không được trùng với tài khoản đã tồn tại trong hệ thống (so sánh không phân biệt hoa thường - Case Insensitive). Nếu trùng $\rightarrow$ Ném `IllegalArgumentException("Tên đăng nhập '<username>' đã tồn tại!")`.
* **Đầu ra (Outputs):** Trả về đối tượng `User` hợp lệ, `id > 0`, `role = "CUSTOMER"`.

#### [BR-AUTH-02] Đăng nhập hệ thống (`AuthService.login`)
* **Đầu vào (Inputs):** `username`, `password`.
* **Quy tắc kiểm tra:**
  1. `BR-AUTH-02.1`: Nếu bỏ trống `username` $\rightarrow$ Ném `IllegalArgumentException("Vui lòng nhập tên đăng nhập!")`.
  2. `BR-AUTH-02.2`: Nếu bỏ trống `password` $\rightarrow$ Ném `IllegalArgumentException("Vui lòng nhập mật khẩu!")`.
  3. `BR-AUTH-02.3`: Tự động cắt bỏ khoảng trắng thừa của username (`trim().toLowerCase()`).
  4. `BR-AUTH-02.4`: Nếu tài khoản không tồn tại hoặc sai mật khẩu $\rightarrow$ Ném `IllegalArgumentException("Tài khoản hoặc mật khẩu không chính xác!")` *(Chống tấn công thu thập tài khoản User Enumeration)*.
* **Đầu ra:** Trả về đối tượng `User` đã xác thực thành công.

---

### ☕ 2. Phân hệ Quản lý Sản phẩm (Product CRUD Module)

#### [BR-PROD-01] Thêm sản phẩm mới (`ProductService.create`)
* **Ràng buộc:**
  1. `BR-PROD-01.1`: `name` không được để trống $\rightarrow$ Ném `IllegalArgumentException("Tên sản phẩm không được để trống!")`.
  2. `BR-PROD-01.2`: `price` phải $> 0$. Nếu $\le 0$ $\rightarrow$ Ném `IllegalArgumentException("Giá sản phẩm phải lớn hơn 0!")`.
  3. `BR-PROD-01.3`: `quantity` phải $\ge 0$. Nếu $< 0$ $\rightarrow$ Ném `IllegalArgumentException("Số lượng không được âm!")`.
* **Đầu ra:** Trả về đối tượng `Product` với ID tự tăng.

#### [BR-PROD-02] Đọc danh sách & Tìm theo ID (`ProductService.getAll` / `findById`)
* `BR-PROD-02.1`: `getAll()` trả về danh sách toàn bộ sản phẩm.
* `BR-PROD-02.2`: `findById(id)` tìm kiếm theo ID. Nếu không thấy $\rightarrow$ Ném `IllegalArgumentException("Không tìm thấy sản phẩm với ID: " + id)`.

#### [BR-PROD-03] Cập nhật thông tin sản phẩm (`ProductService.update`)
* `BR-PROD-03.1`: ID cập nhật phải tồn tại trong hệ thống.
* `BR-PROD-03.2`: Áp dụng toàn bộ quy tắc kiểm tra tính hợp lệ về `name`, `price`, `quantity` như lúc thêm mới.

#### [BR-PROD-04] Xóa sản phẩm (`ProductService.delete`)
* `BR-PROD-04.1`: Kiểm tra sản phẩm có tồn tại theo ID. Nếu không có $\rightarrow$ Ném `IllegalArgumentException("Không tìm thấy sản phẩm để xóa với ID: " + id)`.
* `BR-PROD-04.2`: Xóa thành công khỏi bộ nhớ và trả về `true`.

---

### 🌐 3. Phân hệ Giao diện Web & Selenium POM (Web UI Module)

#### [BR-UI-01] Trang Đăng ký (`/register.html`)
* **Locators:**
  - `#fullname`: Ô nhập họ và tên
  - `#username`: Ô nhập tài khoản
  - `#password`: Ô nhập mật khẩu
  - `#confirmPassword`: Ô xác nhận mật khẩu
  - `#phone`: Ô nhập số điện thoại
  - `#btn-register`: Nút bấm "Đăng ký"
  - `.alert-danger`: Thẻ hiển thị câu thông báo lỗi
  - `.alert-success`: Thẻ hiển thị thông báo thành công

#### [BR-UI-02] Trang Đăng nhập (`/login.html`)
* **Locators:**
  - `#username`: Ô nhập tài khoản
  - `#password`: Ô nhập mật khẩu
  - `#btn-login`: Nút bấm "Đăng nhập"
  - `.alert-danger`: Thông báo sai thông tin

#### [BR-UI-03] Trang Quản lý Sản phẩm (`/products.html`)
* **Locators:**
  - `#product-table`: Bảng dữ liệu danh sách sản phẩm
  - `#btn-add-product`: Nút mở form thêm mới
  - `.btn-edit`: Nút sửa trên từng dòng
  - `.btn-delete`: Nút xóa trên từng dòng

---

## 🤖 PHẦN IV. MẪU PROMPT RAG CHUẨN ĐỂ SINH TEST PLAN & TEST CASES (YÊU CẦU Y2)

Sinh viên sao chép toàn bộ nội dung Phần I, II, III ở trên và gắn kèm vào mẫu Prompt dưới đây để yêu cầu AI sinh bộ tài liệu kiểm thử:

```markdown
Bạn là một QA Automation Lead dày dặn kinh nghiệm.
Dưới đây là Hồ sơ Đặc tả Nghiệp vụ (SRS) của dự án Poly Coffee:
[DÁN TOÀN BỘ PHẦN I, II, III CỦA FILE SPECIFICATIONS.MD VÀO ĐÂY]

Dựa vào các Business Rules trên, hãy thực hiện yêu cầu Y2 của đồ án:
1. Áp dụng kỹ thuật Phân vùng tương đương (Equivalence Partitioning) và Phân tích giá trị biên (Boundary Value Analysis).
2. Xây dựng Kế hoạch kiểm thử ngắn gọn (Test Plan) bao gồm: Mục tiêu, Phạm vi kiểm thử, Môi trường và Công cụ.
3. Thiết kế Bảng Test Cases chi tiết bao phủ 100% các Business Rules cho cả 2 phân hệ Auth và Product theo mẫu:
   | Test_ID | Business_Rule | Method_Tested | Input_Data | Expected_Result | Expected_Exception | Priority | Test_Type |
4. Chỉ ra ít nhất 5 bộ dữ liệu biên tham số hóa (@ParameterizedTest / @CsvSource) cho các trường hợp kiểm thử biên của Mật khẩu và Số điện thoại.
```
