package controller;

import java.util.List;
import java.util.Scanner;
import model.Pelicula;
import model.VideoClubModel;
import view.VideoClubView;

public class VideoClubController {
    private final VideoClubModel model = new VideoClubModel();
    private final VideoClubView view;

    public VideoClubController(Scanner scanner) {
        this.view = new VideoClubView(scanner);
    }

    public void iniciar() {
        int opcion;

        do {
            view.mostrarMenu();
            opcion = view.leerOpcion();

            switch (opcion) {
                case 1:
                    registrarPelicula();
                    break;
                case 2:
                    listarPeliculas();
                    break;
                case 3:
                    buscarPeliculaPorTitulo();
                    break;
                case 4:
                    buscarPeliculasPorGenero();
                    break;
                case 5:
                    actualizarPelicula();
                    break;
                case 6:
                    eliminarPelicula();
                    break;
                case 0:
                    view.mostrarMensaje("Volviendo al menú principal...");
                    break;
                default:
                    view.mostrarMensaje("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrarPelicula() {
        Pelicula pelicula = view.leerPelicula();
        model.registrarPelicula(pelicula);
        view.mostrarMensaje("Película registrada correctamente.");
    }

    private void listarPeliculas() {
        List<Pelicula> peliculas = model.listarPeliculas();
        view.mostrarLista(peliculas);
    }

    private void buscarPeliculaPorTitulo() {
        String titulo = view.leerTitulo();
        Pelicula pelicula = model.buscarPeliculaPorTitulo(titulo);
        view.mostrarPelicula(pelicula);
    }

    private void buscarPeliculasPorGenero() {
        String genero = view.leerGenero();
        List<Pelicula> resultado = model.buscarPeliculasPorGenero(genero);
        view.mostrarLista(resultado);
    }

    private void actualizarPelicula() {
        int id = view.leerId();
        Pelicula nuevaPelicula = view.leerPelicula();
        boolean actualizado = model.actualizarPelicula(id, nuevaPelicula);
        if (actualizado) {
            view.mostrarMensaje("Película actualizada correctamente.");
        } else {
            view.mostrarMensaje("No existe una película con ese ID.");
        }
    }

    private void eliminarPelicula() {
        int id = view.leerId();
        boolean eliminado = model.eliminarPelicula(id);
        if (eliminado) {
            view.mostrarMensaje("Película eliminada correctamente.");
        } else {
            view.mostrarMensaje("No existe una película con ese ID.");
        }
    }
}
