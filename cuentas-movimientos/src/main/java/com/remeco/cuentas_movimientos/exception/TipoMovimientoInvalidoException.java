package com.remeco.cuentas_movimientos.exception;

public class TipoMovimientoInvalidoException extends RuntimeException {
    public TipoMovimientoInvalidoException() {
        super("Tipo de movimiento no válido");
    }
}
