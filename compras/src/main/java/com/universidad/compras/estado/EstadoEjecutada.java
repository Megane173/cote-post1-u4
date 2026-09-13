package com.universidad.compras.estado;

public class EstadoEjecutada implements EstadoSolicitud{

    private final String estado="EJECUTADA";

    public EstadoEjecutada(){}

    @Override
    public String aprobar(EstadoSolicitudContext context) {
        return "Error: no puede abrobar un solicitud que ya fue "+estado.toLowerCase();
    }

    @Override
    public String rechazar(EstadoSolicitudContext context) {
        return "Error: no se puede rechazar una solicitud que ya fue "+estado.toLowerCase();
    }

    @Override
    public String ejecutar(EstadoSolicitudContext context) {
        return "Error: no se puede ejecutar una solicitud que ya fue "+estado.toLowerCase();
    }

    @Override
    public String cancelar(EstadoSolicitudContext context) {
        context.setEstado(new EstadoCancelada());
        return "Cancelar";
    }

    @Override 
    public String getEstado(){
        return estado;
    }
}