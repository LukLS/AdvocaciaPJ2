package com.example.atv2Dac.Unit;

import com.example.atv2Dac.dao.AdvogadoDAO;
import com.example.atv2Dac.dto.AdvogadoDTO;
import com.example.atv2Dac.model.Advogado;
import com.example.atv2Dac.service.AdvogadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdvogadoServiceTestUni {

    @Mock
    private AdvogadoDAO advogadoDAO;

    @Mock(lenient = true)
    private ModelMapper modelMapper;

    @InjectMocks
    private AdvogadoService advogadoService;

    private Advogado advogado;
    private AdvogadoDTO advogadoDTO;

    @BeforeEach
    public void setUp() {
        advogado = new Advogado();
        advogado.setId(1L);
        advogado.setNome("Advogado 1");
        advogado.setLogin("advogado1@example.com");
        advogado.setSenha("password");

        advogadoDTO = AdvogadoDTO.create(advogado);
    }

    @Test
    public void testGetAdvogados() {
        when(advogadoDAO.findAll()).thenReturn(Arrays.asList(advogado));
        when(modelMapper.map(advogado, AdvogadoDTO.class)).thenReturn(advogadoDTO);

        List<AdvogadoDTO> advogados = advogadoService.getAdvogados();
        assertThat(advogados).hasSize(1);
        assertThat(advogados.get(0).getNome()).isEqualTo("Advogado 1");
        verify(advogadoDAO, times(1)).findAll();
    }

    @Test
    public void testGetAdvogado() {
        when(advogadoDAO.findById(1L)).thenReturn(Optional.of(advogado));
        when(modelMapper.map(advogado, AdvogadoDTO.class)).thenReturn(advogadoDTO);

        AdvogadoDTO result = advogadoService.getAdvogado(1L);
        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Advogado 1");
        verify(advogadoDAO, times(1)).findById(1L);
    }

    @Test
    public void testGetAdvogadoNotFound() {
        when(advogadoDAO.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            advogadoService.getAdvogado(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("Advogado não encontrado");
        verify(advogadoDAO, times(1)).findById(1L);
    }

    @Test
    public void testSaveAdvogado() {
        advogado.setId(null);
        when(advogadoDAO.save(any(Advogado.class))).thenReturn(advogado);
        when(modelMapper.map(advogado, AdvogadoDTO.class)).thenReturn(advogadoDTO);

        AdvogadoDTO result = advogadoService.save(advogado);
        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Advogado 1");
        verify(advogadoDAO, times(1)).save(any(Advogado.class));
    }


    @Test
    public void testSaveAdvogadoWithId() {
        advogado.setId(2L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            advogadoService.save(advogado);
        });

        assertThat(exception.getMessage()).isEqualTo("Não foi possível inserir o registro");
    }

    @Test
    public void testUpdateAdvogado() {
        when(advogadoDAO.findById(1L)).thenReturn(Optional.of(advogado));
        when(advogadoDAO.save(any(Advogado.class))).thenReturn(advogado);
        when(modelMapper.map(advogado, AdvogadoDTO.class)).thenReturn(advogadoDTO);

        Advogado updatedAdvogado = new Advogado();
        updatedAdvogado.setNome("Advogado Atualizado");

        AdvogadoDTO result = advogadoService.update(1L, updatedAdvogado);
        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Advogado Atualizado");
        verify(advogadoDAO, times(1)).findById(1L);
        verify(advogadoDAO, times(1)).save(any(Advogado.class));
    }

    @Test
    public void testUpdateAdvogadoNotFound() {
        when(advogadoDAO.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            advogadoService.update(1L, advogado);
        });

        assertThat(exception.getMessage()).isEqualTo("Não foi possível atualizar o registro");
        verify(advogadoDAO, times(1)).findById(1L);
    }

    @Test
    public void testDeleteAdvogado() {
        when(advogadoDAO.findById(1L)).thenReturn(Optional.of(advogado));

        advogadoService.delete(1L);
        verify(advogadoDAO, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAdvogadoNotFound() {
        when(advogadoDAO.findById(1L)).thenReturn(Optional.empty());

        advogadoService.delete(1L);
        verify(advogadoDAO, times(1)).deleteById(1L);
    }
}
