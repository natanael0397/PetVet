package Repository;

import Model.Medication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicationsDAO {
    private Connection connection;

    public MedicationsDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Medication medication) throws SQLException {
        String sql = "INSERT INTO Medication (id, milligrams) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, medication.getId());
            statement.setDouble(2, medication.getMilligrams());
            statement.executeUpdate();
        }
    }

    public void update(Medication medication) throws SQLException {
        String sql = "UPDATE Medication SET milligrams = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, medication.getMilligrams());
            statement.setInt(2, medication.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Medication WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public Medication findById(int id) throws SQLException {
        String sql = "SELECT * FROM Medication WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Medication(
                            id,
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getString("description"),
                            resultSet.getDouble("milligrams")
                    );
                }
            }
        }
        return null;
    }

    public boolean exists(int id) throws SQLException {
        String sql = "SELECT 1 FROM Medication WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}

