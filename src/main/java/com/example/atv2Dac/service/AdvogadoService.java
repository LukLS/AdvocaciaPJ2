package com.example.atv2Dac.service;

import com.example.atv2Dac.dao.AdvogadoDAO;
import com.example.atv2Dac.dto.AdvogadoDTO;
import com.example.atv2Dac.model.Advogado;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvogadoService {

    @Autowired
    private AdvogadoDAO advogadoDAO;

    @Autowired
    private ModelMapper modelMapper;

    public List<AdvogadoDTO> getAdvogados() {
       List<AdvogadoDTO> list = advogadoDAO.findAll().stream().map(AdvogadoDTO::create).collect(Collectors.toList());

        return list;
    }

    public AdvogadoDTO getAdvogado(Long id) {
        Advogado advogado = advogadoDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Advogado não encontrado"));
        return modelMapper.map(advogado, AdvogadoDTO.class);
    }


    public AdvogadoDTO save(Advogado advogado) {
        Assert.isNull(advogado.getId(), "Não foi possível inserir o registro");
        return AdvogadoDTO.create(advogadoDAO.save(advogado));
    }

    public AdvogadoDTO update(Long id,Advogado advogado) {

        Optional<Advogado> optional = advogadoDAO.findById(id);
        if (optional.isPresent()) {
            Advogado db = optional.get();
            db.setLogin(advogado.getLogin());
            db.setSenha(advogado.getSenha());
            db.setNome(advogado.getNome());
            db.setCpf(advogado.getCpf());
            db.setInscricao(advogado.getInscricao());
            db.setEstadoDeEmissao(advogado.getEstadoDeEmissao());
            db.setFiliacao(advogado.getFiliacao());
            db.setDataDeNascimento(advogado.getDataDeNascimento());
            advogadoDAO.save(db);
            return AdvogadoDTO.create(db);
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");

        }
    }

    public void delete(Long id){
        Optional<Advogado> advogado = advogadoDAO.findById(id);
        advogadoDAO.deleteById(id);
    }

}
