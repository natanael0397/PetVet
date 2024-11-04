import Model.*;
import Model.Enum.CategoryAnimalEnum;
import Repository.ConnectionBD;
import Repository.CustomerDAO;
import Repository.ProductDAO;
import Repository.ShoppingCartDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        ConnectionBD connection = new ConnectionBD();
        CustomerDAO customerDAO = new CustomerDAO(connection.connection());
        ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO(connection.connection());
        ProductDAO productDAO = new ProductDAO(connection.connection());
        addProduct(productDAO);


        System.out.println("/////Be Welcome to PetVet/////");
        System.out.println("Please insert your Name and Email: ");
        System.out.println("Name : ");
        String name = sc.nextLine();
        System.out.println("Email :");
        String email = sc.next();
        Customer client = new Customer(name, email);
        client = customerDAO.insert(client);
        ShoppingCart shoppingCart = new ShoppingCart(client);

        int control = 0;
        while (control != 5) {
            System.out.println("Enter 1 to see product : ");
            System.out.println("Enter 2 to add the product in the cart : ");
            System.out.println("Enter 3 to remove the product in the cart : ");
            System.out.println("Enter 4 to finish the buy : ");
            System.out.println("Enter 5 to finish the system :");
            int usercommand = sc.nextInt();
            switch (usercommand) {
                case 1:
                    productDAO.findAll().forEach(System.out::println);
                    break;

                case 2:
                    addProduct(shoppingCart, productDAO, sc);
                    break;

                case 3:
                    remProduct(shoppingCart, productDAO, sc);
                    break;

                case 4:
                    showBuy(shoppingCart);
                    break;

                case 5:
                    control = 5;

            }
        }


    }

    private static void showBuy(ShoppingCart shoppingCart) {
        System.out.println("You bought " + shoppingCart.getQuantity());
        System.out.println("Your Total Value : " + shoppingCart.getTotalValue());
    }

    private static void remProduct(ShoppingCart shoppingCart, ProductDAO productDAO, Scanner scanner) {
        System.out.println("Enter the id of the product : ");
        shoppingCart.removeToCart(productDAO.findById(scanner.nextInt()));
    }

    private static void addProduct(ShoppingCart shoppingCart, ProductDAO productDAO, Scanner scanner) {
        System.out.println("Enter with id of the product : ");
        shoppingCart.addToCart(productDAO.findById(scanner.nextInt()));

    }

    private  static void addProduct(ProductDAO productDAO){
        productDAO.insert(new Accessorie("Toothbrush",15.00,"Toothbrush for pets", CategoryAnimalEnum.DOG,"Hygiene"));
        productDAO.insert(new Accessorie("Collar",40.00,"Adjustable collar for pets", CategoryAnimalEnum.DOG,"Accessorie"));
        productDAO.insert(new Feed("Pedigree Food Cat",35.00,"Food for cats",CategoryAnimalEnum.CAT,15));
        productDAO.insert(new Feed("Pedigree Food Dog",25.00,"Food for dogs",CategoryAnimalEnum.DOG,30));
        productDAO.insert(new Medication("Meloxican",65.00,"Antiflamatory for animals",CategoryAnimalEnum.CAT,0.500));
        productDAO.insert(new Medication("Mellanil",95.00,"Antiflamatory for animals",CategoryAnimalEnum.DOG,1.500));

    }
}
