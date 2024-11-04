package Repository;

import Model.Customer;
import Model.Product;
import Model.ShoppingCart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDAO {
    private Connection connection;

    public ShoppingCartDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um carrinho de compras
    public ShoppingCart insert(ShoppingCart shoppingCart) {
        String sql = "INSERT INTO ShoppingCart (totalValue, customer_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, shoppingCart.getTotalValue());
            statement.setInt(2, shoppingCart.getCustomer().getId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    shoppingCart.setId(generatedKeys.getInt(1));
                }
            }

            insertProducts(shoppingCart);
        }
        catch (Exception e ){
            return null;
        }
        return shoppingCart;
    }

    // Método auxiliar para inserir produtos no relacionamento ShoppingCart_Product
    private void insertProducts(ShoppingCart shoppingCart) throws SQLException {
        String sql = "INSERT INTO ShoppingCart_Product (shoppingCart_id, product_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Product product : shoppingCart.getProducts()) {
                statement.setInt(1, shoppingCart.getId());
                statement.setInt(2, product.getId());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    // Método para atualizar um carrinho de compras
    public void update(ShoppingCart shoppingCart) throws SQLException {
        String sql = "UPDATE ShoppingCart SET totalValue = ?, customer_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, shoppingCart.getTotalValue());
            statement.setInt(2, shoppingCart.getCustomer().getId());
            statement.setInt(3, shoppingCart.getId());
            statement.executeUpdate();
        }

        // Atualiza a lista de produtos associada ao carrinho
        deleteProducts(shoppingCart.getId());
        insertProducts(shoppingCart);
    }

    // Método para remover todos os produtos de um carrinho
    private void deleteProducts(int shoppingCartId) throws SQLException {
        String sql = "DELETE FROM ShoppingCart_Product WHERE shoppingCart_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, shoppingCartId);
            statement.executeUpdate();
        }
    }


    public void delete(int id) throws SQLException {
        deleteProducts(id); // Remove o relacionamento com produtos primeiro
        String sql = "DELETE FROM ShoppingCart WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public ShoppingCart findById(int id) throws SQLException {
        String sql = "SELECT * FROM ShoppingCart WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Customer customer = findCustomerById(resultSet.getInt("customer_id"));
                    List<Product> products = findProductsByCartId(id);
                    return new ShoppingCart(
                            id,
                            resultSet.getDouble("totalValue"),
                            customer,
                            products
                    );
                }
            }
        }
        return null;
    }

    public List<ShoppingCart> findAll() throws SQLException {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        String sql = "SELECT * FROM ShoppingCart";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Customer customer = findCustomerById(resultSet.getInt("customer_id"));
                List<Product> products = findProductsByCartId(id);
                shoppingCarts.add(new ShoppingCart(
                        id,
                        resultSet.getDouble("totalValue"),
                        customer,
                        products
                ));
            }
        }
        return shoppingCarts;
    }


    private Customer findCustomerById(int customerId) {
        // Implementação para buscar o cliente (depende de como a classe CustomerDAO está implementada)
        return new CustomerDAO(connection).findById(customerId);
    }


    private List<Product> findProductsByCartId(int shoppingCartId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT product_id FROM ShoppingCart_Product WHERE shoppingCart_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, shoppingCartId);
            try (ResultSet resultSet = statement.executeQuery()) {
                ProductDAO productDAO = new ProductDAO(connection);
                while (resultSet.next()) {
                    products.add(productDAO.findById(resultSet.getInt("product_id")));
                }
            }
        }
        return products;
    }
}

