package dao;

import modelo.Tecnico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TecnicoDAO {

    public List<Tecnico> listarDisponibles() throws SQLException {
        String sql = "SELECT id_tecnico, nombre, apellido, especialidad, email, activo " +
                "FROM tecnico WHERE activo = TRUE ORDER BY apellido, nombre";

        List<Tecnico> tecnicos = new ArrayList<>();

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tecnicos.add(new Tecnico(
                        rs.getInt("id_tecnico"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("especialidad"),
                        rs.getString("email"),
                        rs.getBoolean("activo")
                ));
            }
        }

        return tecnicos;
    }
}
