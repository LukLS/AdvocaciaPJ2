package com.example.atv2Dac.model;


import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
public class Advogado extends Cliente{

    private String inscricao;

    private String estadoDeEmissao;

    private String filiacao;

    private Date dataDeNascimento;

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

}
