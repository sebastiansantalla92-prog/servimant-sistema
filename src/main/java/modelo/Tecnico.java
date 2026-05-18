package modelo;

public class Tecnico {
    private int idTecnico;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String email;
    private boolean activo;

    public Tecnico() {}

    public Tecnico(int idTecnico, String nombre, String apellido, String especialidad, String email, boolean activo) {
        this.idTecnico = idTecnico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.email = email;
        this.activo = activo;
    }

    public int getIdTecnico() { return idTecnico; }
    public void setIdTecnico(int idTecnico) { this.idTecnico = idTecnico; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return idTecnico + " - " + nombre + " " + apellido + " (" + especialidad + ")";
    }
}
