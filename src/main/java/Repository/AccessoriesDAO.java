package Repository;

import Model.Accessorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessoriesDAO {
    private Connection connection;

    public AccessoriesDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Accessorie accessorie) throws SQLException {
        String sql = "INSERT INTO Accessorie (id, accessorieType) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accessorie.getId());
            statement.setString(2, accessorie.getAccessorieType());
            statement.executeUpdate();
        }
    }

    public void update(Accessorie accessorie) throws SQLException {
        String sql = "UPDATE Accessorie SET accessorieType = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accessorie.getAccessorieType());
            statement.setInt(2, accessorie.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Accessorie WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public Accessorie findById(int id) throws SQLException {
        String sql = "SELECT * FROM Accessorie WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Accessorie(
                            id,
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getString("description"),
                            resultSet.getString("accessorieType")
                    );
                }
            }
        }
        return null;
    }

    public boolean exists(int id) throws SQLException {
        String sql = "SELECT 1 FROM Accessorie WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}