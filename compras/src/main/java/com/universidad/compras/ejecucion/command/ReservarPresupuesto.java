package com.universidad.compras.ejecucion.command;

import com.universidad.compras.ejecucion.PresupuestoService;
import com.universidad.compras.modelo.Solicitud;

public class ReservarPresupuesto extends Command {

    private final PresupuestoService presupuestoService;


    public ReservarPresupuesto(
            PresupuestoService presupuestoService, 
            Solicitud solicitud) {

        super(solicitud);
        this.presupuestoService = presupuestoService;
        super.ejecutado=false;
    }

    @Override
    public boolean ejecutar() {
        ejecutado = presupuestoService.reservar(solicitud.getCentroCosto(), solicitud.getMonto());
        return ejecutado;
    }

    @Override
    public void deshacer() {
        if (ejecutado) {
            presupuestoService.liberar(solicitud.getCentroCosto(), solicitud.getMonto());
            ejecutado = false;
        }
    }
}