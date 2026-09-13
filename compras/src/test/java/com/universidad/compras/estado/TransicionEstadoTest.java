// src/test/java/com/universidad/compras/estado/TransicionEstadoTest.java
package com.universidad.compras.estado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.universidad.compras.modelo.Solicitud;
import com.universidad.compras.notificacion.SolicitudEventManager;

class TransicionEstadoTest {

    @Test
    void ejecutarUnaSolicitudAprobadaLaDejaEjecutada() {
        Solicitud s = new Solicitud("S-030", "luis@udes.edu.co", 3000000, "MATERIAL_OFICINA", "CC-200");
        s.setEstado("APROBADA");
        EstadoSolicitudContext contexto= new EstadoSolicitudContext(s, new SolicitudEventManager());
        contexto.ejecutar();
        assertEquals("EJECUTADA", s.getEstado());
    }

    @Test
    void ejecutarUnaSolicitudPendienteSeRechazaSinCambiarElEstado() {
        Solicitud s = new Solicitud("S-031", "ana@udes.edu.co", 1000000, "SOFTWARE", "CC-100");
        EstadoSolicitudContext contexto= new EstadoSolicitudContext(s, new SolicitudEventManager());
        contexto.ejecutar();
        assertEquals("PENDIENTE", s.getEstado());
    }

    @Test
    void unaSolicitudEjecutadaNoPuedeVolverAEjecutarse() {
        Solicitud s = new Solicitud("S-032", "ana@udes.edu.co", 1000000, "SOFTWARE", "CC-100");
        s.setEstado("EJECUTADA");
        EstadoSolicitudContext contexto= new EstadoSolicitudContext(s, new SolicitudEventManager());
        contexto.ejecutar();
        assertEquals("EJECUTADA", s.getEstado());
    }
}