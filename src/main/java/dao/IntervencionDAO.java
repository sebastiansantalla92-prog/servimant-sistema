package dao;

import modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IntervencionDAO {

    public void insertar(Intervencion intervencion) throws SQLException {
        String sql = "INSERT INTO intervencion " +
                "(id_solicitud, id_tecnico, descripcion, fecha_intervencion, id_estado_resultante) " +
                "VALUES (?, ?, ?, NOW(), ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, intervencion.getSolicitud().getIdSolicitud());
            ps.setInt(2, intervencion.getTecnico().getIdTecnico());
            ps.setString(3, intervencion.getDescripcion());
            ps.setInt(4, intervencion.getEstadoResultante().getIdEstado());

            ps.executeUpdate();
        }
    }

    public List<Intervencion> listarPorSolicitud(int idSolicitud) throws SQLException {
        String sql = "SELECT i.id_intervencion, i.descripcion, i.fecha_intervencion, " +
                "t.id_tecnico, t.nombre AS tecnico_nombre, t.apellido AS tecnico_apellido, t.especialidad, " +
                "e.id_estado, e.nombre AS estado_nombre " +
                "FROM intervencion i " +
                "INNER JOIN tecnico t ON i.id_tecnico = t.id_tecnico " +
                "INNER JOIN estado e ON i.id_estado_resultante = e.id_estado " +
                "WHERE i.id_solicitud = ? ORDER BY i.fecha_intervencion DESC";

        List<Intervencion> intervenciones = new ArrayList<>();

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idSolicitud);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tecnico tecnico = new Tecnico();
                    tecnico.setIdTecnico(rs.getInt("id_tecnico"));
                    tecnico.setNombre(rs.getString("tecnico_nombre"));
                    tecnico.setApellido(rs.getString("tecnico_apellido"));
                    tecnico.setEspecialidad(rs.getString("especialidad"));

                    Estado estado = new Estado();
                    estado.setIdEstado(rs.getInt("id_estado"));
                    estado.setNombre(rs.getString("estado_nombre"));

                    Solicitud solicitud = new Solicitud();
                    solicitud.setIdSolicitud(idSolicitud);

                    Intervencion intervencion = new Intervencion();
                    intervencion.setIdIntervencion(rs.getInt("id_intervencion"));
                    intervencion.setSolicitud(solicitud);
                    intervencion.setTecnico(tecnico);
                    intervencion.setDescripcion(rs.getString("descripcion"));
                    intervencion.setEstadoResultante(estado);

                    Timestamp fecha = rs.getTimestamp("fecha_intervencion");
                    if (fecha != null) intervencion.setFechaIntervencion(fecha.toLocalDateTime());

                    intervenciones.add(intervencion);
                }
            }
        }

        return intervenciones;
    }
}
