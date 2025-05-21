package com.remeco.cuentas_movimientos.controller;

import com.remeco.cuentas_movimientos.model.Movimiento;
import com.remeco.cuentas_movimientos.service.MovimientoService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService servicio;

    public MovimientoController(MovimientoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Movimiento> listar() {
        return servicio.listar();
    }

    @PostMapping
    public ResponseEntity<Movimiento> crear(@RequestBody @Valid Movimiento movimiento) {
        if (movimiento.getMonto() == null || movimiento.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }

        if (movimiento.getTipo() == null ||
           (!movimiento.getTipo().equalsIgnoreCase("DEPOSITO") &&
            !movimiento.getTipo().equalsIgnoreCase("RETIRO"))) {
            throw new IllegalArgumentException("El tipo debe ser DEPOSITO o RETIRO");
        }

        Movimiento nuevo = servicio.guardar(movimiento);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{id}")
    public Movimiento obtenerPorId(@PathVariable Long id) {
        return servicio.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }

    @GetMapping("/cuenta/{cuentaId}")
    public List<Movimiento> obtenerPorCuentaId(@PathVariable Long cuentaId) {
        return servicio.obtenerPorCuentaId(cuentaId);
    }

    @PutMapping("/{id}")
public ResponseEntity<Movimiento> actualizar(@PathVariable Long id, @RequestBody Movimiento movimientoActualizado) {
    Movimiento movimiento = servicio.actualizar(id, movimientoActualizado);
    return ResponseEntity.ok(movimiento);
}

}
