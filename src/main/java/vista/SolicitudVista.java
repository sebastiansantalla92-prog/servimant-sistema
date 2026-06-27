package vista;

import modelo.Categoria;
import modelo.Estado;
import modelo.Prioridad;
import modelo.Sector;
import modelo.Solicitud;
import modelo.Usuario;
import servicio.SolicitudServicio;

import java.sql.SQLException;
import java.util.Scanner;

public class SolicitudVista {

    private final SolicitudServicio solicitudServicio =
            new SolicitudServicio();

    public void registrarSolicitud(
            Scanner scanner,
            Usuario usuario
    ) {

        System.out.println("=== Registrar solicitud ===");

        try {
            System.out.print("Descripción del problema: ");
            String descripcion = scanner.nextLine().trim();

            if (descripcion.isEmpty()) {
                throw new IllegalArgumentException(
                        "La descripción no puede estar vacía."
                );
            }

            System.out.print("ID categoría: ");
            int idCategoria =
                    Integer.parseInt(scanner.nextLine());

            System.out.print("ID prioridad: ");
            int idPrioridad =
                    Integer.parseInt(scanner.nextLine());

            System.out.print("ID sector: ");
            int idSector =
                    Integer.parseInt(scanner.nextLine());

            if (idCategoria <= 0
                    || idPrioridad <= 0
                    || idSector <= 0) {

                throw new IllegalArgumentException(
                        "Los identificadores deben ser mayores a cero."
                );
            }

            Solicitud solicitud = new Solicitud();

            solicitud.setDescripcion(descripcion);
            solicitud.setUsuario(usuario);

            Categoria categoria = new Categoria();
            categoria.setIdCategoria(idCategoria);
            solicitud.setCategoria(categoria);

            Prioridad prioridad = new Prioridad();
            prioridad.setIdPrioridad(idPrioridad);
            solicitud.setPrioridad(prioridad);

            Estado estadoPendiente = new Estado();
            estadoPendiente.setIdEstado(1);
            solicitud.setEstado(estadoPendiente);

            Sector sector = new Sector();
            sector.setIdSector(idSector);
            solicitud.setSector(sector);

            solicitudServicio.registrarSolicitud(solicitud);

            System.out.println(
                    "Solicitud registrada correctamente "
                    + "con estado Pendiente."
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: debe ingresar valores numéricos "
                    + "para categoría, prioridad y sector."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Datos inválidos: " + e.getMessage()
            );

        } catch (SQLException e) {

            System.out.println(
                    "No se pudo registrar la solicitud "
                    + "por un error en la base de datos."
            );

            System.out.println(
                    "Detalle técnico: " + e.getMessage()
            );
        }
    }
    
    public void cerrarSolicitud(Scanner scanner) {

    System.out.println("=== Cerrar solicitud ===");

    try {
        System.out.print("ID solicitud: ");

        int idSolicitud =
                Integer.parseInt(scanner.nextLine());

        if (idSolicitud <= 0) {
            throw new IllegalArgumentException(
                    "El identificador debe ser mayor a cero."
            );
        }

        solicitudServicio.cerrarSolicitud(idSolicitud);

        System.out.println(
                "Solicitud cerrada correctamente."
        );

    } catch (NumberFormatException e) {

        System.out.println(
                "Error: el ID de la solicitud "
                + "debe ser un valor numérico."
        );

    } catch (IllegalArgumentException e) {

        System.out.println(
                "Datos inválidos: " + e.getMessage()
        );

    } catch (IllegalStateException e) {

        System.out.println(
                "No se pudo cerrar la solicitud: "
                + e.getMessage()
        );

    } catch (SQLException e) {

        System.out.println(
                "No se pudo cerrar la solicitud "
                + "por un error en la base de datos."
        );

        System.out.println(
                "Detalle técnico: " + e.getMessage()
        );
    }
}
}