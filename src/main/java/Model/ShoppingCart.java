package Model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private Integer id;

    private String name;

    private Double quantity;

    private Double totalValue;
    private Customer customer;
    public List<Product> products;

    public ShoppingCart(String name, Integer id, Double quantity, Double totalValue, Customer customer) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.totalValue = totalValue;
        this.products = new ArrayList<>();
        this.customer = customer;
    }


    public void addToCart(Product product) {
        this.products.add(product);
    }

    public void removeToCart(Product product) {
        this.products.remove(product);
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
