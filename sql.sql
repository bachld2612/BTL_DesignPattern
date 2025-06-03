-- Tạo database nếu chưa tồn tại
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'cvdtt')
BEGIN
    CREATE DATABASE [cvdtt];
END
GO

USE [cvdtt];
GO

-- Bảng admin
CREATE TABLE admin (
                       id_admin INT PRIMARY KEY,
                       username VARCHAR(50),
                       password VARCHAR(50),
                       full_name VARCHAR(100),
                       phone VARCHAR(15),
                       role VARCHAR(50)
);
GO

-- Bảng suppliers
CREATE TABLE suppliers (
                           id_suppliers INT PRIMARY KEY,
                           id_admin INT,
                           username_sup VARCHAR(50),
                           password_sup VARCHAR(50),
                           name_sup VARCHAR(100),
                           phone VARCHAR(15),
                           start_date DATE,
                           end_date DATE,
                           CONSTRAINT fk_suppliers_admin
                               FOREIGN KEY (id_admin) REFERENCES admin(id_admin)
);
GO

-- Bảng customers
CREATE TABLE customers (
                           id_customers INT PRIMARY KEY,
                           username VARCHAR(50),
                           password VARCHAR(50),
                           full_name VARCHAR(100),
                           phone VARCHAR(15),
                           address TEXT,
                           date_of_birth DATE
);
GO

-- Bảng products
CREATE TABLE products (
                          id_products INT PRIMARY KEY,
                          id_suppliers INT,
                          name_products VARCHAR(100),
                          description TEXT,
                          price DECIMAL(10, 2),
                          state VARCHAR(20),
                          id_admin INT,
                          CONSTRAINT fk_products_admin
                              FOREIGN KEY (id_admin) REFERENCES admin(id_admin),
                          CONSTRAINT fk_products_suppliers
                              FOREIGN KEY (id_suppliers) REFERENCES suppliers(id_suppliers)
);
GO

-- Bảng events
CREATE TABLE events (
                        id_events INT PRIMARY KEY,
                        id_admin INT,
                        name_events VARCHAR(100),
                        start_date DATE,
                        end_date DATE,
                        content TEXT,
                        CONSTRAINT fk_events_admin
                            FOREIGN KEY (id_admin) REFERENCES admin(id_admin)
);
GO

-- Bảng carts
CREATE TABLE carts (
                       id_carts INT PRIMARY KEY,
                       id_customers INT,
                       CONSTRAINT fk_carts_customers
                           FOREIGN KEY (id_customers) REFERENCES customers(id_customers)
);
GO

-- Bảng product_carts
CREATE TABLE product_carts (
                               id_carts INT,
                               id_product INT,
                               PRIMARY KEY (id_carts, id_product),
                               CONSTRAINT fk_product_carts_carts
                                   FOREIGN KEY (id_carts) REFERENCES carts(id_carts),
                               CONSTRAINT fk_product_carts_products
                                   FOREIGN KEY (id_product) REFERENCES products(id_products)
);
GO

-- Bảng bookings
CREATE TABLE bookings (
                          id_bookings INT PRIMARY KEY,
                          id_customers INT,
                          id_carts INT,
                          start_date DATE,
                          end_date DATE,
                          status VARCHAR(20),
                          amount DECIMAL(10, 2),
                          CONSTRAINT fk_bookings_customers
                              FOREIGN KEY (id_customers) REFERENCES customers(id_customers),
                          CONSTRAINT fk_bookings_carts
                              FOREIGN KEY (id_carts) REFERENCES carts(id_carts)
);
GO

-- Bảng earning_cards
CREATE TABLE earning_cards (
                               id_earning_cards INT PRIMARY KEY,
                               id_customers INT,
                               amount DECIMAL(10, 2),
                               card_class INT,
                               CONSTRAINT fk_earning_cards_customers
                                   FOREIGN KEY (id_customers) REFERENCES customers(id_customers)
);
GO

-- Bảng buy_bills
CREATE TABLE buy_bills (
                           id_buy_bills INT PRIMARY KEY,
                           id_admin INT,
                           amount DECIMAL(10, 2),
                           buy_date DATE,
                           state VARCHAR(50),
                           CONSTRAINT fk_buy_bills_admin
                               FOREIGN KEY (id_admin) REFERENCES admin(id_admin)
);
GO

-- Bảng buy_bill_products
CREATE TABLE buy_bill_products (
                                   id_product INT,
                                   id_buy_bills INT,
                                   PRIMARY KEY (id_product, id_buy_bills),
                                   CONSTRAINT fk_buy_bill_products_products
                                       FOREIGN KEY (id_product) REFERENCES products(id_products),
                                   CONSTRAINT fk_buy_bill_products_buy_bills
                                       FOREIGN KEY (id_buy_bills) REFERENCES buy_bills(id_buy_bills)
);
GO

-- Bảng orders
CREATE TABLE orders (
                        id_orders INT PRIMARY KEY,
                        id_bookings INT,
                        order_date DATE,
                        total_amount DECIMAL(10, 2),
                        status VARCHAR(50),
                        payment_method VARCHAR(50),
                        note TEXT,
                        CONSTRAINT fk_orders_bookings
                            FOREIGN KEY (id_bookings) REFERENCES bookings(id_bookings)
);
GO

-- Bảng vouchers
CREATE TABLE vouchers (
                          id_vouchers INT PRIMARY KEY,
                          id_admin INT,
                          id_order INT,
                          name_vouchers VARCHAR(100),
                          start_value DECIMAL(10, 2),
                          end_value DECIMAL(10, 2),
                          is_active BIT,
                          code VARCHAR(20),
                          CONSTRAINT fk_vouchers_admin
                              FOREIGN KEY (id_admin) REFERENCES admin(id_admin),
                          CONSTRAINT fk_vouchers_orders
                              FOREIGN KEY (id_order) REFERENCES orders(id_orders)
);
GO

-- Bảng sales_bills
CREATE TABLE sales_bills (
                             id_sales_bills INT PRIMARY KEY,
                             id_orders INT,
                             amount INT,
                             booking_date DATE,
                             state VARCHAR(50),
                             CONSTRAINT fk_sales_bills_orders
                                 FOREIGN KEY (id_orders) REFERENCES orders(id_orders)
);
GO
