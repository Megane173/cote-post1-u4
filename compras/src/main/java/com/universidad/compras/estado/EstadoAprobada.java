package com.universidad.compras.estado;

public class EstadoAprobada implements EstadoSolicitud{

    private final String estado="APROBADA";

    public EstadoAprobada(){}

    @Override
    public String aprobar(EstadoSolicitudContext context) {
        return "Error: no puede aprobar un solicitud que ya fue "+estado.toLowerCase();
    }

    @Override
    public String rechazar(EstadoSolicitudContext context) {
        return "Error: no se puede rechazar una solicitud que ya fue "+estado.toLowerCase();
    }

    @Override
    public String ejecutar(EstadoSolicitudContext context) {
        context.setEstado(new EstadoEjecutada());
        return "Ejecutada";
    }

    @Override
    public String cancelar(EstadoSolicitudContext context) {
        context.setEstado(new EstadoCancelada());
        return "Ejecutada";
    }

    @Override 
    public String getEstado(){
        return estado;
    }
}
