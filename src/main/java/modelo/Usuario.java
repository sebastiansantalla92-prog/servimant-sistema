package modelo;

public class Usuario extends Persona {
    private int idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private Rol rol;

    public Usuario() {
        super();
    }

    public Usuario(int idUsuario, String nombre, String apellido, String email,
                   String nombreUsuario, String contrasena, boolean activo, Rol rol) {
        super(nombre, apellido, email, activo);
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String obtenerDescripcion() {
        String nombreRol = rol != null ? rol.getNombre() : "Sin rol asignado";
        return "Usuario: " + getNombreCompleto() + " - Rol: " + nombreRol;
    }

    @Override
    public String toString() {
        return idUsuario + " - " + getNombreCompleto() + " (" + nombreUsuario + ")";
    }
}
