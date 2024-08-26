package com.example.atv2Dac.dto;

import com.example.atv2Dac.model.Cliente;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTO {

    private Long id;

    private String nome;

    private String cnpjCPF;

    private String email;

    private Long numero;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cnpjCPF = cliente.getCnpjCPF();
        this.email = cliente.getEmail();
        this.numero = cliente.getNumero();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public static List<ClienteDTO> create(List<Cliente> clienteList) {
        return clienteList.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    public static ClienteDTO create(Cliente cliente) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(cliente, ClienteDTO.class);
    }


}
