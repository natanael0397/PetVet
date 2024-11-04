package Repository;

import Model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    // Inserir um novo cliente
    public Customer insert(Customer customer) {
        String sql = "INSERT INTO Customer (name, email) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1));
                }
            }

        }catch (Exception e){
            return null;
        }
        return customer;
    }


    public void update(Customer customer) throws SQLException {
        String sql = "UPDATE Customer SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setInt(3, customer.getId());
            statement.executeUpdate();
        }
    }


    public void delete(int customerId) throws SQLException {
        String sql = "DELETE FROM Customer WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
        }
    }


    public Customer findById(int id)  {
        String sql = "SELECT * FROM Customer WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Customer(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email")
                    );
                }
            }
        }catch (Exception e ){
            return null;
        }
        return null;
    }


    public List<Customer> findAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
                customers.add(customer);
            }
        }
        return customers;
    }
}


