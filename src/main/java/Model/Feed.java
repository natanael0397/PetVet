package Model;

public class Feed extends Product {
    private Integer weight;

    public Feed(Integer id, String name, Double price, String description, Integer weight) {
        super(id, name, price, description);
        this.weight = weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
