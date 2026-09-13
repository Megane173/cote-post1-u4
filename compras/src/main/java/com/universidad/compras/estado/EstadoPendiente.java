package com.universidad.compras.estado;

public class EstadoPendiente implements EstadoSolicitud{

    private final String estado="PENDIENTE";

    public EstadoPendiente(){}

    @Override
    public String aprobar(EstadoSolicitudContext context) {
        context.setEstado(new EstadoAprobada());
        return "Aprobada";
    }

    @Override
    public String rechazar(EstadoSolicitudContext context) {
        context.setEstado(new EstadoRechazada());
        return "Rechazada";
    }

    @Override
    public String ejecutar(EstadoSolicitudContext context) {
        return "Error: no se puede ejecutar una solicitud que esta "+estado.toLowerCase();
    }

    @Override
    public String cancelar(EstadoSolicitudContext context) {
        return "Error: no se puede cancelar una solicitud que esta "+estado.toLowerCase();
    }

    @Override 
    public String getEstado(){
        return estado;
    }
}
