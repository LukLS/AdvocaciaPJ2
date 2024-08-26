package com.example.atv2Dac.controller;

import com.example.atv2Dac.model.Login;
import com.example.atv2Dac.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Login login){
        String user = loginService.loginADV(login); // Supondo que loginADV retorne um objeto User
        if (user == null){
            System.out.println("ERRO AQ "+login);
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        String token = TokenProvider.generateToken(user);
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("token", token);
        System.out.println("token é "+token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/password")
    public ResponseEntity<String> sendCurrentPasswordEmail(@RequestBody Map<String, String> request) {
        String login = request.get("login");
        if (login == null || login.isEmpty()) {
            return ResponseEntity.badRequest().body("Login não fornecido");
        }

        try {
            loginService.sendCurrentPasswordEmail(login);
            return ResponseEntity.ok("Senha enviada com sucesso para o seu email");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar o email");
        }
    }

}
