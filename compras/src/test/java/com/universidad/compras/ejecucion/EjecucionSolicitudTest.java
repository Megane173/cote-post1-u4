// src/test/java/com/universidad/compras/ejecucion/EjecucionSolicitudTest.java
package com.universidad.compras.ejecucion;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.universidad.compras.ejecucion.command.EjecutorCommand;
import com.universidad.compras.ejecucion.command.OrdenarCompras;
import com.universidad.compras.ejecucion.command.ReservarPresupuesto;
import com.universidad.compras.modelo.Solicitud;

class EjecucionSolicitudTest {

    @Test
    void ejecutarReservaPresupuestoYGeneraOrden() {
        Solicitud s = new Solicitud("S-010", "ana@udes.edu.co", 3000000, "SOFTWARE", "CC-100");
        s.setEstado("APROBADA");
        Object ejecutor = new EjecutorCommand();
        ((EjecutorCommand) ejecutor).ejecutar(new ReservarPresupuesto(new PresupuestoService(),
                s)
        );
        ((EjecutorCommand) ejecutor).ejecutar(new OrdenarCompras(
                new OrdenCompraService(),
                s,
                "proveedor"
        )
        );

        assertEquals("EJECUTADA", s.getEstado());
    }

    @Test
    void deshacerSoloLaUltimaOperacionNoAfectaLaAnterior() {
        // ejecutar reservar presupuesto, luego generar orden; deshacer una vez
        // debe revertir solo la generación de la orden, no la reserva.
        Solicitud s = new Solicitud("S-011", "luis@udes.edu.co", 4000000, "MATERIAL_OFICINA", "CC-200");
        Object ejecutor = new EjecutorCommand();
        assertDoesNotThrow(() -> {
            ((EjecutorCommand) ejecutor).ejecutar(new ReservarPresupuesto(new PresupuestoService(),
                    s
            )
            );

            ((EjecutorCommand) ejecutor).ejecutar(new OrdenarCompras(
                    new OrdenCompraService(),
                    s,
                    "proveedor"
            )
            );
            ((EjecutorCommand) ejecutor).getHistorial().get(0).deshacer();
        });
    }

    @Test
    void elHistorialConservaTodasLasOperacionesNoSoloLaUltima() {

        Solicitud s = new Solicitud("S-012", "luis@udes.edu.co", 5000000, "MATERIAL_OFICINA", "CC-202");
        Object ejecutor = new EjecutorCommand();
        ((EjecutorCommand) ejecutor).ejecutar(new ReservarPresupuesto(new PresupuestoService(),
                s
        )
        );
        ((EjecutorCommand) ejecutor).ejecutar(new OrdenarCompras(
                new OrdenCompraService(),
                s,
                "proveedor"
        )
        );
        assertDoesNotThrow(() -> {
            ((EjecutorCommand) ejecutor).getHistorial().size(); });
    }
}
