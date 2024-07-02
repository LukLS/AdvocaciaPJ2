package com.example.atv2Dac.controller;

import com.example.atv2Dac.dto.ClienteDTO;
import com.example.atv2Dac.model.Cliente;
import com.example.atv2Dac.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {


        @Autowired
        private ClienteService clienteService;

        @PostMapping()
        public void save(@RequestBody Cliente cliente){
            clienteService.save(cliente);
        }

        @GetMapping()
        public ResponseEntity getClientes(){
            List<ClienteDTO> clientes = clienteService.getClientes();
            return ResponseEntity.ok(clientes);
        }

        @GetMapping("/{id}")
        public ResponseEntity getCliente(@PathVariable("id")Long id){
            ClienteDTO cliente= clienteService.getCliente(id);
            return ResponseEntity.ok(cliente);
        }

        @PutMapping("/{id}")
        public ResponseEntity update(@PathVariable("id") Long id,@RequestBody Cliente cliente){
            ClienteDTO clienteUpdate = clienteService.update(id, cliente);
            return ResponseEntity.ok(clienteUpdate);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity delete(@PathVariable("id") Long id) {
            clienteService.delete(id);
            return ResponseEntity.ok().build();
        }



}
