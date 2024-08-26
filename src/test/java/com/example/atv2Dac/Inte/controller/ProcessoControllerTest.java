package com.example.atv2Dac.Inte.controller;

import com.example.atv2Dac.controller.ProcessoController;
import com.example.atv2Dac.dto.ProcessoDTO;
import com.example.atv2Dac.service.ProcessoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProcessoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProcessoService processoService;

    @InjectMocks
    private ProcessoController processoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(processoController).build();
    }

    @Test
    public void testGetAllProcessos() throws Exception {
        when(processoService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/processo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(processoService, times(1)).findAll();
    }

    @Test
    public void testGetProcessoById() throws Exception {
        ProcessoDTO processoDTO = new ProcessoDTO();
        processoDTO.setId(1L);
        processoDTO.setTitulo("Processo Teste");

        when(processoService.findById(1L)).thenReturn(Optional.of(processoDTO));

        mockMvc.perform(get("/api/processo/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Processo Teste"));

        verify(processoService, times(1)).findById(1L);
    }

    @Test
    public void testGetProcessoById_NotFound() throws Exception {
        when(processoService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/processo/1"))
                .andExpect(status().isNotFound());

        verify(processoService, times(1)).findById(1L);
    }

    @Test
    public void testCreateProcesso() throws Exception {
        ProcessoDTO processoDTO = new ProcessoDTO();
        processoDTO.setTitulo("Novo Processo");

        when(processoService.save(any(ProcessoDTO.class))).thenReturn(processoDTO);

        mockMvc.perform(post("/api/processo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Novo Processo\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titulo").value("Novo Processo"));

        verify(processoService, times(1)).save(any(ProcessoDTO.class));
    }

    @Test
    public void testUpdateProcesso() throws Exception {
        ProcessoDTO processoDTO = new ProcessoDTO();
        processoDTO.setId(1L);
        processoDTO.setTitulo("Processo Atualizado");

        when(processoService.findById(1L)).thenReturn(Optional.of(processoDTO));
        when(processoService.save(any(ProcessoDTO.class))).thenReturn(processoDTO);

        mockMvc.perform(put("/api/processo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Processo Atualizado\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titulo").value("Processo Atualizado"));

        verify(processoService, times(1)).findById(1L);
        verify(processoService, times(1)).save(any(ProcessoDTO.class));
    }

    @Test
    public void testDeleteProcesso() throws Exception {
        when(processoService.findById(1L)).thenReturn(Optional.of(new ProcessoDTO()));

        mockMvc.perform(delete("/api/processo/1"))
                .andExpect(status().isNoContent());

        verify(processoService, times(1)).findById(1L);
        verify(processoService, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteProcesso_NotFound() throws Exception {
        when(processoService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/processo/1"))
                .andExpect(status().isNotFound());

        verify(processoService, times(1)).findById(1L);
        verify(processoService, never()).deleteById(1L);
    }
}
