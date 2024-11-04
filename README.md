Description:
This Java project simulates a shopping cart system for a pet shop called PetVet. The application runs in the console and allows customers to perform operations such as viewing products,
adding and removing items from the cart, and finalizing purchases, with database integration to store customer and product data.

Features:

Customer Registration: Basic information such as name and email is added.

Product Listing: Displays all products available in the store, including accessories, food, and medications.

Cart Management: Add, remove, and view items in the shopping cart.

Finalize Purchase: Displays the total quantity and value of the purchase.

CRUD operations in the database for cart items.

The Menu Structure:
1 - View products

2 - Add product to cart

3 - Remove product from cart

4 - Finalize purchase

5 - Exit system


Main Workflow
Welcome and Registration: The customer enters their name and email, which are saved in the database.
Cart Operations: The customer can view, add, or remove products in the cart.
Purchase Finalization: Displays the total quantity of items and the final amount.
System Exit: Ends the program after interacting with the menu.

Technologies Used was :
Java for application logic
JDBC for database connectivity and data manipulation
MySQL (or another compatible relational database) to store customer, product, and cart information

The prerequisites of system are:
Java JDK 11 or higher,
MySQL Database (or any other JDBC-compatible database)
JDBC Library configured for database connection

How to Run
Clone the repository:
bash
Copiar código
git clone https://github.com/user/repository-name.git and 
Import the project into your preferred IDE,later 
set up the database connection in the properties file and
run the Main class to start the application.

The Project Structure: 
src/main: Contains the main application classes, including Customer, Product, ShoppingCart, ProductDAO, and CustomerDAO.
src/test: Unit tests for the project classes.
database: SQL scripts for creating and managing tables.
