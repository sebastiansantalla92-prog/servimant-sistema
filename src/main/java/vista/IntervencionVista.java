package vista;

import modelo.*;
import servicio.IntervencionServicio;

import java.sql.SQLException;
import java.util.Scanner;

public class IntervencionVista {
    private final IntervencionServicio intervencionServicio = new IntervencionServicio();

    public void registrarIntervencion(Scanner scanner) {
        System.out.println("=== Registrar intervención ===");

        System.out.print("ID solicitud: ");
        int idSolicitud = Integer.parseInt(scanner.nextLine());

        System.out.print("ID técnico: ");
        int idTecnico = Integer.parseInt(scanner.nextLine());

        System.out.print("Descripción de la intervención: ");
        String descripcion = scanner.nextLine();

        System.out.print("ID estado resultante (3 En proceso, 4 Resuelta, 5 Cerrada): ");
        int idEstado = Integer.parseInt(scanner.nextLine());

        Solicitud solicitud = new Solicitud();
        solicitud.setIdSolicitud(idSolicitud);

        Tecnico tecnico = new Tecnico();
        tecnico.setIdTecnico(idTecnico);

        Estado estado = new Estado();
        estado.setIdEstado(idEstado);

        Intervencion intervencion = new Intervencion();
        intervencion.setSolicitud(solicitud);
        intervencion.setTecnico(tecnico);
        intervencion.setDescripcion(descripcion);
        intervencion.setEstadoResultante(estado);

        try {
            intervencionServicio.registrarIntervencion(intervencion);
            System.out.println("Intervención registrada correctamente. Estado de solicitud actualizado.");
        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("No se pudo registrar la intervención: " + e.getMessage());
        }
    }

    public void consultarHistorial(Scanner scanner) {
        System.out.println("=== Historial de intervenciones ===");

        System.out.print("ID solicitud: ");
        int idSolicitud = Integer.parseInt(scanner.nextLine());

        try {
            var historial = intervencionServicio.consultarHistorial(idSolicitud);

            if (historial.isEmpty()) {
                System.out.println("La solicitud no posee intervenciones registradas.");
                return;
            }

            historial.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println("Error al consultar historial: " + e.getMessage());
        }
    }
}
