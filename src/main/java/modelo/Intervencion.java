package modelo;

import java.time.LocalDateTime;

public class Intervencion {
    private int idIntervencion;
    private Solicitud solicitud;
    private Tecnico tecnico;
    private String descripcion;
    private LocalDateTime fechaIntervencion;
    private Estado estadoResultante;

    public int getIdIntervencion() { return idIntervencion; }
    public void setIdIntervencion(int idIntervencion) { this.idIntervencion = idIntervencion; }

    public Solicitud getSolicitud() { return solicitud; }
    public void setSolicitud(Solicitud solicitud) { this.solicitud = solicitud; }

    public Tecnico getTecnico() { return tecnico; }
    public void setTecnico(Tecnico tecnico) { this.tecnico = tecnico; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaIntervencion() { return fechaIntervencion; }
    public void setFechaIntervencion(LocalDateTime fechaIntervencion) { this.fechaIntervencion = fechaIntervencion; }

    public Estado getEstadoResultante() { return estadoResultante; }
    public void setEstadoResultante(Estado estadoResultante) { this.estadoResultante = estadoResultante; }

    @Override
    public String toString() {
        return "#" + idIntervencion + " - " + descripcion;
    }
}
