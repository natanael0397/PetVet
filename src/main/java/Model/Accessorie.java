package Model;

public class Accessorie extends Product {
    //ToDo insert "final"?
    private String accessorieType;

    public Accessorie(Integer id, String name, Double price, String description, Integer quantity, String accessorieType) {
        super(id, name, price, description, quantity);
        this.accessorieType = accessorieType;
    }

    //ToDo insert "final"?
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
