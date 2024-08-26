package com.example.atv2Dac.dto;

import com.example.atv2Dac.model.Advogado;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdvogadoDTO {

    private Long id;

    private String login;

    private String senha;

    private String nome;

    private String cpf;

    private String inscricao;

    private String estadoDeEmissao;

    private String filiacao;

    private Date dataDeNascimento;

    public AdvogadoDTO(Advogado advogado) {
        this.id = advogado.getId();
        this.login = advogado.getLogin();
        this.senha = advogado.getSenha();
        this.nome = advogado.getNome();
        this.cpf = advogado.getCpf();
        this.inscricao = advogado.getInscricao();
        this.estadoDeEmissao = advogado.getEstadoDeEmissao();
        this.filiacao = advogado.getFiliacao();
        this.dataDeNascimento = advogado.getDataDeNascimento();
    }

    public AdvogadoDTO() {
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

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getEstadoDeEmissao() {
        return estadoDeEmissao;
    }

    public void setEstadoDeEmissao(String estadoDeEmissao) {
        this.estadoDeEmissao = estadoDeEmissao;
    }

    public String getFiliacao() {
        return filiacao;
    }

    public void setFiliacao(String filiacao) {
        this.filiacao = filiacao;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public static List<AdvogadoDTO> create(List<Advogado> advogadoList) {
        return advogadoList.stream().map(AdvogadoDTO::new).collect(Collectors.toList());
    }

    public static AdvogadoDTO create(Advogado advogado) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(advogado, AdvogadoDTO.class);
    }


}
