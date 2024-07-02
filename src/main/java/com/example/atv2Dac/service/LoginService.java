package com.example.atv2Dac.service;

import com.example.atv2Dac.dao.AdvogadoDAO;
import com.example.atv2Dac.dao.AssociadoDAO;
import com.example.atv2Dac.dto.AdvogadoDTO;
import com.example.atv2Dac.dto.AssociadoDTO;
import com.example.atv2Dac.model.Associado;
import com.example.atv2Dac.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    private AdvogadoDAO advogadoDAO;
    @Autowired
    private AssociadoDAO associadoDAO;

    @Autowired
    private JavaMailSender mailSender;

    public void sendCurrentPasswordEmail(String login) {
        String toEmail;
        String password;

        AdvogadoDTO advogadoDTO = advogadoDAO.findByLogin(login);
        if (advogadoDTO != null) {
            toEmail = advogadoDTO.getLogin();
            password = advogadoDTO.getSenha();
        } else {
            AssociadoDTO associadoDTO = associadoDAO.findByLogin(login);
            if (associadoDTO != null) {
                toEmail = associadoDTO.getLogin();
                password = associadoDTO.getSenha();
            } else {
                throw new IllegalArgumentException("Usuário não encontrado");
            }
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Sua senha");
        message.setText("Sua senha é: " + password + "\n\nPor favor, mantenha esta informação segura.");
        mailSender.send(message);
    }


    public List<AssociadoDTO> getAssociados() {
        List<AssociadoDTO> list = associadoDAO.findAll().stream().map(AssociadoDTO::create).collect(Collectors.toList());
        return list;
    }

    public AssociadoDTO getAssociado(Long id){
        Optional<Associado> associado = associadoDAO.findById(id);
        return associado.map(AssociadoDTO::create).orElseThrow(() -> new IllegalArgumentException());
    }

    public String loginADV(Login login) {

        AdvogadoDTO advogadoDTO = advogadoDAO.findByLogin(login.getLogin());
        if (advogadoDTO != null) {
            if (advogadoDTO.getSenha().equals(login.getSenha())) {
                return advogadoDTO.getLogin();
            } else {
                return null;
            }
        } else {
            AssociadoDTO associadoDTO = associadoDAO.findByLogin(login.getLogin());
            if (associadoDTO != null) {
                if (associadoDTO.getSenha().equals(login.getSenha())) {
                    return associadoDTO.getLogin();
                }
            } else {
                return null;
            }
        }
        return null;
    }
}
