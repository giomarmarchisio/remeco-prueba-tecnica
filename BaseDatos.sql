
-- Script de creación de base de datos para la prueba técnica REMECO

CREATE DATABASE IF NOT EXISTS remeco;
USE remeco;

-- Tabla de clientes
CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(50),
    edad INT,
    identificacion VARCHAR(100) UNIQUE,
    direccion VARCHAR(255),
    telefono VARCHAR(50),
    cliente_id VARCHAR(100),
    contrasena VARCHAR(255),
    estado BOOLEAN DEFAULT TRUE
);

-- Tabla de cuentas
CREATE TABLE cuenta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(100) NOT NULL UNIQUE,
    tipo VARCHAR(50) NOT NULL,
    saldo DOUBLE DEFAULT 0,
    estado BOOLEAN DEFAULT TRUE,
    cliente_id BIGINT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Tabla de movimientos
CREATE TABLE movimiento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cuenta_id BIGINT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    monto DOUBLE NOT NULL,
    descripcion VARCHAR(255),
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);
