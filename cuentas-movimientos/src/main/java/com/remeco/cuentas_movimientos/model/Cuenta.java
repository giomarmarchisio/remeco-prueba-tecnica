package com.remeco.cuentas_movimientos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCuenta;

    private String tipo;

    private Double saldo;

    @NotNull(message = "El clienteId es obligatorio")
    @Min(value = 1, message = "El clienteId debe ser mayor que 0")
    private Long clienteId;

    private Boolean estado;
}
