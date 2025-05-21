package com.remeco.cuentas_movimientos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cuentaId es obligatoria")
    private Long cuentaId;

    @NotBlank(message = "El tipo no puede estar vacío")
    private String tipo; // "DEPOSITO", "RETIRO"

    @NotNull
    @Min(value = 0, message = "El monto debe ser mayor a cero")
    private Double monto;

    private LocalDateTime fecha;

    private String descripcion;
}
