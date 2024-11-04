package Repository;

import Model.Accessorie;
import Model.Feed;
import Model.Medication;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    // Inserir um produto e delegar para o DAO específico se for uma subclasse
    public void insert(Product product) {
        String sql = "INSERT INTO Product (name, price, description) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
            }

            if (product instanceof Medication) {
                new MedicationsDAO(connection).insert((Medication) product);
            } else if (product instanceof Feed) {
                new FeedsDAO(connection).insert((Feed) product);
            } else if (product instanceof Accessorie) {
                new AccessoriesDAO(connection).insert((Accessorie) product);
            }
        }catch (Exception e){
            return;
        }
    }

    // Atualizar um produto e suas propriedades específicas
    public void update(Product product) throws SQLException {
        String sql = "UPDATE Product SET name = ?, price = ?, description = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getId());
            statement.executeUpdate();
        }

        if (product instanceof Medication) {
            new MedicationsDAO(connection).update((Medication) product);
        } else if (product instanceof Feed) {
            new FeedsDAO(connection).update((Feed) product);
        } else if (product instanceof Accessorie) {
            new AccessoriesDAO(connection).update((Accessorie) product);
        }
    }

    // Deletar um produto e a entrada na tabela específica
    public void delete(int productId) throws SQLException {
        new MedicationsDAO(connection).delete(productId);
        new FeedsDAO(connection).delete(productId);
        new AccessoriesDAO(connection).delete(productId);

        String sql = "DELETE FROM Product WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        }
    }

    // Consultar um produto por ID
    public Product findById(int id){
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getString("description")
                    );

                    if (new MedicationsDAO(connection).exists(id)) {
                        return new MedicationsDAO(connection).findById(id);
                    } else if (new FeedsDAO(connection).exists(id)) {
                        return new FeedsDAO(connection).findById(id);
                    } else if (new AccessoriesDAO(connection).exists(id)) {
                        return new AccessoriesDAO(connection).findById(id);
                    }
                    return product;
                }
            }

        }
        catch (Exception e){
            return null;
        }
        return null;
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                if (new MedicationsDAO(connection).exists(id)) {
                    products.add(new MedicationsDAO(connection).findById(id));
                } else if (new FeedsDAO(connection).exists(id)) {
                    products.add(new FeedsDAO(connection).findById(id));
                } else if (new AccessoriesDAO(connection).exists(id)) {
                    products.add(new AccessoriesDAO(connection).findById(id));
                } else {
                    products.add(new Product(
                            id,
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getString("description")
                    ));
                }
            }
        }catch (Exception e){
            return new ArrayList<>();
        }
        return products;
    }
}