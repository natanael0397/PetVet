package Model;

public class StockTable {
    private Integer id;
    private String name;
    private Integer quantity;
    private Product product;

    public StockTable(Integer id, String name, String category, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public void addProduct() {
        this.quantity = quantity+1;
    }

    public void remProduct() {
        this.quantity = quantity - 1;
    }

    public void updateStock(Integer newQuantity) {
        this.quantity = newQuantity;
    }

    /*
    public boolean isAvailable(){

    }
    */

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
