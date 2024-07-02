package com.example.atv2Dac.dao;

import com.example.atv2Dac.dto.AssociadoDTO;
import com.example.atv2Dac.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoDAO extends JpaRepository<Associado, Long> {
    AssociadoDTO findByLogin(String login);
}
