package com.example.atv2Dac.Inte.controller;

import com.example.atv2Dac.dto.AssociadoDTO;
import com.example.atv2Dac.model.Associado;
import com.example.atv2Dac.service.AssociadoService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociadoService associadoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Associado associado;
    private AssociadoDTO associadoDTO;

    @BeforeEach
    public void setUp() {
        associado = new Associado();
        associado.setId(1L);
        associado.setNome("Associado 1");
        associado.setLogin("associado1@example.com");

        associadoDTO = AssociadoDTO.create(associado);
    }

    @Test
    public void testSaveAssociado() throws Exception {
        mockMvc.perform(post("/api/associado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(associado)))
                .andExpect(status().isOk());

        Mockito.verify(associadoService, Mockito.times(1)).save(any(Associado.class));
    }

    @Test
    public void testGetAssociados() throws Exception {
        List<AssociadoDTO> associados = Arrays.asList(associadoDTO);
        Mockito.when(associadoService.getAssociados()).thenReturn(associados);

        mockMvc.perform(get("/api/associado")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Associado 1"))
                .andExpect(jsonPath("$[0].login").value("associado1@example.com"));

        Mockito.verify(associadoService, Mockito.times(1)).getAssociados();
    }

    @Test
    public void testGetAssociadoById() throws Exception {
        Mockito.when(associadoService.getAssociado(1L)).thenReturn(associadoDTO);

        mockMvc.perform(get("/api/associado/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Associado 1"))
                .andExpect(jsonPath("$.login").value("associado1@example.com"));

        Mockito.verify(associadoService, Mockito.times(1)).getAssociado(1L);
    }

    @Test
    public void testUpdateAssociado() throws Exception {
        Mockito.when(associadoService.update(eq(1L), any(Associado.class))).thenReturn(associadoDTO);

        mockMvc.perform(put("/api/associado/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(associado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Associado 1"))
                .andExpect(jsonPath("$.login").value("associado1@example.com"));

        Mockito.verify(associadoService, Mockito.times(1)).update(eq(1L), any(Associado.class));
    }

    @Test
    public void testDeleteAssociado() throws Exception {
        mockMvc.perform(delete("/api/associado/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(associadoService, Mockito.times(1)).delete(1L);
    }
}
