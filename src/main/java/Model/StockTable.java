package Model;

public class StockTable {
    private Integer id;
    private Integer quantity;
    private Product product;

    public StockTable(Integer id, Integer quantity,Product product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public void addProduct() {
        this.quantity = quantity + 1;
    }

    public void remProduct() {
        this.quantity = quantity - 1;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
