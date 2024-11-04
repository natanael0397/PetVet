package Model;

import Model.Enum.CategoryAnimalEnum;

public class Accessorie extends Product {
    private String accessorieType;

    public Accessorie(Integer id, String name, Double price, String description, String accessorieType) {
        super(id, name, price, description);
        this.accessorieType = accessorieType;
    }

    public Accessorie(String name, Double price, String description, CategoryAnimalEnum categoryAnimal, String accessorieType) {
        super(name, price, description, categoryAnimal);
        this.accessorieType = accessorieType;
    }


    public Double calculateTotalValue(Integer quantity) {
        return getPrice() * quantity;
    }

    public String getAccessorieType() {
        return accessorieType;
    }

    public void setAccessorieType(String accessorieType) {
        this.accessorieType = accessorieType;
    }
}
