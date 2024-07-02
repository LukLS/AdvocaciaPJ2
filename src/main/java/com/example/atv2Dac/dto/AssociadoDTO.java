package com.example.atv2Dac.dto;

import com.example.atv2Dac.Permissao;
import com.example.atv2Dac.model.Associado;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AssociadoDTO {

    private Long id;

    private String login;

    private String senha;

    private String nome;

    private String cpf;

    private Permissao permissao;

    public AssociadoDTO() {
    }

    public AssociadoDTO(Associado associado) {
        this.id = associado.getId();
        this.login = associado.getLogin();
        this.senha = associado.getSenha();
        this.nome = associado.getNome();
        this.cpf = associado.getCpf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public static List<AssociadoDTO> create(List<Associado> associadosList) {
        return associadosList.stream().map(AssociadoDTO::new).collect(Collectors.toList());
    }

    public static AssociadoDTO create(Associado associado) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(associado, AssociadoDTO.class);
    }



}
