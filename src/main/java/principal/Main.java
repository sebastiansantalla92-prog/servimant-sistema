package principal;

import modelo.Usuario;
import vista.IntervencionVista;
import vista.ListadoSolicitudesVista;
import vista.LoginVista;
import vista.SolicitudVista;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LoginVista loginVista = new LoginVista();
        SolicitudVista solicitudVista = new SolicitudVista();
        ListadoSolicitudesVista listadoVista = new ListadoSolicitudesVista();
        IntervencionVista intervencionVista = new IntervencionVista();

        Usuario usuarioAutenticado = loginVista.iniciarSesion(scanner);

        if (usuarioAutenticado == null) {
            scanner.close();
            return;
        }

        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> solicitudVista.registrarSolicitud(scanner, usuarioAutenticado);
                case 2 -> listadoVista.listarSolicitudes();
                case 3 -> listadoVista.asignarTecnico(scanner);
                case 4 -> intervencionVista.registrarIntervencion(scanner);
                case 5 -> intervencionVista.consultarHistorial(scanner);
                case 0 -> System.out.println("Finalizando sistema ServiMant...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=== Sistema ServiMant ===");
        System.out.println("1. Registrar solicitud");
        System.out.println("2. Listar solicitudes");
        System.out.println("3. Asignar técnico responsable");
        System.out.println("4. Registrar intervención");
        System.out.println("5. Consultar historial de intervenciones");
        System.out.println("0. Salir");
    }
}
