package model;

import database.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoClubModel {
    public VideoClubModel() {
        DatabaseManager.initialize();
    }

    public void registrarPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO peliculas (titulo, genero, director, duracion_minutos, anio_estreno) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pelicula.getTitulo());
            statement.setString(2, pelicula.getGenero());
            statement.setString(3, pelicula.getDirector());
            statement.setInt(4, pelicula.getDuracionMinutos());
            statement.setInt(5, pelicula.getAnioEstreno());
            statement.executeUpdate();
              try (PreparedStatement idStatement = connection.prepareStatement("SELECT last_insert_rowid()");
                  ResultSet result = idStatement.executeQuery()) {
                if (result.next()) {
                    pelicula.setId(result.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo guardar la película en SQLite.", e);
        }
    }

    public List<Pelicula> listarPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT id, titulo, genero, director, duracion_minutos, anio_estreno "
                + "FROM peliculas ORDER BY id";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                peliculas.add(leerPelicula(result));
            }
            return peliculas;
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudieron consultar las películas en SQLite.", e);
        }
    }

    public Pelicula buscarPeliculaPorTitulo(String titulo) {
        String sql = "SELECT id, titulo, genero, director, duracion_minutos, anio_estreno "
                + "FROM peliculas WHERE titulo = ? COLLATE NOCASE LIMIT 1";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, titulo);
            try (ResultSet result = statement.executeQuery()) {
                return result.next() ? leerPelicula(result) : null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo buscar la película en SQLite.", e);
        }
    }

    public List<Pelicula> buscarPeliculasPorGenero(String genero) {
        List<Pelicula> resultado = new ArrayList<>();
        String sql = "SELECT id, titulo, genero, director, duracion_minutos, anio_estreno "
                + "FROM peliculas WHERE genero = ? COLLATE NOCASE ORDER BY id";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, genero);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    resultado.add(leerPelicula(result));
                }
            }
            return resultado;
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudieron buscar películas por género en SQLite.", e);
        }
    }

    public boolean actualizarPelicula(int id, Pelicula nuevaPelicula) {
        String sql = "UPDATE peliculas SET titulo = ?, genero = ?, director = ?, "
                + "duracion_minutos = ?, anio_estreno = ? WHERE id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nuevaPelicula.getTitulo());
            statement.setString(2, nuevaPelicula.getGenero());
            statement.setString(3, nuevaPelicula.getDirector());
            statement.setInt(4, nuevaPelicula.getDuracionMinutos());
            statement.setInt(5, nuevaPelicula.getAnioEstreno());
            statement.setInt(6, id);
            boolean actualizado = statement.executeUpdate() > 0;
            if (actualizado) {
                nuevaPelicula.setId(id);
            }
            return actualizado;
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo actualizar la película en SQLite.", e);
        }
    }

    public boolean eliminarPelicula(int id) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM peliculas WHERE id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo eliminar la película de SQLite.", e);
        }
    }

    private Pelicula leerPelicula(ResultSet result) throws SQLException {
        return new Pelicula(result.getInt("id"), result.getString("titulo"), result.getString("genero"),
                result.getString("director"), result.getInt("duracion_minutos"), result.getInt("anio_estreno"));
    }
}
