package com.universidad.compras.estado;

public interface  EstadoSolicitud {
    
    public String aprobar(EstadoSolicitudContext context);
    public String rechazar(EstadoSolicitudContext context);
    public String ejecutar(EstadoSolicitudContext context);
    public String cancelar(EstadoSolicitudContext context);
    public String getEstado();
}
