package model;

import database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaModel {
    public BibliotecaModel() {
        DatabaseManager.initialize();
    }

    public void agregarLibro(Libro libro) {
        String sql = "INSERT INTO libros (titulo, autor, genero, anio_publicacion) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getGenero());
            statement.setInt(4, libro.getAnioPublicacion());
            statement.executeUpdate();
              try (PreparedStatement idStatement = connection.prepareStatement("SELECT last_insert_rowid()");
                  ResultSet result = idStatement.executeQuery()) {
                if (result.next()) {
                    libro.setId(result.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo guardar el libro en SQLite.", e);
        }
    }

    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT id, titulo, autor, genero, anio_publicacion FROM libros ORDER BY id";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                libros.add(leerLibro(result));
            }
            return libros;
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudieron consultar los libros en SQLite.", e);
        }
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        String sql = "SELECT id, titulo, autor, genero, anio_publicacion FROM libros "
                + "WHERE titulo = ? COLLATE NOCASE LIMIT 1";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, titulo);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? leerLibro(result) : null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo buscar el libro en SQLite.", e);
        }
    }

    public boolean eliminarLibro(int id) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM libros WHERE id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo eliminar el libro de SQLite.", e);
        }
    }

    public Libro consultarLibroPorId(int id) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id, titulo, autor, genero, anio_publicacion FROM libros WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? leerLibro(result) : null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo consultar el libro en SQLite.", e);
        }
    }

    private Libro leerLibro(ResultSet result) throws SQLException {
        return new Libro(result.getInt("id"), result.getString("titulo"), result.getString("autor"),
                result.getString("genero"), result.getInt("anio_publicacion"));
    }
}
