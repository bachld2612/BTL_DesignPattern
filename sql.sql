-- Tạo database nếu chưa tồn tại
CREATE DATABASE IF NOT EXISTS cvdtt;
USE cvdtt;
GO

-- Bảng admin (đã bỏ cột role, thêm AUTO_INCREMENT)
CREATE TABLE admin (
                       id_admin INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50),
                       password VARCHAR(50),
                       full_name VARCHAR(100),
                       phone VARCHAR(15)
);
GO

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
GO

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
GO

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
GO

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
GO

-- Bảng carts
CREATE TABLE carts (
                       id_carts INT AUTO_INCREMENT PRIMARY KEY,
                       id_customers INT,
                       FOREIGN KEY (id_customers) REFERENCES customers(id_customers)
);
GO

-- Bảng product_carts
CREATE TABLE product_carts (
                               id_carts INT,
                               id_product INT,
                               PRIMARY KEY (id_carts, id_product),
                               FOREIGN KEY (id_carts) REFERENCES carts(id_carts),
                               FOREIGN KEY (id_product) REFERENCES products(id_products)
);
GO

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
GO

-- Bảng earning_cards
CREATE TABLE earning_cards (
                               id_earning_cards INT AUTO_INCREMENT PRIMARY KEY,
                               id_customers INT,
                               amount DECIMAL(10, 2),
                               card_class INT,
                               FOREIGN KEY (id_customers) REFERENCES customers(id_customers)
);
GO

-- Bảng buy_bills
CREATE TABLE buy_bills (
                           id_buy_bills INT AUTO_INCREMENT PRIMARY KEY,
                           id_admin INT,
                           amount DECIMAL(10, 2),
                           buy_date DATE,
                           state VARCHAR(50),
                           FOREIGN KEY (id_admin) REFERENCES admin(id_admin)
);
GO

-- Bảng buy_bill_products
CREATE TABLE buy_bill_products (
                                   id_product INT,
                                   id_buy_bills INT,
                                   PRIMARY KEY (id_product, id_buy_bills),
                                   FOREIGN KEY (id_product) REFERENCES products(id_products),
                                   FOREIGN KEY (id_buy_bills) REFERENCES buy_bills(id_buy_bills)
);
GO

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
GO

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
                          FOREIGN KEY (id_admin) REFERENCES admin(id_admin),
                          FOREIGN KEY (id_order) REFERENCES orders(id_orders)
);
GO

-- Bảng sales_bills
CREATE TABLE sales_bills (
                             id_sales_bills INT AUTO_INCREMENT PRIMARY KEY,
                             id_orders INT,
                             amount INT,
                             booking_date DATE,
                             state VARCHAR(50),
                             FOREIGN KEY (id_orders) REFERENCES orders(id_orders)
);
GO

CREATE TABLE discount (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          product_id INT NOT NULL,
                          discount_type ENUM('percent', 'amount') DEFAULT 'percent',
                          value FLOAT NOT NULL,
                          start_date DATE,
                          end_date DATE,
                          FOREIGN KEY (product_id) REFERENCES products(id_products) ON DELETE CASCADE
);

GO

CREATE TABLE customer_event_subscriptions (
                                              id_subscription INT PRIMARY KEY AUTO_INCREMENT,
                                              id_customer INT,
                                              FOREIGN KEY (id_customer) REFERENCES customers(id_customers)
);

GO
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

GO
-- Thêm tài khoản admin mặc định
INSERT INTO admin (username, password, full_name, phone)
VALUES ('admin', 'admin', 'Administrator', '0123456789');
GO
