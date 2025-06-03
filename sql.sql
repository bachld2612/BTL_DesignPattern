
create database cvdtt;
go
use cvdtt;
-- Bảng admin (gộp thuộc tính từ users)
CREATE TABLE admin (
    idAdmin INT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50),
    fullname VARCHAR(100),
    phone VARCHAR(15),
    role VARCHAR(50)
);
-- Bảng suppliers
CREATE TABLE suppliers (
    idSuppliers INT PRIMARY KEY,
	idAdmin INT,
    usernameSup VARCHAR(50),
    passwordSup VARCHAR(50),
    nameSup VARCHAR(100),
    phone VARCHAR(15),
    start_date DATE,
    end_date DATE,
	FOREIGN KEY (idAdmin) REFERENCES admin(idAdmin)
);

-- Bảng customers (gộp thuộc tính từ users)
CREATE TABLE customers (
    idCustomers INT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50),
    fullname VARCHAR(100),
    phone VARCHAR(15),
    address TEXT,
    date_of_birth DATE
);



-- Bảng products
CREATE TABLE products (
    idProducts INT PRIMARY KEY,
	idSuppliers INT,
    nameProducts VARCHAR(100),
    description TEXT,
    price DECIMAL(10, 2),
    state VARCHAR(20),
    idAdmin INT,
    FOREIGN KEY (idAdmin) REFERENCES admin(idAdmin),
	FOREIGN KEY (idSuppliers) REFERENCES suppliers(idSuppliers)
);

-- Bảng events
CREATE TABLE events (
    idEvents INT PRIMARY KEY,
	idAdmin INT,
    nameEvents VARCHAR(100),
    start_date DATE,
    end_date DATE,
    content TEXT,
	FOREIGN KEY (idAdmin) REFERENCES admin(idAdmin)
);

-- Bảng carts
CREATE TABLE carts (
    idCarts INT PRIMARY KEY,
    idCustomers INT,
    FOREIGN KEY (idCustomers) REFERENCES customers(idCustomers)
);
-- Bảng product_carts
CREATE TABLE product_carts (
    idCarts INT,
    idProduct INT,
    PRIMARY KEY (idCarts, idProduct),
    FOREIGN KEY (idCarts) REFERENCES carts(idCarts),
    FOREIGN KEY (idProduct) REFERENCES products(idProducts)
);

-- Bảng bookings
CREATE TABLE bookings (
    idBookings INT PRIMARY KEY,
    idCustomers INT,
	idCarts INT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(20),
    amount DECIMAL(10, 2),
    FOREIGN KEY (idCustomers) REFERENCES customers(idCustomers),
	FOREIGN KEY (idCarts) REFERENCES carts(idCarts),
);

-- Bảng earning_cards
CREATE TABLE earning_cards (
    idEarningCards INT PRIMARY KEY,
    idCustomers INT,
    amount DECIMAL(10, 2),
    cardClass INT,
    FOREIGN KEY (idCustomers) REFERENCES customers(idCustomers)
);

-- Bảng buy_bills
CREATE TABLE buy_bills (
    idBuyBills INT PRIMARY KEY,
	idAdmin INT,
    amount DECIMAL(10, 2),
    buy_date DATE,
	state varchar(50),
	FOREIGN KEY (idAdmin) REFERENCES admin(idAdmin)
);

-- Bảng buy_bill_products
CREATE TABLE buy_bill_products (
--    idBuyProducts INT PRIMARY KEY,
    idProduct INT,
    idBuyBills INT,
	PRIMARY KEY (idProduct, idBuyBills),
    FOREIGN KEY (idProduct) REFERENCES products(idProducts),
    FOREIGN KEY (idBuyBills) REFERENCES buy_bills(idBuyBills)
);

-- Bảng orders
CREATE TABLE orders (
    idOrders INT PRIMARY KEY,
    idBookings INT,
	 order_date DATE,
    total_amount DECIMAL(10, 2),
    status VARCHAR(50),        -- ví dụ: pending, paid, shipped, cancelled
    payment_method VARCHAR(50),-- ví dụ: cash, card, transfer
    note TEXT,
    FOREIGN KEY (idBookings) REFERENCES bookings(idBookings)
);

-- Bảng vouchers
CREATE TABLE vouchers (
    idVouchers INT PRIMARY KEY,
	idAdmin INT,
	idOrder INT,
    nameVouchers VARCHAR(100),
    start_value DECIMAL(10, 2),
    end_value DECIMAL(10, 2),
    is_active boolean,
    code VARCHAR(20),
	FOREIGN KEY (idOrder) REFERENCES orders(idOrders),

);

-- Bảng sales_bills
CREATE TABLE sales_bills (
    idSalesBills INT PRIMARY KEY,
    idOrders INT,
    amount INT,
    bookingDate DATE,
    state VARCHAR(50),
    FOREIGN KEY (idOrders) REFERENCES orders(idOrders)
);