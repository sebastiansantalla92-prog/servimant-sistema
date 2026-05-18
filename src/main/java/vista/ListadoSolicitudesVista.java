package vista;

import modelo.Solicitud;
import servicio.AsignacionServicio;
import servicio.SolicitudServicio;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ListadoSolicitudesVista {
    private final SolicitudServicio solicitudServicio = new SolicitudServicio();
    private final AsignacionServicio asignacionServicio = new AsignacionServicio();

    public void listarSolicitudes() {
        System.out.println("=== Listado de solicitudes ===");

        try {
            List<Solicitud> solicitudes = solicitudServicio.listarSolicitudes();

            if (solicitudes.isEmpty()) {
                System.out.println("No existen solicitudes registradas.");
                return;
            }

            for (Solicitud solicitud : solicitudes) {
                System.out.println(solicitud);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar solicitudes: " + e.getMessage());
        }
    }

    public void asignarTecnico(Scanner scanner) {
        System.out.println("=== Asignar técnico responsable ===");

        try {
            System.out.println("Solicitudes pendientes:");
            asignacionServicio.listarSolicitudesPendientes().forEach(System.out::println);

            System.out.print("ID solicitud: ");
            int idSolicitud = Integer.parseInt(scanner.nextLine());

            System.out.println("Técnicos disponibles:");
            asignacionServicio.listarTecnicosDisponibles().forEach(System.out::println);

            System.out.print("ID técnico: ");
            int idTecnico = Integer.parseInt(scanner.nextLine());

            System.out.print("Observación: ");
            String observacion = scanner.nextLine();

            asignacionServicio.asignarTecnico(idSolicitud, idTecnico, observacion);
            System.out.println("Asignación registrada correctamente. Estado actualizado a Asignada.");

        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("No se pudo realizar la asignación: " + e.getMessage());
        }
    }
}
