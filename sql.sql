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
    username_sup VARCHAR(50),
    password_sup VARCHAR(50),
    name_sup VARCHAR(100),
    phone VARCHAR(15),
    start_date DATE,
    end_date DATE,
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
    level VARCHAR(20) DEFAULT 'BRONZE',
    FOREIGN KEY (id_customers) REFERENCES customers(id_customers)
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

-- Thêm tài khoản admin mặc định
INSERT INTO admin (username, password, full_name, phone)
VALUES ('admin', 'admin', 'Administrator', '0123456789');

-- Thêm dữ liệu mẫu cho customers
INSERT INTO customers (username, password, full_name, phone, address, date_of_birth, points, level)
VALUES 
('user1', 'pass1', 'John Doe', '0123456789', '123 Main St', '1990-01-01', 1500, 'SILVER'),
('user2', 'pass2', 'Jane Smith', '0987654321', '456 Oak St', '1992-02-02', 6000, 'GOLD');

-- Thêm dữ liệu mẫu cho suppliers
INSERT INTO suppliers (id_admin, username_sup, password_sup, name_sup, phone, start_date, end_date)
VALUES 
(1, 'sup1', 'pass1', 'Nike Vietnam', '0123456789', '2024-01-01', '2024-12-31'),
(1, 'sup2', 'pass2', 'Adidas Vietnam', '0987654321', '2024-01-01', '2024-12-31'),
(1, 'sup3', 'pass3', 'Puma Vietnam', '0123456788', '2024-01-01', '2024-12-31');

-- Thêm dữ liệu mẫu cho products
INSERT INTO products (id_suppliers, name_products, description, price, state, id_admin)
VALUES 
(1, 'Nike Air Max', 'Giày thể thao Nike Air Max', 25000, 'ACTIVE', 1),
(1, 'Nike Air Force', 'Giày thể thao Nike Air Force', 22000, 'ACTIVE', 1),
(2, 'Adidas Ultraboost', 'Giày chạy bộ Adidas Ultraboost', 28000, 'ACTIVE', 1),
(2, 'Adidas Superstar', 'Giày thể thao Adidas Superstar', 19000, 'ACTIVE', 1),
(3, 'Puma RS-X', 'Giày thể thao Puma RS-X', 21000, 'ACTIVE', 1),
(3, 'Puma Future', 'Giày đá bóng Puma Future', 23000, 'ACTIVE', 1);

-- Thêm dữ liệu mẫu cho carts
INSERT INTO carts (id_customers)
VALUES 
(1), -- Giỏ hàng của user1
(2); -- Giỏ hàng của user2

-- Thêm sản phẩm vào giỏ hàng
INSERT INTO product_carts (id_carts, id_product)
VALUES 
(1, 1), -- user1 thêm Nike Air Max vào giỏ
(1, 3), -- user1 thêm Adidas Ultraboost vào giỏ
(2, 2), -- user2 thêm Nike Air Force vào giỏ
(2, 4), -- user2 thêm Adidas Superstar vào giỏ
(2, 5); -- user2 thêm Puma RS-X vào giỏ
