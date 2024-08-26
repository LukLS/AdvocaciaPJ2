package com.example.atv2Dac.Inte.service;

import com.example.atv2Dac.dao.AdvogadoDAO;
import com.example.atv2Dac.dao.AssociadoDAO;
import com.example.atv2Dac.dto.AdvogadoDTO;
import com.example.atv2Dac.dto.AssociadoDTO;
import com.example.atv2Dac.model.Associado;
import com.example.atv2Dac.model.Login;
import com.example.atv2Dac.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @MockBean
    private AdvogadoDAO advogadoDAO;

    @MockBean
    private AssociadoDAO associadoDAO;

    @MockBean
    private JavaMailSender mailSender;

    private AdvogadoDTO advogadoDTO;
    private AssociadoDTO associadoDTO;

    @BeforeEach
    public void setUp() {
        advogadoDTO = new AdvogadoDTO();
        advogadoDTO.setLogin("advogado@example.com");
        advogadoDTO.setSenha("password123");

        associadoDTO = new AssociadoDTO();
        associadoDTO.setLogin("associado@example.com");
        associadoDTO.setSenha("password123");

        when(advogadoDAO.findByLogin("advogado@example.com")).thenReturn(advogadoDTO);
        when(associadoDAO.findByLogin("associado@example.com")).thenReturn(associadoDTO);
    }

    @Test
    public void testSendCurrentPasswordEmailForAdvogado() {
        loginService.sendCurrentPasswordEmail("advogado@example.com");
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testSendCurrentPasswordEmailForAssociado() {
        loginService.sendCurrentPasswordEmail("associado@example.com");
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testSendCurrentPasswordEmailUserNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            loginService.sendCurrentPasswordEmail("naoexiste@example.com");
        });
    }

    @Test
    public void testGetAssociados() {
        Associado associado = new Associado();
        associado.setId(1L);
        associado.setLogin("associado@example.com");
        associado.setSenha("password123");

        when(associadoDAO.findAll()).thenReturn(List.of(associado));
        List<AssociadoDTO> associados = loginService.getAssociados();
        assertThat(associados).isNotEmpty();
    }

    @Test
    public void testGetAssociado() {
        Associado associado = new Associado();
        associado.setId(1L);
        associado.setLogin("associado@example.com");
        associado.setSenha("password123");

        when(associadoDAO.findById(1L)).thenReturn(Optional.of(associado));
        AssociadoDTO associadoDTO = loginService.getAssociado(1L);
        assertThat(associadoDTO).isNotNull();
    }

    @Test
    public void testGetAssociadoNotFound() {
        when(associadoDAO.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {
            loginService.getAssociado(1L);
        });
    }

    @Test
    public void testLoginADVSuccess() {
        Login login = new Login();
        login.setLogin("advogado@example.com");
        login.setSenha("password123");

        String result = loginService.loginADV(login);
        assertThat(result).isEqualTo("advogado@example.com");
    }

    @Test
    public void testLoginADVWrongPassword() {
        Login login = new Login();
        login.setLogin("advogado@example.com");
        login.setSenha("wrongpassword");

        String result = loginService.loginADV(login);
        assertThat(result).isNull();
    }

    @Test
    public void testLoginADVUserNotFound() {
        Login login = new Login();
        login.setLogin("naoexiste@example.com");
        login.setSenha("password123");

        String result = loginService.loginADV(login);
        assertThat(result).isNull();
    }

}
