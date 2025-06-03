# Hướng dẫn chạy ứng dụng Swing

## 1. Mô tả chung
Tài liệu này hướng dẫn cách thiết lập và chạy ứng dụng Java Swing của bạn, bao gồm việc tạo cơ sở dữ liệu, cấu hình kết nối và khởi động ứng dụng.

---

## 2. Yêu cầu (Prerequisites)
- **Java Development Kit (JDK)**: Phiên bản Java 21 hoặc mới hơn đã được cài đặt trên máy.
- **SQL Server**: Đảm bảo SQL Server (ví dụ: SQL Server 2017, 2019 hoặc bản tương đương) đã được cài và đang chạy.
- **SQL Script**: Tệp SQL: sql.sql chứa lệnh tạo database và các bảng cần thiết.
- **Tệp cấu hình**: `db.yml` (nằm trong thư mục: `/src/main/java/com/bach/resources/`).
- **IDE**: IntelliJ IDEA.

---

## 3. Chạy ứng dụng
1. **Mở SQL Server Management Studio (SSMS)** hoặc công cụ tương đương, sau đó đăng nhập với tài khoản có quyền tạo database.
2. Chạy tệp SQL tạo sẵn (`sql.sql`)
3. **Truy cập tệp cấu hình**: `db.yml` trong thư mục: `/src/main/java/com/bach/resources/`
4. Sửa nội dung trong file tương ứng với cấu hình database của máy (username, password).
5. Chạy ứng dụng trên IntelliJ IDE với cấu hình đã được cài sẵn

## 4. Lưu ý:
Khi giao diện ứng dụng hiện ra bấm vào load user có thể bị văng lỗi vì dự án đang được set up cho việc test lỗi. Để test được chức năng này hãy làm theo các bước sau:
1. Tạo một database mới và config lại trong file `db.yml`
2. Tạo bảng users có 1 trường id với kiểu dữ liệu int
3. Thêm một vài dữ liệu cho bảng và tiến hành chạy app
