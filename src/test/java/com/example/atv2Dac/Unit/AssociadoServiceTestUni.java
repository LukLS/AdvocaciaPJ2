package com.example.atv2Dac.Unit;

import com.example.atv2Dac.dao.AdvogadoDAO;
import com.example.atv2Dac.dao.AssociadoDAO;
import com.example.atv2Dac.dto.AssociadoDTO;
import com.example.atv2Dac.model.Associado;
import com.example.atv2Dac.service.AssociadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssociadoServiceTestUni {
    @Mock
    private AssociadoDAO associadoDAO;

    @Mock
    private AdvogadoDAO advogadoDAO;

    @InjectMocks
    private AssociadoService associadoService;

    private Associado associado;
    private AssociadoDTO associadoDTO;

    @BeforeEach
    public void setUp() {
        associado = new Associado();
        associado.setId(1L);
        associado.setNome("Associado 1");
        associado.setLogin("associado1@example.com");
        associado.setSenha("password");

        associadoDTO = AssociadoDTO.create(associado);
    }

    @Test
    public void testGetAssociados() {
        when(associadoDAO.findAll()).thenReturn(Arrays.asList(associado));

        List<AssociadoDTO> associados = associadoService.getAssociados();
        assertThat(associados).hasSize(1);
        assertThat(associados.get(0).getNome()).isEqualTo("Associado 1");
        verify(associadoDAO, times(1)).findAll();
    }

    @Test
    public void testGetAssociado() {
        when(associadoDAO.findById(1L)).thenReturn(Optional.of(associado));

        AssociadoDTO result = associadoService.getAssociado(1L);
        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Associado 1");
        verify(associadoDAO, times(1)).findById(1L);
    }

    @Test
    public void testGetAssociadoNotFound() {
        when(associadoDAO.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            associadoService.getAssociado(1L);
        });

        verify(associadoDAO, times(1)).findById(1L);
    }

    @Test
    public void testSaveAssociado() {
        Associado newAssociado = new Associado();
        newAssociado.setNome("Novo Associado");
        newAssociado.setLogin("novo.associado@example.com");
        newAssociado.setSenha("password");
        when(associadoDAO.save(any(Associado.class))).thenReturn(newAssociado);

        AssociadoDTO result = associadoService.save(newAssociado);
        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Novo Associado");
        verify(associadoDAO, times(1)).save(any(Associado.class));
    }


    @Test
    public void testSaveAssociadoWithId() {
        associado.setId(2L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            associadoService.save(associado);
        });

        assertThat(exception.getMessage()).isEqualTo("Não foi possível inserir o registro");
    }

    @Test
    public void testUpdateAssociado() {
        when(associadoDAO.findById(1L)).thenReturn(Optional.of(associado));
        when(associadoDAO.save(any(Associado.class))).thenReturn(associado);

        Associado updatedAssociado = new Associado();
        updatedAssociado.setNome("Associado Atualizado");

        AssociadoDTO result = associadoService.update(1L, updatedAssociado);
        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Associado Atualizado");
        verify(associadoDAO, times(1)).findById(1L);
        verify(associadoDAO, times(1)).save(any(Associado.class));
    }

    @Test
    public void testUpdateAssociadoNotFound() {
        when(associadoDAO.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            associadoService.update(1L, associado);
        });

        assertThat(exception.getMessage()).isEqualTo("Não foi possível atualizar o registro");
        verify(associadoDAO, times(1)).findById(1L);
    }

    @Test
    public void testDeleteAssociado() {
        when(associadoDAO.findById(1L)).thenReturn(Optional.of(associado));

        associadoService.delete(1L);
        verify(associadoDAO, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAssociadoNotFound() {
        when(associadoDAO.findById(1L)).thenReturn(Optional.empty());

        associadoService.delete(1L);
        verify(associadoDAO, times(1)).deleteById(1L);
    }
}