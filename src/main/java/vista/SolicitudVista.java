package vista;

import modelo.*;
import servicio.SolicitudServicio;

import java.sql.SQLException;
import java.util.Scanner;

public class SolicitudVista {
    private final SolicitudServicio solicitudServicio = new SolicitudServicio();

    public void registrarSolicitud(Scanner scanner, Usuario usuario) {
        System.out.println("=== Registrar solicitud ===");

        System.out.print("Descripción del problema: ");
        String descripcion = scanner.nextLine();

        System.out.print("ID categoría: ");
        int idCategoria = Integer.parseInt(scanner.nextLine());

        System.out.print("ID prioridad: ");
        int idPrioridad = Integer.parseInt(scanner.nextLine());

        System.out.print("ID sector: ");
        int idSector = Integer.parseInt(scanner.nextLine());

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

        try {
            solicitudServicio.registrarSolicitud(solicitud);
            System.out.println("Solicitud registrada correctamente con estado Pendiente.");
        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("No se pudo registrar la solicitud: " + e.getMessage());
        }
    }
}
