package com.example.atv2Dac.Inte.controller;

import com.example.atv2Dac.dto.AdvogadoDTO;
import com.example.atv2Dac.model.Advogado;
import com.example.atv2Dac.service.AdvogadoService;
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
public class AdvogadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdvogadoService advogadoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Advogado advogado;
    private AdvogadoDTO advogadoDTO;

    @BeforeEach
    public void setUp() {
        advogado = new Advogado();
        advogado.setId(1L);
        advogado.setNome("Advogado 1");
        advogado.setLogin("advogado1@example.com");

        advogadoDTO = AdvogadoDTO.create(advogado);
    }

    @Test
    public void testSaveAdvogado() throws Exception {
        mockMvc.perform(post("/api/advogado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(advogado)))
                .andExpect(status().isOk());

        Mockito.verify(advogadoService, Mockito.times(1)).save(any(Advogado.class));
    }

    @Test
    public void testGetAdvogados() throws Exception {
        List<AdvogadoDTO> advogados = Arrays.asList(advogadoDTO);
        Mockito.when(advogadoService.getAdvogados()).thenReturn(advogados);

        mockMvc.perform(get("/api/advogado")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Advogado 1"))
                .andExpect(jsonPath("$[0].login").value("advogado1@example.com"));

        Mockito.verify(advogadoService, Mockito.times(1)).getAdvogados();
    }

    @Test
    public void testGetAdvogadoById() throws Exception {
        Mockito.when(advogadoService.getAdvogado(1L)).thenReturn(advogadoDTO);

        mockMvc.perform(get("/api/advogado/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Advogado 1"))
                .andExpect(jsonPath("$.login").value("advogado1@example.com"));

        Mockito.verify(advogadoService, Mockito.times(1)).getAdvogado(1L);
    }

    @Test
    public void testUpdateAdvogado() throws Exception {
        Mockito.when(advogadoService.update(eq(1L), any(Advogado.class))).thenReturn(advogadoDTO);

        mockMvc.perform(put("/api/advogado/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(advogado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Advogado 1"))
                .andExpect(jsonPath("$.login").value("advogado1@example.com"));

        Mockito.verify(advogadoService, Mockito.times(1)).update(eq(1L), any(Advogado.class));
    }

    @Test
    public void testDeleteAdvogado() throws Exception {
        mockMvc.perform(delete("/api/advogado/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(advogadoService, Mockito.times(1)).delete(1L);
    }
}
