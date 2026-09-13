
// src/test/java/com/universidad/compras/aprobacion/AprobacionNivelesTest.java
package com.universidad.compras.aprobacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.universidad.compras.aprobacion.cadenaevaluacion.AprobadorBase;
import com.universidad.compras.aprobacion.cadenaevaluacion.DirectorFinanciero;
import com.universidad.compras.aprobacion.cadenaevaluacion.Gerente;
import com.universidad.compras.aprobacion.cadenaevaluacion.RevisorNormativo;
import com.universidad.compras.aprobacion.cadenaevaluacion.SupervisorArea;
import com.universidad.compras.modelo.Solicitud;

class AprobacionNivelesTest {

    @Test
    void solicitudDentroDeAutoridadDelSupervisorSeAprueba() {

         AprobadorBase supervisor = new SupervisorArea();
        supervisor
            .setNext(new Gerente())
            .setNext(new DirectorFinanciero());

        ServicioAprobacion servicio = supervisor;
        Solicitud s = new Solicitud("S-001", "ana@udes.edu.co", 1500000, "MATERIAL_OFICINA", "CC-100");
        ResultadoAprobacion r = servicio.evaluar(s);
        assertTrue(r.isAprobada());
        assertEquals("Supervisor de Área", r.getNivelResolutor());
    }

    @Test
    void solicitudQueSuperaAlSupervisorEscalaAlGerente() {

        AprobadorBase supervisor = new SupervisorArea();
        supervisor
            .setNext(new Gerente())
            .setNext(new DirectorFinanciero());

        ServicioAprobacion servicio = supervisor;
        Solicitud s = new Solicitud("S-002", "luis@udes.edu.co", 6000000, "SOFTWARE", "CC-200");
        ResultadoAprobacion r = servicio.evaluar(s);
        assertTrue(r.isAprobada());
        assertEquals("Gerente de Área", r.getNivelResolutor());
    }

    @Test
    void solicitudInternacionalPasaPorCumplimientoAntesDelNivelPorMonto() {
        AprobadorBase revisor = new RevisorNormativo();
        revisor
            .setNext(new SupervisorArea())
            .setNext(new Gerente())
            .setNext(new DirectorFinanciero());

        ServicioAprobacion servicio = revisor;
        Solicitud s = new Solicitud("S-003", "gerencia@udes.edu.co", 1000000, "INTERNACIONAL", "CC-300");
        ResultadoAprobacion r = servicio.evaluar(s);
        assertEquals("Revisor de Cumplimiento Normativo", r.getNivelResolutor());
    }
}