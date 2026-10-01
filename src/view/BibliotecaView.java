package view;

import java.util.List;
import java.util.Scanner;

import model.Libro;

public class BibliotecaView {
    private final Scanner scanner;

    public BibliotecaView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        System.out.println("\n=== BIBLIOTECA ===");
        System.out.println("1. Agregar un libro");
        System.out.println("2. Listar todos los libros");
        System.out.println("3. Buscar un libro por título");
        System.out.println("4. Eliminar un libro");
        System.out.println("5. Consultar un libro por ID");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    public int leerOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada invalida. Intente de nuevo: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    public Libro leerLibro() {
        System.out.print("Ingrese el titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el genero: ");
        String genero = scanner.nextLine();
        System.out.print("Ingrese la fecha de publicacion: ");
        int anio = leerEntero();

        return new Libro(0, titulo, autor, genero, anio);
    }

    public String leerTitulo() {
        System.out.print("Ingrese el titulo: ");
        return scanner.nextLine();
    }

    public int leerId() {
        System.out.print("Ingrese el ID: ");
        return leerEntero();
    }

    public int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada invalida. Ingrese un numero: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    public void mostrarLibro(Libro libro) {
        if (libro == null) {
            System.out.println("No se encontro el libro.");
            return;
        }
        System.out.println("Libro encontrado:");
        System.out.println(libro);
    }

    public void mostrarLista(List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        System.out.println("Lista de libros:");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
