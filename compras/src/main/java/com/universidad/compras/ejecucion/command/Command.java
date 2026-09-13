package com.universidad.compras.ejecucion.command;

import com.universidad.compras.modelo.Solicitud;

public abstract class  Command {

    protected boolean ejecutado;

    protected final Solicitud solicitud;

    protected Command(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public abstract boolean ejecutar();

    public abstract  void deshacer();

    public Solicitud getSolicitud(){
        return solicitud;
    }

    public boolean isEjecutado(){
        return ejecutado;
    }
}
