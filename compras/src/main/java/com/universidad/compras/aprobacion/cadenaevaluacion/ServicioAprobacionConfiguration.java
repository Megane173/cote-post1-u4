package com.universidad.compras.aprobacion.cadenaevaluacion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.universidad.compras.aprobacion.ServicioAprobacion;

@Configuration 
public class ServicioAprobacionConfiguration {
    

    @Bean
    public ServicioAprobacion servicioAprobacion(){
        
        AprobadorBase revisor = new RevisorNormativo();
        revisor
            .setNext(new SupervisorArea())
            .setNext(new Gerente())
            .setNext(new DirectorFinanciero());

        return revisor;
    } 
}
