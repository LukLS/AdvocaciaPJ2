package com.example.atv2Dac.components;

import com.example.atv2Dac.dao.AdvogadoDAO;
import com.example.atv2Dac.model.Advogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Autowired
    private AdvogadoDAO advogadoDAO;

    @Override
    public void run(String... args) throws Exception {
        if (advogadoDAO.findByLogin(adminEmail) == null) {
            Advogado admin = new Advogado();
            admin.setNome("Administrador");
            admin.setLogin(adminEmail);
            admin.setSenha(adminPassword); // Em produção, você usaria o BCrypt aqui

            advogadoDAO.save(admin);
            System.out.println(">>> ADMIN PADRÃO CRIADO COM SUCESSO! <<<");
        }
    }
}