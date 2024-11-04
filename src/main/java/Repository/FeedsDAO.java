package Repository;

import Model.Feed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FeedsDAO {
    private Connection connection;

    public FeedsDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Feed feed) throws SQLException {
        String sql = "INSERT INTO Feed (id, weight) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, feed.getId());
            statement.setInt(2, feed.getWeight());
            statement.executeUpdate();
        }
    }

    public void update(Feed feed) throws SQLException {
        String sql = "UPDATE Feed SET weight = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, feed.getWeight());
            statement.setInt(2, feed.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Feed WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public Feed findById(int id) throws SQLException {
        String sql = "SELECT * FROM Feed WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Feed(
                            id,
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getString("description"),
                            resultSet.getInt("weight")
                    );
                }
            }
        }
        return null;
    }

    public boolean exists(int id) throws SQLException {
        String sql = "SELECT 1 FROM Feed WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}



