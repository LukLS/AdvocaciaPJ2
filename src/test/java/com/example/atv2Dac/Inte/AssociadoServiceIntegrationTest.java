package com.example.atv2Dac.Inte;

import com.example.atv2Dac.dao.AssociadoDAO;
import com.example.atv2Dac.dto.AssociadoDTO;
import com.example.atv2Dac.model.Associado;
import com.example.atv2Dac.service.AssociadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class AssociadoServiceIntegrationTest {

    @Autowired
    private AssociadoService associadoService;

    @Autowired
    private AssociadoDAO associadoDAO;

    @Test
    public void testSaveAssociado() {
        Associado associado = new Associado();
        associado.setLogin("testLogin");
        associado.setSenha("testSenha");
        associado.setNome("Test Nome");
        associado.setCpf("12345678900");

        AssociadoDTO savedAssociado = associadoService.save(associado);

        assertNotNull(savedAssociado.getId());
        assertEquals("testLogin", savedAssociado.getLogin());
    }

    @Test
    public void testGetAssociados() {
        List<AssociadoDTO> associados = associadoService.getAssociados();
        assertNotNull(associados);
        assertTrue(associados.size() > 0);
    }

    @Test
    public void testGetAssociado() {
        Associado associado = new Associado();
        associado.setLogin("testLogin");
        associado.setSenha("testSenha");
        associado.setNome("Test Nome");
        associado.setCpf("12345678900");

        AssociadoDTO savedAssociado = associadoService.save(associado);
        AssociadoDTO foundAssociado = associadoService.getAssociado(savedAssociado.getId());

        assertNotNull(foundAssociado);
        assertEquals(savedAssociado.getId(), foundAssociado.getId());
    }

    @Test
    public void testUpdateAssociado() {
        Associado associado = new Associado();
        associado.setLogin("testLogin");
        associado.setSenha("testSenha");
        associado.setNome("Test Nome");
        associado.setCpf("12345678900");

        AssociadoDTO savedAssociado = associadoService.save(associado);

        Associado updatedAssociado = new Associado();
        updatedAssociado.setLogin("updatedLogin");
        updatedAssociado.setSenha("updatedSenha");
        updatedAssociado.setNome("Updated Nome");
        updatedAssociado.setCpf("12345678900");

        AssociadoDTO updatedAssociadoDTO = associadoService.update(savedAssociado.getId(), updatedAssociado);

        assertNotNull(updatedAssociadoDTO);
        assertEquals("updatedLogin", updatedAssociadoDTO.getLogin());
    }

    @Test
    public void testDeleteAssociado() {
        Associado associado = new Associado();
        associado.setLogin("testLogin");
        associado.setSenha("testSenha");
        associado.setNome("Test Nome");
        associado.setCpf("12345678900");

        AssociadoDTO savedAssociado = associadoService.save(associado);

        associadoService.delete(savedAssociado.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            associadoService.getAssociado(savedAssociado.getId());
        });
    }
}
