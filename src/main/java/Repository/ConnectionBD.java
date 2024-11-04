package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class ConnectionBD {
    private final String url = "jdbc:mysql://localhost:3306/PetVet";
    private final String user = "natan";
    private final String password = "123456";

    Connection connection;

    public Connection connection() {
        try {
            connection = DriverManager.getConnection(url, user, password);

        } catch (
                SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
        return connection;
    }

    public boolean closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
