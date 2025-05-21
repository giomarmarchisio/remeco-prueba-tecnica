package com.remeco.clientes_personas.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void clienteInvalido_DeberiaTenerViolaciones() {
        Cliente cliente = new Cliente(); // sin datos

        Set<ConstraintViolation<Cliente>> violaciones = validator.validate(cliente);

        assertFalse(violaciones.isEmpty(), "Se esperaban violaciones de restricciones");
    }

    @Test
    public void clienteValido_NoDeberiaTenerViolaciones() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Giomar");
        cliente.setGenero("M");
        cliente.setEdad(40);
        cliente.setIdentificacion("1234567890");
        cliente.setDireccion("Av. Principal");
        cliente.setTelefono("0999999999");
        cliente.setClienteId("gmiomar");
        cliente.setContrasena("abc123");
        cliente.setEstado(true);

        Set<ConstraintViolation<Cliente>> violaciones = validator.validate(cliente);

        assertTrue(violaciones.isEmpty(), "No se esperaban violaciones");
    }
}
