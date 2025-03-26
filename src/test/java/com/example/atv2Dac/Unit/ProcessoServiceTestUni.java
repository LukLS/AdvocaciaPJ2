package com.example.atv2Dac.Unit;

import com.example.atv2Dac.dao.ClienteDAO;
import com.example.atv2Dac.dao.ProcessoDAO;
import com.example.atv2Dac.dto.ProcessoDTO;
import com.example.atv2Dac.model.Processo;
import com.example.atv2Dac.service.ProcessoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProcessoServiceTestUni {

    @InjectMocks
    private ProcessoService processoService;

    @Mock
    private ProcessoDAO processoRepository;

    @Mock
    private ClienteDAO clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Processo processo = new Processo();
        processo.setId(1L);
        processo.setTitulo("Processo Teste");

        when(processoRepository.findAll()).thenReturn(Collections.singletonList(processo));

        List<ProcessoDTO> processos = processoService.findAll();

        assertNotNull(processos);
        assertEquals(1, processos.size());
        assertEquals("Processo Teste", processos.get(0).getTitulo());

        verify(processoRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Processo processo = new Processo();
        processo.setId(1L);
        processo.setTitulo("Processo Teste");

        when(processoRepository.findById(1L)).thenReturn(Optional.of(processo));

        Optional<ProcessoDTO> processoDTO = processoService.findById(1L);

        assertTrue(processoDTO.isPresent());
        assertEquals("Processo Teste", processoDTO.get().getTitulo());

        verify(processoRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        Processo processo = new Processo();
        processo.setTitulo("Novo Processo");

        when(processoRepository.save(any(Processo.class))).thenReturn(processo);

        ProcessoDTO processoDTO = new ProcessoDTO();
        processoDTO.setTitulo("Novo Processo");

        ProcessoDTO savedProcessoDTO = processoService.save(processoDTO);

        assertNotNull(savedProcessoDTO);
        assertEquals("Novo Processo", savedProcessoDTO.getTitulo());

        verify(processoRepository, times(1)).save(any(Processo.class));
    }

    @Test
    public void testDeleteById_success() {
        when(processoRepository.existsById(1L)).thenReturn(true);

        doNothing().when(processoRepository).deleteById(1L);

        processoService.deleteById(1L);

        verify(processoRepository, times(1)).deleteById(1L);
        verify(processoRepository, times(1)).existsById(1L);
    }

    @Test
    public void testDeleteById_notFound() {
        when(processoRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            processoService.deleteById(1L);
        });

        verify(processoRepository, times(1)).existsById(1L);
        verify(processoRepository, times(0)).deleteById(1L); // Não deve chamar deleteById
    }

}
