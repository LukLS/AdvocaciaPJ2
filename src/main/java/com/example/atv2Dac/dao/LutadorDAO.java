package com.example.atv2Dac.dao;

import com.example.atv2Dac.model.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LutadorDAO extends JpaRepository<Lutador, Long> {

}
