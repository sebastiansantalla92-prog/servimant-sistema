package modelo;

public class Sector {
    private int idSector;
    private String nombre;
    private String ubicacion;
    private String descripcion;

    public Sector() {}

    public Sector(int idSector, String nombre, String ubicacion, String descripcion) {
        this.idSector = idSector;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    public int getIdSector() { return idSector; }
    public void setIdSector(int idSector) { this.idSector = idSector; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
