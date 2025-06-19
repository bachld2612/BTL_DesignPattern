-- Tạo database nếu chưa tồn tại
CREATE DATABASE IF NOT EXISTS cvdtt;
USE cvdtt;

-- Bảng admin (đã bỏ cột role, thêm AUTO_INCREMENT)
CREATE TABLE admin (
                       id_admin INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50),
                       password VARCHAR(50),
                       full_name VARCHAR(100),
                       phone VARCHAR(15)
);


-- Bảng suppliers
CREATE TABLE suppliers (
                           id_suppliers INT AUTO_INCREMENT PRIMARY KEY,
                           id_admin INT,
                           phone VARCHAR(15),
                           address VARCHAR(255),
                           email VARCHAR(100),
                           name VARCHAR(100),
                           FOREIGN KEY (id_admin) REFERENCES admin(id_admin)
);


-- Bảng customers
CREATE TABLE customers (
                           id_customers INT AUTO_INCREMENT PRIMARY KEY,
                           username VARCHAR(50),
                           password VARCHAR(50),
                           full_name VARCHAR(100),
                           phone VARCHAR(15),
                           address TEXT,
                           date_of_birth DATE,
                           points INT DEFAULT 0,
                           level VARCHAR(20) DEFAULT 'BRONZE'
);


-- Bảng products
CREATE TABLE products (
                          id_products INT AUTO_INCREMENT PRIMARY KEY,
                          id_suppliers INT,
                          name_products VARCHAR(100),
                          description TEXT,
                          price DECIMAL(10, 2),
                          state VARCHAR(20),
                          id_admin INT,
                          FOREIGN KEY (id_admin) REFERENCES admin(id_admin),
                          FOREIGN KEY (id_suppliers) REFERENCES suppliers(id_suppliers)
);


-- Bảng events
CREATE TABLE events (
                        id_events INT AUTO_INCREMENT PRIMARY KEY,
                        id_admin INT,
                        name_events VARCHAR(100),
                        start_date DATE,
                        end_date DATE,
                        content TEXT,
                        FOREIGN KEY (id_admin) REFERENCES admin(id_admin)
);


-- Bảng carts
CREATE TABLE carts (
                       id_carts INT AUTO_INCREMENT PRIMARY KEY,
                       id_customers INT,
                       FOREIGN KEY (id_customers) REFERENCES customers(id_customers)
);


-- Bảng product_carts
CREATE TABLE product_carts (
                               id_carts INT,
                               id_product INT,
                               PRIMARY KEY (id_carts, id_product),
                               FOREIGN KEY (id_carts) REFERENCES carts(id_carts),
                               FOREIGN KEY (id_product) REFERENCES products(id_products)
);


-- Bảng bookings
CREATE TABLE bookings (
                          id_bookings INT AUTO_INCREMENT PRIMARY KEY,
                          id_customers INT,
                          id_carts INT,
                          start_date DATE,
                          end_date DATE,
                          status VARCHAR(20),
                          amount DECIMAL(10, 2),
                          FOREIGN KEY (id_customers) REFERENCES customers(id_customers),
                          FOREIGN KEY (id_carts) REFERENCES carts(id_carts)
);


-- Bảng earning_cards
CREATE TABLE earning_cards (
                               id_earning_cards INT AUTO_INCREMENT PRIMARY KEY,
                               id_customers INT,
                               amount DECIMAL(10, 2),
                               card_class INT,
                               FOREIGN KEY (id_customers) REFERENCES customers(id_customers)
);


-- Bảng buy_bills
CREATE TABLE buy_bills (
                           id_buy_bills INT AUTO_INCREMENT PRIMARY KEY,
                           id_admin INT,
                           amount DECIMAL(10, 2),
                           buy_date DATE,
                           state VARCHAR(50),
                           FOREIGN KEY (id_admin) REFERENCES admin(id_admin)
);


-- Bảng buy_bill_products
CREATE TABLE buy_bill_products (
                                   id_product INT,
                                   id_buy_bills INT,
                                   PRIMARY KEY (id_product, id_buy_bills),
                                   FOREIGN KEY (id_product) REFERENCES products(id_products),
                                   FOREIGN KEY (id_buy_bills) REFERENCES buy_bills(id_buy_bills)
);

-- Bảng orders
CREATE TABLE orders (
                        id_orders INT AUTO_INCREMENT PRIMARY KEY,
                        id_bookings INT,
                        order_date DATE,
                        total_amount DECIMAL(10, 2),
                        status VARCHAR(50),
                        payment_method VARCHAR(50),
                        note TEXT,
                        FOREIGN KEY (id_bookings) REFERENCES bookings(id_bookings)
);
-- Bảng vouchers
CREATE TABLE vouchers (
    id_vouchers INT AUTO_INCREMENT PRIMARY KEY,
    id_admin INT,
    id_order INT,
    name_vouchers VARCHAR(100),
    start_value DECIMAL(10, 2),
    end_value DECIMAL(10, 2),
    is_active BOOLEAN,
    code VARCHAR(20),
    discount_type VARCHAR(20),
    discount_value DOUBLE,
    FOREIGN KEY (id_admin) REFERENCES admin(id_admin),
    FOREIGN KEY (id_order) REFERENCES orders(id_orders)
);



