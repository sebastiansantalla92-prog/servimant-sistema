package vista;

import servicio.ReporteServicio;

import java.sql.SQLException;

public class ReporteVista {

    private final ReporteServicio reporteServicio =
            new ReporteServicio();

    public void mostrarResumenPorEstado() {

        System.out.println();
        System.out.println("=== Resumen de solicitudes por estado ===");

        try {
            String[] estados = reporteServicio.obtenerEstados();
            int[] cantidades =
                    reporteServicio.contarSolicitudesPorEstado();

            int totalSolicitudes = 0;

            for (int i = 0; i < estados.length; i++) {
                System.out.println(
                        estados[i] + ": " + cantidades[i]
                );

                totalSolicitudes += cantidades[i];
            }

            System.out.println("----------------------------------------");
            System.out.println(
                    "Total de solicitudes: " + totalSolicitudes
            );

        } catch (SQLException e) {
            System.out.println(
                    "No fue posible generar el resumen de solicitudes."
            );
            System.out.println(
                    "Detalle técnico: " + e.getMessage()
            );
        }
    }
}