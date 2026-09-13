package com.universidad.compras.ejecucion.command;

import com.universidad.compras.ejecucion.OrdenCompraService;
import com.universidad.compras.modelo.Solicitud;

public class OrdenarCompras extends  Command {
    

    private final OrdenCompraService ordenCompraService;
    private final String proveedor;
    private String numeroOrden;

    public OrdenarCompras(OrdenCompraService ordenCompraService,
        Solicitud solicitud,
        String proveedor
    ){
        super(solicitud);
        this.ordenCompraService=ordenCompraService;
        this.proveedor=proveedor;
    }

    @Override 
    public Solicitud getSolicitud(){
        return this.solicitud;
    }

    @Override
    public boolean ejecutar(){
        this.numeroOrden=ordenCompraService.generar(solicitud.getId(), proveedor);            
        ejecutado=true;

        return ejecutado;
    }

    @Override
    public void deshacer(){
        if(ejecutado){
            ordenCompraService.cancelar(numeroOrden);
            ejecutado=false;
        }
    }
}
