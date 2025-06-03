# Hướng dẫn chạy ứng dụng Swing

## 1. Mô tả chung
Tài liệu này hướng dẫn cách thiết lập và chạy ứng dụng Java Swing, bao gồm việc tạo cơ sở dữ liệu, cấu hình kết nối và khởi động ứng dụng thông qua MySQL.

---

## 2. Yêu cầu (Prerequisites)

- **Java Development Kit (JDK)**: Phiên bản Java 21 hoặc mới hơn.
- **MySQL Server**: Phiên bản 8.x, đang chạy tại `localhost:3306`.
- **SQL Script**: File `sql.sql` chứa các lệnh tạo cơ sở dữ liệu và bảng cần thiết.
- **Tệp cấu hình**: `db.properties`, nằm tại đường dẫn `/src/main/resources/`.
- **IDE**: IntelliJ IDEA hoặc IDE bất kỳ hỗ trợ Maven.

---

## 3. Cách chạy ứng dụng

### Bước 1: Tạo cơ sở dữ liệu
- Mở **MySQL Workbench**, **DBeaver**, hoặc dòng lệnh MySQL.
- Chạy tệp `sql.sql` để tạo cơ sở dữ liệu `cvdtt` và các bảng tương ứng.

### Bước 2: Cấu hình kết nối
- Truy cập tệp `/src/main/resources/db.yml`.
- Cập nhật nội dung cho đúng thông tin MySQL trên máy bạn:

```properties
url=jdbc:mysql://localhost:3306/cvdtt?useSSL=false&serverTimezone=UTC
username=root
password=your_password
