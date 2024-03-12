package com.example.atv2Dac.service;

import com.example.atv2Dac.dao.ClienteDAO;
import com.example.atv2Dac.dto.ClienteDTO;
import com.example.atv2Dac.model.Cliente;
import com.example.atv2Dac.model.Lutador;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    public List<ClienteDTO> getClientes() {
        List<ClienteDTO> list = clienteDAO.findAll().stream().map(ClienteDTO::create).collect(Collectors.toList());
        return list;
    }

    public ClienteDTO getCliente(Long id){
        Optional<Cliente> cliente = clienteDAO.findById(id);
        return cliente.map(ClienteDTO::create).orElseThrow(() -> new IllegalArgumentException());
    }

    public ClienteDTO save(Cliente cliente) {
        Assert.isNull(cliente.getId(),"Não foi possível inserir o registro");
        return ClienteDTO.create(clienteDAO.save(cliente));
    }

    public ClienteDTO update(Long id,Cliente cliente) {

        Optional<Cliente> optional = clienteDAO.findById(id);
        if (optional.isPresent()) {
            Cliente db = optional.get();
            db.setNome(cliente.getNome());
            db.setCnpjCPF(cliente.getCnpjCPF());
            db.setEndereco(cliente.getEndereco());
            clienteDAO.save(db);
            return ClienteDTO.create(db);
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");

        }
    }

    public void delete(Long id){
        Optional<Cliente> cliente = clienteDAO.findById(id);
        clienteDAO.deleteById(id);
    }



}
