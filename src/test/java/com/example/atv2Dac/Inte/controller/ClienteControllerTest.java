package com.example.atv2Dac.Inte.controller;

import com.example.atv2Dac.dto.ClienteDTO;
import com.example.atv2Dac.model.Cliente;
import com.example.atv2Dac.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente 1");
        cliente.setEmail("cliente1@example.com");

        clienteDTO = ClienteDTO.create(cliente);
    }

    @Test
    public void testSaveCliente() throws Exception {
        mockMvc.perform(post("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk());

        Mockito.verify(clienteService, Mockito.times(1)).save(any(Cliente.class));
    }

    @Test
    public void testGetClientes() throws Exception {
        List<ClienteDTO> clientes = Arrays.asList(clienteDTO);
        Mockito.when(clienteService.getClientes()).thenReturn(clientes);

        mockMvc.perform(get("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Cliente 1"))
                .andExpect(jsonPath("$[0].email").value("cliente1@example.com"));

        Mockito.verify(clienteService, Mockito.times(1)).getClientes();
    }

    @Test
    public void testGetClienteById() throws Exception {
        Mockito.when(clienteService.getCliente(1L)).thenReturn(clienteDTO);

        mockMvc.perform(get("/api/cliente/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Cliente 1"))
                .andExpect(jsonPath("$.email").value("cliente1@example.com"));

        Mockito.verify(clienteService, Mockito.times(1)).getCliente(1L);
    }

    @Test
    public void testUpdateCliente() throws Exception {
        Mockito.when(clienteService.update(eq(1L), any(Cliente.class))).thenReturn(clienteDTO);

        mockMvc.perform(put("/api/cliente/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Cliente 1"))
                .andExpect(jsonPath("$.email").value("cliente1@example.com"));

        Mockito.verify(clienteService, Mockito.times(1)).update(eq(1L), any(Cliente.class));
    }

    @Test
    public void testDeleteCliente() throws Exception {
        mockMvc.perform(delete("/api/cliente/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(clienteService, Mockito.times(1)).delete(1L);
    }
}
