package vista;

import modelo.Solicitud;
import modelo.Tecnico;
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
        List<Solicitud> solicitudesPendientes =
                asignacionServicio.listarSolicitudesPendientes();

        if (solicitudesPendientes.isEmpty()) {
            System.out.println(
                    "No existen solicitudes pendientes para asignar."
            );
            return;
        }

        System.out.println("Solicitudes pendientes:");

        solicitudesPendientes.forEach(System.out::println);

        System.out.print("ID solicitud: ");
        int idSolicitud =
                Integer.parseInt(scanner.nextLine());

        List<Tecnico> tecnicosDisponibles =
                asignacionServicio.listarTecnicosDisponibles();

        if (tecnicosDisponibles.isEmpty()) {
            System.out.println(
                    "No existen técnicos disponibles."
            );
            return;
        }

        System.out.println("Técnicos disponibles:");

        tecnicosDisponibles.forEach(System.out::println);

        System.out.print("ID técnico: ");
        int idTecnico =
                Integer.parseInt(scanner.nextLine());

        System.out.print("Observación: ");
        String observacion =
                scanner.nextLine().trim();

        asignacionServicio.asignarTecnico(
                idSolicitud,
                idTecnico,
                observacion
        );

        System.out.println(
                "Asignación registrada correctamente. "
                + "Estado actualizado a Asignada."
        );

    } catch (NumberFormatException e) {

        System.out.println(
                "Error: los identificadores de la solicitud "
                + "y del técnico deben ser valores numéricos."
        );

    } catch (IllegalArgumentException e) {

        System.out.println(
                "Datos inválidos: " + e.getMessage()
        );

    } catch (SQLException e) {

        System.out.println(
                "No se pudo realizar la asignación "
                + "por un error en la base de datos."
        );

        System.out.println(
                "Detalle técnico: " + e.getMessage()
        );
    }
    }
}
