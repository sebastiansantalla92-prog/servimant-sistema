package vista;

import modelo.Estado;
import modelo.Intervencion;
import modelo.Solicitud;
import modelo.Tecnico;
import servicio.IntervencionServicio;

import java.sql.SQLException;
import java.util.Scanner;

public class IntervencionVista {

    private final IntervencionServicio intervencionServicio =
            new IntervencionServicio();

    public void registrarIntervencion(Scanner scanner) {

        System.out.println("=== Registrar intervención ===");

        try {
            System.out.print("ID solicitud: ");
            int idSolicitud =
                    Integer.parseInt(scanner.nextLine());

            System.out.print("ID técnico: ");
            int idTecnico =
                    Integer.parseInt(scanner.nextLine());

            System.out.print("Descripción de la intervención: ");
            String descripcion =
                    scanner.nextLine().trim();

            System.out.print(
                    "ID estado resultante "
                    + "(3 En proceso, 4 Resuelta, 5 Cerrada): "
            );

            int idEstado =
                    Integer.parseInt(scanner.nextLine());

            if (idSolicitud <= 0 || idTecnico <= 0) {
                throw new IllegalArgumentException(
                        "Los identificadores deben ser mayores a cero."
                );
            }

            if (descripcion.isEmpty()) {
                throw new IllegalArgumentException(
                        "La descripción no puede estar vacía."
                );
            }

            if (idEstado < 3 || idEstado > 5) {
                throw new IllegalArgumentException(
                        "El estado resultante debe ser 3, 4 o 5."
                );
            }

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

            intervencionServicio.registrarIntervencion(
                    intervencion
            );

            System.out.println(
                    "Intervención registrada correctamente. "
                    + "Estado de solicitud actualizado."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: los identificadores y el estado "
                    + "deben ser valores numéricos."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Datos inválidos: " + e.getMessage()
            );

        } catch (SQLException e) {

            System.out.println(
                    "No se pudo registrar la intervención "
                    + "por un error en la base de datos."
            );

            System.out.println(
                    "Detalle técnico: " + e.getMessage()
            );
        }
    }

    public void consultarHistorial(Scanner scanner) {

        System.out.println(
                "=== Historial de intervenciones ==="
        );

        try {
            System.out.print("ID solicitud: ");

            int idSolicitud =
                    Integer.parseInt(scanner.nextLine());

            if (idSolicitud <= 0) {
                throw new IllegalArgumentException(
                        "El identificador debe ser mayor a cero."
                );
            }

            var historial =
                    intervencionServicio.consultarHistorial(
                            idSolicitud
                    );

            if (historial.isEmpty()) {
                System.out.println(
                        "La solicitud no posee "
                        + "intervenciones registradas."
                );

                return;
            }

            historial.forEach(System.out::println);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: el ID de la solicitud "
                    + "debe ser un valor numérico."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Datos inválidos: " + e.getMessage()
            );

        } catch (SQLException e) {

            System.out.println(
                    "No se pudo consultar el historial "
                    + "por un error en la base de datos."
            );

            System.out.println(
                    "Detalle técnico: " + e.getMessage()
            );
        }
    }
}