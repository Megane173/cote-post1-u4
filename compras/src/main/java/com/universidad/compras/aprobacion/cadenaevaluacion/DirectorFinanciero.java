package com.universidad.compras.aprobacion.cadenaevaluacion;

import com.universidad.compras.aprobacion.ResultadoAprobacion;
import com.universidad.compras.modelo.Solicitud;

public class DirectorFinanciero extends AprobadorBase{

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud){

        if(solicitud.getMonto()>10_000_000){
            return delegar(solicitud);
        }
        return new ResultadoAprobacion(
                true,
                "Director financiero",
                "Solicitud aprobada"
            );
    }
}