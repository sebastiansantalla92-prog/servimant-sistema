package servicio;

import dao.SolicitudDAO;
import modelo.Solicitud;

import java.sql.SQLException;
import java.util.List;

public class SolicitudServicio {
    private final SolicitudDAO solicitudDAO = new SolicitudDAO();

    public boolean validarSolicitud(Solicitud solicitud) {
        return solicitud != null
                && solicitud.getDescripcion() != null
                && !solicitud.getDescripcion().isBlank()
                && solicitud.getUsuario() != null
                && solicitud.getCategoria() != null
                && solicitud.getPrioridad() != null
                && solicitud.getEstado() != null
                && solicitud.getSector() != null;
    }

    public void registrarSolicitud(Solicitud solicitud) throws SQLException {
        if (!validarSolicitud(solicitud)) {
            throw new IllegalArgumentException("La solicitud posee datos obligatorios incompletos.");
        }
        solicitudDAO.insertar(solicitud);
    }

    public List<Solicitud> listarSolicitudes() throws SQLException {
        return solicitudDAO.listar();
    }

    public void cerrarSolicitud(int idSolicitud) throws SQLException {
        boolean tieneIntervenciones = solicitudDAO.tieneIntervenciones(idSolicitud);

        if (!tieneIntervenciones) {
            throw new IllegalStateException("No se puede cerrar la solicitud si no posee intervenciones registradas.");
        }

        final int ESTADO_CERRADA = 5;
        solicitudDAO.actualizarEstado(idSolicitud, ESTADO_CERRADA);
    }
}
