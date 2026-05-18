package dao;

import modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitudDAO {

    public void insertar(Solicitud solicitud) throws SQLException {
        String sql = "INSERT INTO solicitud " +
                "(descripcion, fecha_creacion, fecha_actualizacion, id_usuario, id_categoria, id_prioridad, id_estado, id_sector) " +
                "VALUES (?, NOW(), NOW(), ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, solicitud.getDescripcion());
            ps.setInt(2, solicitud.getUsuario().getIdUsuario());
            ps.setInt(3, solicitud.getCategoria().getIdCategoria());
            ps.setInt(4, solicitud.getPrioridad().getIdPrioridad());
            ps.setInt(5, solicitud.getEstado().getIdEstado());
            ps.setInt(6, solicitud.getSector().getIdSector());

            ps.executeUpdate();
        }
    }

    public List<Solicitud> listar() throws SQLException {
        String sql = "SELECT s.id_solicitud, s.descripcion, s.fecha_creacion, s.fecha_actualizacion, " +
                "u.id_usuario, u.nombre AS usuario_nombre, u.apellido AS usuario_apellido, " +
                "c.id_categoria, c.nombre AS categoria_nombre, " +
                "p.id_prioridad, p.nombre AS prioridad_nombre, " +
                "e.id_estado, e.nombre AS estado_nombre, " +
                "se.id_sector, se.nombre AS sector_nombre, se.ubicacion " +
                "FROM solicitud s " +
                "INNER JOIN usuario u ON s.id_usuario = u.id_usuario " +
                "INNER JOIN categoria c ON s.id_categoria = c.id_categoria " +
                "INNER JOIN prioridad p ON s.id_prioridad = p.id_prioridad " +
                "INNER JOIN estado e ON s.id_estado = e.id_estado " +
                "INNER JOIN sector se ON s.id_sector = se.id_sector " +
                "ORDER BY s.id_solicitud DESC";

        List<Solicitud> solicitudes = new ArrayList<>();

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                solicitudes.add(mapearSolicitud(rs));
            }
        }

        return solicitudes;
    }

    public List<Solicitud> listarPendientes() throws SQLException {
        String sql = "SELECT s.id_solicitud, s.descripcion, s.fecha_creacion, s.fecha_actualizacion, " +
                "u.id_usuario, u.nombre AS usuario_nombre, u.apellido AS usuario_apellido, " +
                "c.id_categoria, c.nombre AS categoria_nombre, " +
                "p.id_prioridad, p.nombre AS prioridad_nombre, " +
                "e.id_estado, e.nombre AS estado_nombre, " +
                "se.id_sector, se.nombre AS sector_nombre, se.ubicacion " +
                "FROM solicitud s " +
                "INNER JOIN usuario u ON s.id_usuario = u.id_usuario " +
                "INNER JOIN categoria c ON s.id_categoria = c.id_categoria " +
                "INNER JOIN prioridad p ON s.id_prioridad = p.id_prioridad " +
                "INNER JOIN estado e ON s.id_estado = e.id_estado " +
                "INNER JOIN sector se ON s.id_sector = se.id_sector " +
                "WHERE e.nombre = 'Pendiente' " +
                "ORDER BY s.id_solicitud ASC";

        List<Solicitud> solicitudes = new ArrayList<>();

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                solicitudes.add(mapearSolicitud(rs));
            }
        }

        return solicitudes;
    }

    public void actualizarEstado(int idSolicitud, int idEstado) throws SQLException {
        String sql = "UPDATE solicitud SET id_estado = ?, fecha_actualizacion = NOW() WHERE id_solicitud = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idEstado);
            ps.setInt(2, idSolicitud);
            ps.executeUpdate();
        }
    }

    public void registrarAsignacion(int idSolicitud, int idTecnico, String observacion) throws SQLException {
        String sql = "INSERT INTO asignacion (id_solicitud, id_tecnico, fecha_asignacion, observacion) VALUES (?, ?, NOW(), ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idSolicitud);
            ps.setInt(2, idTecnico);
            ps.setString(3, observacion);
            ps.executeUpdate();
        }
    }

    public boolean tieneIntervenciones(int idSolicitud) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM intervencion WHERE id_solicitud = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idSolicitud);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt("total") > 0;
            }
        }
    }

    private Solicitud mapearSolicitud(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNombre(rs.getString("usuario_nombre"));
        usuario.setApellido(rs.getString("usuario_apellido"));

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getInt("id_categoria"));
        categoria.setNombre(rs.getString("categoria_nombre"));

        Prioridad prioridad = new Prioridad();
        prioridad.setIdPrioridad(rs.getInt("id_prioridad"));
        prioridad.setNombre(rs.getString("prioridad_nombre"));

        Estado estado = new Estado();
        estado.setIdEstado(rs.getInt("id_estado"));
        estado.setNombre(rs.getString("estado_nombre"));

        Sector sector = new Sector();
        sector.setIdSector(rs.getInt("id_sector"));
        sector.setNombre(rs.getString("sector_nombre"));
        sector.setUbicacion(rs.getString("ubicacion"));

        Solicitud solicitud = new Solicitud();
        solicitud.setIdSolicitud(rs.getInt("id_solicitud"));
        solicitud.setDescripcion(rs.getString("descripcion"));
        solicitud.setUsuario(usuario);
        solicitud.setCategoria(categoria);
        solicitud.setPrioridad(prioridad);
        solicitud.setEstado(estado);
        solicitud.setSector(sector);

        Timestamp fechaCreacion = rs.getTimestamp("fecha_creacion");
        Timestamp fechaActualizacion = rs.getTimestamp("fecha_actualizacion");

        if (fechaCreacion != null) solicitud.setFechaCreacion(fechaCreacion.toLocalDateTime());
        if (fechaActualizacion != null) solicitud.setFechaActualizacion(fechaActualizacion.toLocalDateTime());

        return solicitud;
    }
}
