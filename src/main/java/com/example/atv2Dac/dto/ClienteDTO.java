package com.example.atv2Dac.dto;

import com.example.atv2Dac.model.Cliente;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTO {

    private Long id;

    private String nome;

    private String cnpjCPF;

    private String endereco;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cnpjCPF = cliente.getCnpjCPF();
        this.endereco = cliente.getEndereco();
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

    public String getCnpjCPF() {
        return cnpjCPF;
    }

    public void setCnpjCPF(String cnpjCPF) {
        this.cnpjCPF = cnpjCPF;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public static List<ClienteDTO> create(List<Cliente> clienteList) {
        return clienteList.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    public static ClienteDTO create(Cliente cliente) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(cliente, ClienteDTO.class);
    }


}
