package Model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private Integer id;

    private Double totalValue;
    private Customer customer;
    public List<Product> products;

    public ShoppingCart( Customer customer) {
        this.totalValue = 0d;
        this.products = new ArrayList<>();
        this.customer = customer;
    }

    public ShoppingCart(Integer id, Double totalValue, Customer customer) {
        this.id = id;
        this.totalValue = totalValue;
        this.customer = customer;
    }

    public ShoppingCart(Integer id, Double totalValue, Customer customer, List<Product> products) {
        this.id = id;
        this.totalValue = totalValue;
        this.customer = customer;
        this.products = products;
    }

    public void addToCart(Product product) {
        this.products.add(product);
        totalValue =totalValue+product.getPrice();
    }

    public void removeToCart(Product product) {
        this.products.remove(product);
        totalValue =totalValue-product.getPrice();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return this.products.size();
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

    public List<Product> getProducts() {
        return products;
    }
}
