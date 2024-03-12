package com.example.atv2Dac.dto;

import com.example.atv2Dac.model.Lutador;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class LutadorDTO {

    private Long id;

    private String nome;

    private String cartel;


    public LutadorDTO() {

    }

    public LutadorDTO (Lutador lutador) {
        this.id = lutador.getId();
        this.nome = lutador.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCartel() {
        return cartel;
    }

    public void setCartel(String cartel) {
        this.cartel = cartel;
    }

    public static List<LutadorDTO> create(List<Lutador> lutadorList) {
        return lutadorList.stream().map(LutadorDTO::new).collect(Collectors.toList());
    }

    public static LutadorDTO create(Lutador lutador) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(lutador, LutadorDTO.class);
    }

}
