package com.universidad.compras.estado;

import com.universidad.compras.modelo.Solicitud;
import com.universidad.compras.notificacion.SolicitudEventManager;

public class EstadoSolicitudContext{
    
    private final Solicitud solicitud;
    private EstadoSolicitud estadoSolicitud;
    private final SolicitudEventManager solicitudEventManager;


    public EstadoSolicitudContext(Solicitud solicitud, SolicitudEventManager solicitudEventManager){
        this.solicitud=solicitud;
        this.solicitudEventManager=solicitudEventManager;
        this.estadoSolicitud=crearEstado(solicitud.getEstado());
    }


    public String aprobar() {
        return estadoSolicitud.aprobar(this);
    }

    public String rechazar() {
        return estadoSolicitud.rechazar(this);
    }

    public String ejecutar() {
        return estadoSolicitud.ejecutar(this);
    }

    public String cancelar() {
        return estadoSolicitud.cancelar(this);
    }

    public void setEstado(EstadoSolicitud estadoSolicitud){
        this.estadoSolicitud=estadoSolicitud;
        this.solicitud.setEstado(estadoSolicitud.getEstado());
        solicitudEventManager.notifySubscribers(this.solicitud);
    }
    
    private EstadoSolicitud crearEstado(String estado) {
    return switch (estado) {
        case "PENDIENTE" -> new EstadoPendiente();
        case "APROBADA" -> new EstadoAprobada();
        case "EJECUTADA" -> new EstadoEjecutada();
        case "CANCELADA" -> new EstadoCancelada();
        case "RECHAZADA" -> new EstadoRechazada();
        default -> throw new IllegalArgumentException(
            "Estado desconocido: " + estado
        );
    };
}
}
