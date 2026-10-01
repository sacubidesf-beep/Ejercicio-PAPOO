import java.util.Scanner;

import controller.BibliotecaController;
import controller.VideoClubController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliotecaController bibliotecaController = new BibliotecaController(scanner);
        VideoClubController videoClubController = new VideoClubController(scanner);

        int opcion;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Biblioteca");
            System.out.println("2. Videoteca");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Entrada invalida. Intente de nuevo: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    bibliotecaController.iniciar();
                    break;
                case 2:
                    videoClubController.iniciar();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
