package servicio;

import dao.SolicitudDAO;
import dao.TecnicoDAO;
import modelo.Solicitud;
import modelo.Tecnico;

import java.sql.SQLException;
import java.util.List;

public class AsignacionServicio {
    private final SolicitudDAO solicitudDAO = new SolicitudDAO();
    private final TecnicoDAO tecnicoDAO = new TecnicoDAO();

    public List<Solicitud> listarSolicitudesPendientes() throws SQLException {
        return solicitudDAO.listarPendientes();
    }

    public List<Tecnico> listarTecnicosDisponibles() throws SQLException {
        return tecnicoDAO.listarDisponibles();
    }

    public void asignarTecnico(int idSolicitud, int idTecnico, String observacion) throws SQLException {
        if (idSolicitud <= 0 || idTecnico <= 0) {
            throw new IllegalArgumentException("La solicitud y el técnico seleccionados deben ser válidos.");
        }

        solicitudDAO.registrarAsignacion(idSolicitud, idTecnico, observacion);

        final int ESTADO_ASIGNADA = 2;
        solicitudDAO.actualizarEstado(idSolicitud, ESTADO_ASIGNADA);
    }
}
