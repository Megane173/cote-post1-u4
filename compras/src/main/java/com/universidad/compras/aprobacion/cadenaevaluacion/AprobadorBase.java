package com.universidad.compras.aprobacion.cadenaevaluacion;

import com.universidad.compras.aprobacion.ResultadoAprobacion;
import com.universidad.compras.aprobacion.ServicioAprobacion;
import com.universidad.compras.modelo.Solicitud;

public abstract class AprobadorBase implements ServicioAprobacion{

    protected AprobadorBase siguiente;

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud){
        return delegar(solicitud);
    }

    public ResultadoAprobacion delegar(Solicitud solicitud){
        if(siguiente==null) {
            return new ResultadoAprobacion(false, "", "");   
        }
        return siguiente.evaluar(solicitud);
    }

    public AprobadorBase setNext(AprobadorBase siguiente){
        this.siguiente=siguiente;
        return siguiente;
    }
}