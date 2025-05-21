package com.remeco.clientes_personas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remeco.clientes_personas.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void crearCliente_Valido_DeberiaRetornarCreated() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos Pérez");
        cliente.setGenero("M");
        cliente.setEdad(40);
        cliente.setIdentificacion("1234567890");
        cliente.setDireccion("Av. Central");
        cliente.setTelefono("0987654321");
        cliente.setClienteId("cperez");
        cliente.setContrasena("segura123");
        cliente.setEstado(true);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated());
    }

    @Test
void guardarCliente_DatosInvalidos_RetornaBadRequest() throws Exception {
    // Cliente con nombre vacío y sin identificación
    Cliente clienteInvalido = new Cliente();
    clienteInvalido.setNombre(""); // inválido
    clienteInvalido.setGenero("M");
    clienteInvalido.setEdad(30);
    clienteInvalido.setDireccion("Calle falsa");
    clienteInvalido.setTelefono("0999999999");
    clienteInvalido.setClienteId("cid123");
    clienteInvalido.setContrasena("1234");
    clienteInvalido.setEstado(true);

    mockMvc.perform(post("/clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(clienteInvalido)))
            .andExpect(status().isBadRequest());
}
}

