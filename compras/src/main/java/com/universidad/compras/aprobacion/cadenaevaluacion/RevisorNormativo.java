package com.universidad.compras.aprobacion.cadenaevaluacion;

import com.universidad.compras.aprobacion.ResultadoAprobacion;
import com.universidad.compras.modelo.Solicitud;

public class RevisorNormativo extends AprobadorBase{

    @Override
    public ResultadoAprobacion evaluar(Solicitud solicitud){

        if(solicitud.getCategoria().equals("INTERNACIONAL")){
            return new ResultadoAprobacion(
                true,
                "Revisor de Cumplimiento Normativo",
                "Solicitud aprobada"
            );
        }
        
        return delegar(solicitud);
    }
}