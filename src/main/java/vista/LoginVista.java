package vista;

import dao.UsuarioDAO;
import modelo.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginVista {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario iniciarSesion(Scanner scanner) {
        System.out.println("=== Inicio de sesión ===");
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        try {
            Usuario usuarioAutenticado = usuarioDAO.validarCredenciales(usuario, contrasena);

            if (usuarioAutenticado == null) {
                System.out.println("Credenciales inválidas o usuario inactivo.");
                return null;
            }

            System.out.println("Bienvenido/a, " + usuarioAutenticado.getNombre() + " " + usuarioAutenticado.getApellido());
            System.out.println("Rol: " + usuarioAutenticado.getRol().getNombre());
            return usuarioAutenticado;

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }
}
