package Model;

import Model.Enum.CategoryAnimalEnum;

public class Feed extends Product {
    private Integer weight;

    public Feed(Integer id, String name, Double price, String description, Integer weight) {
        super(id, name, price, description);
        this.weight = weight;
    }

    public Feed(String name, Double price, String description, CategoryAnimalEnum categoryAnimal, Integer weight) {
        super(name, price, description, categoryAnimal);
        this.weight = weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }


    public Integer getWeight() {
        return weight;
    }
}