-- Bảng sales_bills
CREATE TABLE sales_bills (
                             id_sales_bills INT AUTO_INCREMENT PRIMARY KEY,
                             id_orders INT,
                             amount INT,
                             booking_date DATE,
                             state VARCHAR(50),
                             FOREIGN KEY (id_orders) REFERENCES orders(id_orders)
);


CREATE TABLE discount (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          product_id INT NOT NULL,
                          discount_type ENUM('percent', 'amount') DEFAULT 'percent',
                          value FLOAT NOT NULL,
                          start_date DATE,
                          end_date DATE,
                          FOREIGN KEY (product_id) REFERENCES products(id_products) ON DELETE CASCADE
);



CREATE TABLE customer_event_subscriptions (
                                              id_subscription INT PRIMARY KEY AUTO_INCREMENT,
                                              id_customer INT,
                                              FOREIGN KEY (id_customer) REFERENCES customers(id_customers)
);


CREATE TABLE notifications (
                               id_notification INT PRIMARY KEY AUTO_INCREMENT,
                               id_customer INT,
                               id_event INT,
                               message TEXT,
                               sent_at DATETIME,
                               status VARCHAR(20),
                               FOREIGN KEY (id_customer) REFERENCES customers(id_customers),
                               FOREIGN KEY (id_event) REFERENCES events(id_events)
);

-- Thêm tài khoản admin mặc định
INSERT INTO admin (username, password, full_name, phone)
VALUES ('admin', 'admin', 'Administrator', '0123456789');

-- FAKE DATA FOR DEMO

-- Admin
INSERT INTO admin (username, password, full_name, phone) VALUES
('admin1', '123456', 'Nguyễn Văn A', '0901111111'),
('admin2', '123456', 'Trần Thị B', '0902222222');

-- Customers
INSERT INTO customers (username, password, full_name, phone, address, date_of_birth, points, level) VALUES
('user1', '123456', 'Lê Văn C', '0911111111', 'Hà Nội', '2000-01-01', 1200, 'SILVER'),
('user2', '123456', 'Phạm Thị D', '0912222222', 'Hồ Chí Minh', '1999-05-10', 6000, 'GOLD'),
('user3', '123456', 'Ngô Văn E', '0913333333', 'Đà Nẵng', '2001-09-15', 300, 'BRONZE');

-- Suppliers
INSERT INTO suppliers (id_admin, phone, address, email, name) VALUES
(1, '0909999999', 'Hà Nội', 'supplier1@email.com', 'Nhà cung cấp A'),
(2, '0908888888', 'Hồ Chí Minh', 'supplier2@email.com', 'Nhà cung cấp B');

-- Products
INSERT INTO products (id_suppliers, name_products, description, price, state, id_admin) VALUES
(1, 'Sản phẩm 1', 'Mô tả sản phẩm 1', 100000, 'active', 1),
(2, 'Sản phẩm 2', 'Mô tả sản phẩm 2', 200000, 'active', 2);

-- Vouchers
INSERT INTO vouchers (id_admin, id_order, name_vouchers, start_value, end_value, is_active, code, discount_type, discount_value) VALUES
(1, NULL, 'Voucher 10%', 0, 1000000, 1, 'VOUCHER10', 'percent', 10),
(2, NULL, 'Voucher 50k', 500000, 2000000, 1, 'VOUCHER50K', 'amount', 50000);

-- Carts
INSERT INTO carts (id_customers) VALUES (1), (2);

-- Product_carts
INSERT INTO product_carts (id_carts, id_product) VALUES
(1, 1),
(2, 2);

-- Bookings
INSERT INTO bookings (id_customers, id_carts, start_date, end_date, status, amount) VALUES
(1, 1, '2024-06-01', '2024-06-02', 'PENDING', 100000),
(2, 2, '2024-06-01', '2024-06-02', 'PENDING', 200000);

-- Orders
INSERT INTO orders (id_bookings, order_date, total_amount, status, payment_method, note) VALUES
(1, '2024-06-02', 100000, 'PENDING', 'CASH', 'Giao hàng nhanh'),
(2, '2024-06-02', 200000, 'PAID', 'CARD', 'Giao hàng tiêu chuẩn');

-- Earning_cards
INSERT INTO earning_cards (id_customers, amount, card_class) VALUES
(1, 50000, 1),
(2, 100000, 2);
