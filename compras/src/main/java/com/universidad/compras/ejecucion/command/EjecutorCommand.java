package com.universidad.compras.ejecucion.command;

import java.util.ArrayList;
import java.util.List;

import com.universidad.compras.modelo.Solicitud;

public class EjecutorCommand {

    private final List<Command> historial = new ArrayList<>();

    public EjecutorCommand() {
    }

    public boolean ejecutar(Command command) {
        boolean resultado = command.ejecutar();

        if (resultado) {
            historial.add(command);
            actualizarEstado(command.getSolicitud());
        }

        return resultado;
    }

    private void actualizarEstado(Solicitud solicitud) {

        List<Command> comandos = historial.stream()
                .filter(c -> c.getSolicitud().getId()
                .equals(solicitud.getId()))
                .toList();

        boolean reserva = comandos.stream()
                .anyMatch(c -> c instanceof ReservarPresupuesto
                && c.isEjecutado());

        boolean orden = comandos.stream()
                .anyMatch(c -> c instanceof OrdenarCompras
                && c.isEjecutado());

        if (reserva && orden) {
            solicitud.setEstado("EJECUTADA");
        }
    }

    public List<Command> getHistorial() {
        return List.copyOf(historial);
    }
}
