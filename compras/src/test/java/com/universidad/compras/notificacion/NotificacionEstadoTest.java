// src/test/java/com/universidad/compras/notificacion/NotificacionEstadoTest.java
package com.universidad.compras.notificacion;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

import com.universidad.compras.estado.EstadoSolicitudContext;
import com.universidad.compras.modelo.Solicitud;

class NotificacionEstadoTest {

    @Test
    void cambiarEstadoDisparaLasTresReaccionesSinLanzarExcepcion() {
        Solicitud s = new Solicitud("S-020", "ana@udes.edu.co", 2500000, "SOFTWARE", "CC-100");

        SolicitudEventManager eventManager = new SolicitudEventManager();

        eventManager.suscribir(new SuscriptorCorreo());
        eventManager.suscribir(new SuscriptorDashboard());
        eventManager.suscribir(new SuscriptorAuditoria());

        EstadoSolicitudContext mecanismo = new EstadoSolicitudContext(s, eventManager);
        assertDoesNotThrow(() -> { /* cambiar el estado de "s" a través de "mecanismo" */
            mecanismo.aprobar();
         });
    }

    @Test
    void agregarUnCuartoSuscriptorDePruebaNoRequiereModificarElMecanismo() {
        
        Solicitud s = new Solicitud("S-021", "ana@udes.edu.co", 2500000, "SOFTWARE", "CC-100");

        SolicitudEventManager eventManager = new SolicitudEventManager();

        eventManager.suscribir(new SuscriptorCorreo());
        eventManager.suscribir(new SuscriptorDashboard());
        eventManager.suscribir(new SuscriptorAuditoria());

        EstadoSolicitudContext mecanismo = new EstadoSolicitudContext(s, new SolicitudEventManager());

        SuscriptorSolicitud cuartoSuscriptor = solicitud -> System.out.println("Respondiendo a cambio de estado");
        
        assertDoesNotThrow(() -> { eventManager.suscribir(cuartoSuscriptor);
            mecanismo.aprobar();
        });
    }
}