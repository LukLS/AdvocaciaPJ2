package com.example.atv2Dac.model;

import lombok.Data;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    @Temporal(TemporalType.DATE)
    private Date prazo;

    private String registroReceita;

    @ElementCollection
    private List<String> tag = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection
    private List<String> historico = new ArrayList<>();

    private Long cliente;

    @ElementCollection
    private List<File> files = new ArrayList<>();

    public Processo(String titulo, String descricao, Date prazo, String registroReceita,List<String> tag ,Long cliente, Status status, List<String> historico) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.registroReceita = registroReceita;
        this.tag = tag;
        this.status = status;
        this.historico = historico;
        this.cliente = cliente;
        this.files = files != null ? files : new ArrayList<>();
    }

    public Processo() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getPrazo() {
        return prazo;
    }

    public String getRegistroReceita() {
        return registroReceita;
    }

    public List<String> getTag() {
        return tag;
    }
    public Status getStatus() {
        return status;
    }

    public List<String> getHistorico() {
        return historico;
    }

    public Long getCliente() {
        return cliente;
    }

    public List<File> getFiles() {
        return files;
    }

}
