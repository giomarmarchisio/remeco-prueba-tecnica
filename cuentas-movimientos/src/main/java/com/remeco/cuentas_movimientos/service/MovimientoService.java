package com.remeco.cuentas_movimientos.service;

import com.remeco.cuentas_movimientos.exception.SaldoInsuficienteException;
import com.remeco.cuentas_movimientos.exception.TipoMovimientoInvalidoException;
import com.remeco.cuentas_movimientos.model.Cuenta;
import com.remeco.cuentas_movimientos.model.Movimiento;
import com.remeco.cuentas_movimientos.repository.CuentaRepository;
import com.remeco.cuentas_movimientos.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public List<Movimiento> listar() {
        return movimientoRepository.findAll();
    }

    public Movimiento guardar(Movimiento movimiento) {
    Cuenta cuenta = cuentaRepository.findById(movimiento.getCuentaId())
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

    if ("DEPOSITO".equalsIgnoreCase(movimiento.getTipo())) {
        cuenta.setSaldo(cuenta.getSaldo() + movimiento.getMonto());
    } else if ("RETIRO".equalsIgnoreCase(movimiento.getTipo())) {
        if (cuenta.getSaldo() < movimiento.getMonto()) {
            throw new SaldoInsuficienteException();
        }
        cuenta.setSaldo(cuenta.getSaldo() - movimiento.getMonto());
        } else {
            throw new TipoMovimientoInvalidoException();
        }

        cuentaRepository.save(cuenta);
    movimiento.setFecha(LocalDateTime.now());
    return movimientoRepository.save(movimiento);
    }

    public void eliminar(Long id) {
        movimientoRepository.deleteById(id);
    }

    public Movimiento obtenerPorId(Long id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    public List<Movimiento> obtenerPorCuentaId(Long cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId);
    }

    public Movimiento actualizar(Long id, Movimiento movimientoActualizado) {
    Movimiento existente = movimientoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

    existente.setTipo(movimientoActualizado.getTipo());
    existente.setMonto(movimientoActualizado.getMonto());
    existente.setDescripcion(movimientoActualizado.getDescripcion());

    return movimientoRepository.save(existente);

}

}
