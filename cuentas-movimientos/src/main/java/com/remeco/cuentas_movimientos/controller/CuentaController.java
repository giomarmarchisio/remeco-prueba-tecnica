package com.remeco.cuentas_movimientos.controller;

import com.remeco.cuentas_movimientos.model.Cuenta;
import com.remeco.cuentas_movimientos.service.CuentaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService servicio;

    public CuentaController(CuentaService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Cuenta> listar() {
        return servicio.listar();
    }

    @PostMapping
    public Cuenta guardar(@RequestBody Cuenta cuenta) {
        return servicio.guardar(cuenta);
    }

    @GetMapping("/{id}")
    public Cuenta obtenerPorId(@PathVariable Long id) {
        return servicio.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
    
    @PutMapping("/{id}")
public ResponseEntity<Cuenta> actualizar(@PathVariable Long id, @RequestBody Cuenta cuentaActualizada) {
    Cuenta cuenta = servicio.actualizar(id, cuentaActualizada);
    return ResponseEntity.ok(cuenta);
}

}
