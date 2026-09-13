package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class SuscriptorDashboard implements SuscriptorSolicitud {

    @Override
    public void update(Solicitud solicitud) {
        ClientesNotificacion.actualizarDashboardContabilidad(
            solicitud.getId(),
            solicitud.getEstado(),
            solicitud.getMonto()
        );
    }
}