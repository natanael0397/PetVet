package Model;

import Model.Enum.CategoryAnimalEnum;

//Model.Product tá ok
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private CategoryAnimalEnum categoryAnimal;

    public Product(Integer id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;

    }

    public Product(String name, Double price, String description, CategoryAnimalEnum categoryAnimal) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryAnimal = categoryAnimal;
    }

    public String describe(){
        return getDescription();
    }

    public Double calculateTotalValue(Integer quantity){
        return price * quantity;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", categoryAnimal=" + categoryAnimal +
                '}';
    }
}
