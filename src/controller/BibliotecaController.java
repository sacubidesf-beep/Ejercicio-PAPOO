package controller;

import java.util.List;
import java.util.Scanner;
import model.BibliotecaModel;
import model.Libro;
import view.BibliotecaView;

public class BibliotecaController {
    private final BibliotecaModel model = new BibliotecaModel();
    private final BibliotecaView view;

    public BibliotecaController(Scanner scanner) {
        this.view = new BibliotecaView(scanner);
    }

    public void iniciar() {
        int opcion;

        do {
            view.mostrarMenu();
            opcion = view.leerOpcion();

            switch (opcion) {
                case 1:
                    agregarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    buscarLibroPorTitulo();
                    break;
                case 4:
                    eliminarLibro();
                    break;
                case 5:
                    consultarLibroPorId();
                    break;
                case 0:
                    view.mostrarMensaje("Volviendo al menú principal...");
                    break;
                default:
                    view.mostrarMensaje("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void agregarLibro() {
        Libro libro = view.leerLibro();
        model.agregarLibro(libro);
        view.mostrarMensaje("Libro agregado correctamente.");
    }

    private void listarLibros() {
        List<Libro> libros = model.listarLibros();
        view.mostrarLista(libros);
    }

    private void buscarLibroPorTitulo() {
        String titulo = view.leerTitulo();
        Libro libro = model.buscarLibroPorTitulo(titulo);
        view.mostrarLibro(libro);
    }

    private void eliminarLibro() {
        int id = view.leerId();
        boolean eliminado = model.eliminarLibro(id);
        if (eliminado) {
            view.mostrarMensaje("Libro eliminado correctamente.");
        } else {
            view.mostrarMensaje("No existe un libro con ese ID.");
        }
    }

    private void consultarLibroPorId() {
        int id = view.leerId();
        Libro libro = model.consultarLibroPorId(id);
        view.mostrarLibro(libro);
    }
}
