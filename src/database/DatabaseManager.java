package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseManager {
    private static final String URL = "jdbc:sqlite:papoo.db";

    private DatabaseManager() {
    }

    public static void initialize() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS libros ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "titulo TEXT NOT NULL, autor TEXT NOT NULL, genero TEXT NOT NULL, "
                    + "anio_publicacion INTEGER NOT NULL)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS peliculas ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "titulo TEXT NOT NULL, genero TEXT NOT NULL, director TEXT NOT NULL, "
                    + "duracion_minutos INTEGER NOT NULL, anio_estreno INTEGER NOT NULL)");
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo inicializar la base de datos SQLite.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}