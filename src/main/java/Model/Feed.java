package Model;

public class Feed extends Product {
    private Integer weight;

    public Feed(Integer id, String name, Double price, String description, Integer quantity, Integer weight) {
        super(id, name, price, description, quantity);
        this.weight = weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
