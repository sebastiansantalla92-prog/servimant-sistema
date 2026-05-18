package dao;

import modelo.Rol;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario validarCredenciales(String nombreUsuario, String contrasena) throws SQLException {
        String sql = "SELECT u.id_usuario, u.nombre, u.apellido, u.email, u.nombre_usuario, u.contrasena, u.activo, " +
                "r.id_rol, r.nombre AS rol_nombre, r.descripcion AS rol_descripcion " +
                "FROM usuario u INNER JOIN rol r ON u.id_rol = r.id_rol " +
                "WHERE u.nombre_usuario = ? AND u.contrasena = ? AND u.activo = TRUE";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Rol rol = new Rol(
                            rs.getInt("id_rol"),
                            rs.getString("rol_nombre"),
                            rs.getString("rol_descripcion")
                    );

                    return new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("nombre_usuario"),
                            rs.getString("contrasena"),
                            rs.getBoolean("activo"),
                            rol
                    );
                }
            }
        }

        return null;
    }
}
