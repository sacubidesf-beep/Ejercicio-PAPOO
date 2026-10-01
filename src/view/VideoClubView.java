package view;

import java.util.List;
import java.util.Scanner;

import model.Pelicula;

public class VideoClubView {
    private final Scanner scanner;

    public VideoClubView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        System.out.println("\n=== VIDEOTECA ===");
        System.out.println("1. Registrar pelicula");
        System.out.println("2. Listar peliculas");
        System.out.println("3. Buscar por titulo");
        System.out.println("4. Buscar por genero");
        System.out.println("5. Actualizar informacion de una pelicula");
        System.out.println("6. Eliminar pelicula");
        System.out.println("0. Volver al menu principal");
        System.out.print("Seleccione una opcion: ");
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

    public Pelicula leerPelicula() {
        System.out.print("Ingrese el titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el genero: ");
        String genero = scanner.nextLine();
        System.out.print("Ingrese el director: ");
        String director = scanner.nextLine();
        System.out.print("Ingrese la duracion en minutos: ");
        int duracion = leerEntero();
        System.out.print("Ingrese la fecha de estreno: ");
        int anio = leerEntero();

        return new Pelicula(0, titulo, genero, director, duracion, anio);
    }

    public int leerId() {
        System.out.print("Ingrese el ID: ");
        return leerEntero();
    }

    public String leerTitulo() {
        System.out.print("Ingrese el título: ");
        return scanner.nextLine();
    }

    public String leerGenero() {
        System.out.print("Ingrese el género: ");
        return scanner.nextLine();
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

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarPelicula(Pelicula pelicula) {
        if (pelicula == null) {
            System.out.println("No se encontro ninguna pelicula.");
            return;
        }
        System.out.println("Pelicula encontrada:");
        System.out.println(pelicula);
    }

    public void mostrarLista(List<Pelicula> peliculas) {
        if (peliculas.isEmpty()) {
            System.out.println("No hay peliculas registradas.");
            return;
        }
        System.out.println("Lista de peliculas:");
        for (Pelicula pelicula : peliculas) {
            System.out.println(pelicula);
        }
    }
}
