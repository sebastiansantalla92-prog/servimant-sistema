package servicio;

import modelo.Solicitud;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteServicio {

    private final SolicitudServicio solicitudServicio = new SolicitudServicio();

    /*
     * Arreglo de tamaño fijo.
     * Contiene todos los estados posibles de una solicitud.
     */
    private static final String[] ESTADOS = {
        "Pendiente",
        "Asignada",
        "En proceso",
        "Resuelta",
        "Cerrada"
    };

    public String[] obtenerEstados() {
        return ESTADOS.clone();
    }

    public int[] contarSolicitudesPorEstado() throws SQLException {

        /*
         * El servicio obtiene una lista de solicitudes desde MySQL.
         * Luego se crea explícitamente un ArrayList para cumplir
         * con la utilización de esta estructura dinámica.
         */
        List<Solicitud> solicitudesObtenidas =
                solicitudServicio.listarSolicitudes();

        ArrayList<Solicitud> solicitudes =
                new ArrayList<>(solicitudesObtenidas);

        /*
         * Este arreglo almacena la cantidad correspondiente
         * a cada estado del arreglo ESTADOS.
         */
        int[] cantidades = new int[ESTADOS.length];

        for (Solicitud solicitud : solicitudes) {

            if (solicitud.getEstado() == null
                    || solicitud.getEstado().getNombre() == null) {
                continue;
            }

            String estadoActual = solicitud.getEstado().getNombre();

            for (int i = 0; i < ESTADOS.length; i++) {

                if (ESTADOS[i].equalsIgnoreCase(estadoActual)) {
                    cantidades[i]++;
                    break;
                }
            }
        }

        return cantidades;
    }
}