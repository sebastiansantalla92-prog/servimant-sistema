package modelo;

public class Tecnico extends Persona {
    private int idTecnico;
    private String especialidad;

    public Tecnico() {
        super();
    }

    public Tecnico(int idTecnico, String nombre, String apellido, String especialidad, String email, boolean activo) {
        super(nombre, apellido, email, activo);
        this.idTecnico = idTecnico;
        this.especialidad = especialidad;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String obtenerDescripcion() {
        return "Técnico: " + getNombreCompleto() + " - Especialidad: " + especialidad;
    }

    @Override
    public String toString() {
        return idTecnico + " - " + getNombreCompleto() + " (" + especialidad + ")";
    }
}
