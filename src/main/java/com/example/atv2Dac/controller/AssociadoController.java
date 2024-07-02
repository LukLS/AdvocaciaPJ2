package com.example.atv2Dac.controller;

import com.example.atv2Dac.dto.AssociadoDTO;
import com.example.atv2Dac.model.Associado;
import com.example.atv2Dac.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/associado")
@CrossOrigin(origins = "http://localhost:3000")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @PostMapping()
    public void save(@RequestBody Associado associado){
        associadoService.save(associado);
    }

    @GetMapping()
    public ResponseEntity getAssociados(){
        List<AssociadoDTO> associado = associadoService.getAssociados();
        return ResponseEntity.ok(associado);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAssociado(@PathVariable("id")Long id){
        AssociadoDTO associado= associadoService.getAssociado(id);
        return ResponseEntity.ok(associado);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,@RequestBody Associado associado){
        AssociadoDTO associadoUpdate = associadoService.update(id, associado);
        return ResponseEntity.ok(associadoUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        associadoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
