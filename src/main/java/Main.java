import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);


        System.out.println("/////Be Welcome to PetVet/////");
        Customer client = new Customer(1, "Alex", "ala@outlook.com");
        ShoppingCart shoppingCart = new ShoppingCart(1, client);

        Feed feedCat = new Feed(1, "Pedigree", 12.50, "Comida para gatos/1kg", 12);
        StockTable stockTableCat = new StockTable(1, 20, feedCat);

        Medication medicationMeloxican = new Medication(1, "Meloxican", 15.50, "Antiflamatório", 0.500);
        StockTable stockTableMeloxican = new StockTable(3,15,medicationMeloxican);



        shoppingCart.addToCart(medicationMeloxican);
        stockTableMeloxican.remProduct();
        shoppingCart.addToCart(feedCat);
        stockTableCat.remProduct();
        System.out.println("O valor total do produto foi de : "+ shoppingCart.getTotalValue());
        System.out.println("Quantidade total do produto : " + shoppingCart.getQuantity());




    }
}
