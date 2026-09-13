package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;

public class SuscriptorCorreo implements SuscriptorSolicitud {

    @Override
    public void update(Solicitud solicitud) {
        ClientesNotificacion.enviarCorreo(
            solicitud.getSolicitanteEmail(),
            "Cambio de estado",
            "La solicitud " + solicitud.getId()
                + " cambió a " + solicitud.getEstado()
        );
    }
}