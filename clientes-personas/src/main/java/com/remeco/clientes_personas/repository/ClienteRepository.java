package com.remeco.clientes_personas.repository;

import com.remeco.clientes_personas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByClienteId(String clienteId);
}
