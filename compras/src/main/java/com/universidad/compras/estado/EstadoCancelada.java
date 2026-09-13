package com.universidad.compras.estado;

public class EstadoCancelada implements EstadoSolicitud{

    private final String estado="CANCELADA";

    public EstadoCancelada(){}

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
        return "Error: no se puede ejecutar una solicitud que ya fue "+estado.toLowerCase();
    }

    @Override
    public String cancelar(EstadoSolicitudContext context) {
        return "Error: no se puede cancelar una solicitud que ya fue "+estado.toLowerCase();
    }

    @Override 
    public String getEstado(){
        return estado;
    }
}