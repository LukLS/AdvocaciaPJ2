package com.example.atv2Dac.Inte;

import com.example.atv2Dac.dao.AdvogadoDAO;
import com.example.atv2Dac.dto.AdvogadoDTO;
import com.example.atv2Dac.model.Advogado;
import com.example.atv2Dac.service.AdvogadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class AdvogadoServiceTest {

    @Autowired
    private AdvogadoService advogadoService;

    @Autowired
    private AdvogadoDAO advogadoDAO;

    @Autowired
    private ModelMapper modelMapper;

    private Date convertToDateViaInstant(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    @Test
    public void testSaveAdvogado() {
        Advogado advogado = new Advogado();
        advogado.setLogin("testLogin");
        advogado.setSenha("testSenha");
        advogado.setNome("Test Nome");
        advogado.setCpf("12345678900");
        advogado.setInscricao("12345");
        advogado.setEstadoDeEmissao("SP");
        advogado.setFiliacao("Test Filiacao");
        advogado.setDataDeNascimento(convertToDateViaInstant(LocalDate.of(1980, 1, 1)));

        AdvogadoDTO savedAdvogado = advogadoService.save(advogado);

        assertNotNull(savedAdvogado.getId());
        assertEquals("testLogin", savedAdvogado.getLogin());
    }

    @Test
    public void testGetAdvogados() {
        List<AdvogadoDTO> advogados = advogadoService.getAdvogados();
        assertNotNull(advogados);
        assertTrue(advogados.size() > 0);
    }

    @Test
    public void testGetAdvogado() {
        Advogado advogado = new Advogado();
        advogado.setLogin("testLogin");
        advogado.setSenha("testSenha");
        advogado.setNome("Test Nome");
        advogado.setCpf("12345678900");
        advogado.setInscricao("12345");
        advogado.setEstadoDeEmissao("SP");
        advogado.setFiliacao("Test Filiacao");
        advogado.setDataDeNascimento(convertToDateViaInstant(LocalDate.of(1980, 1, 1)));

        AdvogadoDTO savedAdvogado = advogadoService.save(advogado);
        AdvogadoDTO foundAdvogado = advogadoService.getAdvogado(savedAdvogado.getId());

        assertNotNull(foundAdvogado);
        assertEquals(savedAdvogado.getId(), foundAdvogado.getId());
    }

    @Test
    public void testUpdateAdvogado() {
        Advogado advogado = new Advogado();
        advogado.setLogin("testLogin");
        advogado.setSenha("testSenha");
        advogado.setNome("Test Nome");
        advogado.setCpf("12345678900");
        advogado.setInscricao("12345");
        advogado.setEstadoDeEmissao("SP");
        advogado.setFiliacao("Test Filiacao");
        advogado.setDataDeNascimento(convertToDateViaInstant(LocalDate.of(1980, 1, 1)));

        AdvogadoDTO savedAdvogado = advogadoService.save(advogado);

        Advogado updatedAdvogado = new Advogado();
        updatedAdvogado.setLogin("updatedLogin");
        updatedAdvogado.setSenha("updatedSenha");
        updatedAdvogado.setNome("Updated Nome");
        updatedAdvogado.setCpf("12345678900");
        updatedAdvogado.setInscricao("12345");
        updatedAdvogado.setEstadoDeEmissao("SP");
        updatedAdvogado.setFiliacao("Updated Filiacao");
        advogado.setDataDeNascimento(convertToDateViaInstant(LocalDate.of(1980, 1, 1)));

        AdvogadoDTO updatedAdvogadoDTO = advogadoService.update(savedAdvogado.getId(), updatedAdvogado);

        assertNotNull(updatedAdvogadoDTO);
        assertEquals("updatedLogin", updatedAdvogadoDTO.getLogin());
    }

    @Test
    public void testDeleteAdvogado() {
        Advogado advogado = new Advogado();
        advogado.setLogin("testLogin");
        advogado.setSenha("testSenha");
        advogado.setNome("Test Nome");
        advogado.setCpf("12345678900");
        advogado.setInscricao("12345");
        advogado.setEstadoDeEmissao("SP");
        advogado.setFiliacao("Test Filiacao");
        advogado.setDataDeNascimento(convertToDateViaInstant(LocalDate.of(1980, 1, 1)));

        AdvogadoDTO savedAdvogado = advogadoService.save(advogado);

        advogadoService.delete(savedAdvogado.getId());

        assertThrows(RuntimeException.class, () -> {
            advogadoService.getAdvogado(savedAdvogado.getId());
        });
    }
}
