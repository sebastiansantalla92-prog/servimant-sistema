package servicio;

import dao.IntervencionDAO;
import dao.SolicitudDAO;
import modelo.Intervencion;

import java.sql.SQLException;
import java.util.List;

public class IntervencionServicio {
    private final IntervencionDAO intervencionDAO = new IntervencionDAO();
    private final SolicitudDAO solicitudDAO = new SolicitudDAO();

    public boolean validarIntervencion(Intervencion intervencion) {
        return intervencion != null
                && intervencion.getSolicitud() != null
                && intervencion.getTecnico() != null
                && intervencion.getEstadoResultante() != null
                && intervencion.getDescripcion() != null
                && !intervencion.getDescripcion().isBlank();
    }

    public void registrarIntervencion(Intervencion intervencion) throws SQLException {
        if (!validarIntervencion(intervencion)) {
            throw new IllegalArgumentException("La intervención posee datos obligatorios incompletos.");
        }

        intervencionDAO.insertar(intervencion);
        solicitudDAO.actualizarEstado(
                intervencion.getSolicitud().getIdSolicitud(),
                intervencion.getEstadoResultante().getIdEstado()
        );
    }

    public List<Intervencion> consultarHistorial(int idSolicitud) throws SQLException {
        return intervencionDAO.listarPorSolicitud(idSolicitud);
    }
}
