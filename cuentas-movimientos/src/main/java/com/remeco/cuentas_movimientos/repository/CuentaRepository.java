package com.remeco.cuentas_movimientos.repository;

import com.remeco.cuentas_movimientos.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    boolean existsByNumeroCuenta(String numeroCuenta);
}
