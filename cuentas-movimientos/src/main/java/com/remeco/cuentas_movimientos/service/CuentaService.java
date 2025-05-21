package com.remeco.cuentas_movimientos.service;

import com.remeco.cuentas_movimientos.model.Cuenta;
import com.remeco.cuentas_movimientos.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    private final CuentaRepository repository;

    public CuentaService(CuentaRepository repository) {
        this.repository = repository;
    }

    public List<Cuenta> listar() {
        return repository.findAll();
    }

    public Cuenta guardar(Cuenta cuenta) {
        return repository.save(cuenta);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Cuenta obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean existePorNumeroCuenta(String numeroCuenta) {
        return repository.existsByNumeroCuenta(numeroCuenta);
    }

    // 👉 MÉTODO FALTANTE
    public Cuenta actualizar(Long id, Cuenta cuentaActualizada) {
        Cuenta existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        existente.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
        existente.setTipo(cuentaActualizada.getTipo());
        existente.setSaldo(cuentaActualizada.getSaldo());
        existente.setEstado(cuentaActualizada.getEstado());
        existente.setClienteId(cuentaActualizada.getClienteId());

        return repository.save(existente);
    }
}
