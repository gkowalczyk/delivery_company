DROP TABLE IF EXISTS Customers;

CREATE TABLE Customers
(
    id              LONG AUTO_INCREMENT PRIMARY KEY,
    sender_address   VARCHAR(250),
    recipient_address VARCHAR(250),
    package_type    VARCHAR(250),
    package_size    VARCHAR(250),
    delivery_date   DATE
);

DROP TABLE IF EXISTS Orders;

CREATE TABLE Orders
(
    id    LONG AUTO_INCREMENT PRIMARY KEY

);


DROP TABLE IF EXISTS Couriers;

CREATE TABLE Couriers
(
    id    LONG AUTO_INCREMENT PRIMARY KEY

);


DROP TABLE IF EXISTS Delivery;

CREATE TABLE Delivery
(
    id    LONG AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (Id) REFERENCES Orders(ID)
);

DROP TABLE IF EXISTS Tracking;

CREATE TABLE Tracking
(
    id    LONG AUTO_INCREMENT PRIMARY KEY

);
