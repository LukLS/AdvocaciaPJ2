package com.example.atv2Dac.service;

import com.example.atv2Dac.dao.AdvogadoDAO;
import com.example.atv2Dac.dao.AssociadoDAO;
import com.example.atv2Dac.dto.AssociadoDTO;
import com.example.atv2Dac.model.Associado;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoDAO associadoDAO;

    @Autowired
    private AdvogadoDAO advogadoDAO;

    public List<AssociadoDTO> getAssociados() {
        List<AssociadoDTO> list = associadoDAO.findAll().stream().map(AssociadoDTO::create).collect(Collectors.toList());
        return list;
    }

    public AssociadoDTO getAssociado(Long id){
        Optional<Associado> associado = associadoDAO.findById(id);
        return associado.map(AssociadoDTO::create).orElseThrow(() -> new IllegalArgumentException());
    }

    public AssociadoDTO save(Associado associado) {
        Assert.isNull(associado.getId(),"Não foi possível inserir o registro");
        return AssociadoDTO.create(associadoDAO.save(associado));
    }

    public AssociadoDTO update(Long id,Associado associado) {

        Optional<Associado> optional = associadoDAO.findById(id);
        if (optional.isPresent()) {
            Associado db = optional.get();
            db.setLogin(associado.getLogin());
            db.setSenha(associado.getSenha());
            db.setNome(associado.getNome());
            db.setCpf(associado.getCpf());
            db.setDataDeNascimento(associado.getDataDeNascimento());
            associadoDAO.save(db);
            return AssociadoDTO.create(db);
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id){
        Optional<Associado> associado = associadoDAO.findById(id);
        associadoDAO.deleteById(id);
    }

}
