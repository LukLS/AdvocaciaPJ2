package com.example.atv2Dac.dto;

import com.example.atv2Dac.model.Status;
import lombok.Data;

import java.io.File;
import java.util.Date;
import java.util.List;

@Data
public class ProcessoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private Date prazo;
    private String registroReceita;
    private List<String> tag;
    private Status status;
    private String descricaoStatus;
    private List<String> historico;
    private Long cliente;
    private List<File> files;

}