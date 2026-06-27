package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/servimant_db"
            + "?useUnicode=true"
            + "&characterEncoding=UTF-8"
            + "&connectionCollation=utf8mb4_unicode_ci";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private ConexionBD() {}

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}
