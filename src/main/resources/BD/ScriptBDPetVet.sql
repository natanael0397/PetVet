CREATE DATABASE PetVet;
USE PetVet;


CREATE TABLE Customer(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (50) NOT NULL,
email VARCHAR (30) UNIQUE NOT NULL
);

CREATE TABLE Product(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR (50) NOT NULL,
price INT NOT NULL,
description VARCHAR (50) NOT NULL,
category_Animal ENUM ('GENERAL','DOG','CAT') NOT NULL
);

CREATE TABLE ShoppingCart(
id INT PRIMARY KEY AUTO_INCREMENT,

customer_id INT NOT NULL,
totalValue DOUBLE NOT NULL,
FOREIGN KEY (customer_id) REFERENCES Customer(id) ON DELETE CASCADE
);

CREATE TABLE ShoppingCart_Product(
shoppingCart_id INT NOT NULL,
product_id INT NOT NULL,
quantity INT NOT NULL,
PRIMARY KEY (shoppingCart_id,product_id),
FOREIGN KEY  (shoppingCart_id) REFERENCES ShoppingCart(id),
FOREIGN KEY(product_id) REFERENCES Product(id)
);

CREATE TABLE StockTable(
id INT PRIMARY KEY AUTO_INCREMENT,
product_id INT NOT NULL,
quantity INT NOT NULL,
FOREIGN KEY (product_id) REFERENCES Product(id) ON DELETE CASCADE
);

CREATE TABLE Accessorie(
id INT PRIMARY KEY AUTO_INCREMENT,
acessory_Type ENUM ('GENERAL','DOG','CAT') NOT NULL,
 product_id INT NOT NULL,
 FOREIGN KEY(product_id) REFERENCES Product(id)
 );

 CREATE TABLE Feed(
 id INT PRIMARY KEY AUTO_INCREMENT,
 weigh INT NOT NULL,
 product_id INT NOT NULL,
 FOREIGN KEY(product_id) REFERENCES Product(id)
 );

 CREATE TABLE Medication(
 id INT PRIMARY KEY AUTO_INCREMENT,
 milligrams DOUBLE  NOT NULL,
 product_id INT NOT NULL,
 FOREIGN KEY(product_id) REFERENCES Product(id)
 );

