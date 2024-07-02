package com.example.atv2Dac.dao;

import com.example.atv2Dac.dto.AdvogadoDTO;
import com.example.atv2Dac.model.Advogado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvogadoDAO extends JpaRepository<Advogado, Long> {

    AdvogadoDTO findByLogin(String login);
}
