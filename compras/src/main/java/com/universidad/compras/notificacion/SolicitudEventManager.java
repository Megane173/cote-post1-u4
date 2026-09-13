package com.universidad.compras.notificacion;

import java.util.LinkedList;
import java.util.List;

import com.universidad.compras.modelo.Solicitud;


public class SolicitudEventManager {
    
    private final List<SuscriptorSolicitud> suscriptores;

    public SolicitudEventManager(){
        this.suscriptores=new LinkedList<>();
    }

    public void suscribir(SuscriptorSolicitud s){
        suscriptores.add(s);
    }

    public void desuscribir(SuscriptorSolicitud s){
        suscriptores.remove(s);
    }

    public void notifySubscribers(Solicitud solicitud){

        suscriptores.stream().forEach(s -> s.update(solicitud));
    }
}
