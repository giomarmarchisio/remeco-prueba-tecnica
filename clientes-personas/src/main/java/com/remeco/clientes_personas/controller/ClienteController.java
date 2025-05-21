package com.remeco.clientes_personas.controller;

import com.remeco.clientes_personas.model.Cliente;
import com.remeco.clientes_personas.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService servicio;

    public ClienteController(ClienteService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = servicio.listar();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
    Cliente cliente = servicio.obtenerPorId(id);
    return ResponseEntity.ok(cliente);
}


    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente) {
        Cliente nuevoCliente = servicio.guardar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente actualizado = servicio.actualizar(id, cliente);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
