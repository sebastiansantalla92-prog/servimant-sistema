package modelo;

public class Prioridad {
    private int idPrioridad;
    private String nombre;
    private String descripcion;
    private int nivel;

    public Prioridad() {}

    public Prioridad(int idPrioridad, String nombre, String descripcion, int nivel) {
        this.idPrioridad = idPrioridad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
    }

    public int getIdPrioridad() { return idPrioridad; }
    public void setIdPrioridad(int idPrioridad) { this.idPrioridad = idPrioridad; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }
}
