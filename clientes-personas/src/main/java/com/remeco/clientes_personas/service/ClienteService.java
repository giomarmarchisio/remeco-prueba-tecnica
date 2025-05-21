package com.remeco.clientes_personas.service;

import com.remeco.clientes_personas.model.Cliente;
import com.remeco.clientes_personas.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente guardar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente actualizar(Long id, Cliente clienteActualizado) {
        Cliente existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Copiamos los datos actualizables (excepto ID)
        existente.setNombre(clienteActualizado.getNombre());
        existente.setGenero(clienteActualizado.getGenero());
        existente.setEdad(clienteActualizado.getEdad());
        existente.setIdentificacion(clienteActualizado.getIdentificacion());
        existente.setDireccion(clienteActualizado.getDireccion());
        existente.setTelefono(clienteActualizado.getTelefono());
        existente.setClienteId(clienteActualizado.getClienteId());
        existente.setContrasena(clienteActualizado.getContrasena());
        existente.setEstado(clienteActualizado.getEstado());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        repository.deleteById(id);
    }

        public Cliente obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

}

