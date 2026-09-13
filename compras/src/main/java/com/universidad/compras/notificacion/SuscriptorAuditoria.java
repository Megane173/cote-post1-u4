package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class SuscriptorAuditoria implements SuscriptorSolicitud {

    @Override
    public void update(Solicitud solicitud) {
        ClientesNotificacion.registrarAuditoria(
            solicitud.getId(),
            solicitud.getEstado(),
            "Cambio en el estado de la solicitud"
        );
    }
}