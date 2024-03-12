package com.example.atv2Dac.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
/*

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


 */
}
