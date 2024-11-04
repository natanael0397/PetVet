package Repository;

import Model.Customer;
import Model.Product;
import Model.StockTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockTableDAO {
    private Connection connection;

    public StockTableDAO(Connection connection) {
        this.connection = connection;
    }


    public void insert(StockTable stockTable) throws SQLException {
        String sql = "INSERT INTO StockTable (quantity, product_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, stockTable.getQuantity());
            statement.setInt(2, stockTable.getProduct().getId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    stockTable.setId(generatedKeys.getInt(1));
                }
            }
        }
    }


    public void update(StockTable stockTable) throws SQLException {
        String sql = "UPDATE StockTable SET quantity = ?, product_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, stockTable.getQuantity());
            statement.setInt(2, stockTable.getProduct().getId());
            statement.setInt(3, stockTable.getId());
            statement.executeUpdate();
        }
    }


    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM StockTable WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public StockTable findById(int id) throws SQLException {
        String sql = "SELECT * FROM StockTable WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = findProductById(resultSet.getInt("product_id"));
                    return new StockTable(
                            id,
                            resultSet.getInt("quantity"),
                            product
                    );
                }
            }
        }
        return null;
    }

    public List<StockTable> findAll() throws SQLException {
        List<StockTable> stockTables = new ArrayList<>();
        String sql = "SELECT * FROM StockTable";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Product product = findProductById(resultSet.getInt("product_id"));
                stockTables.add(new StockTable(
                        resultSet.getInt("id"),
                        resultSet.getInt("quantity"),
                        product
                ));
            }
        }
        return stockTables;
    }

    private Product findProductById(int productId) throws SQLException {
        // Implementação para buscar o produto (depende de como a classe ProductDAO está implementada)
        return new ProductDAO(connection).findById(productId);
    }
}

