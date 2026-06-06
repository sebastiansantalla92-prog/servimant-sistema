package modelo;

public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String email;
    protected boolean activo;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String email, boolean activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public abstract String obtenerDescripcion();
}
