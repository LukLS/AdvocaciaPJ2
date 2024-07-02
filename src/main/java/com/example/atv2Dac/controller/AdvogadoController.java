package com.example.atv2Dac.controller;

import com.example.atv2Dac.dto.AdvogadoDTO;
import com.example.atv2Dac.model.Advogado;
import com.example.atv2Dac.service.AdvogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advogado")
@CrossOrigin(origins = "http://localhost:3000")
public class AdvogadoController {

    @Autowired
    private AdvogadoService advogadoService;

    @PostMapping()
    public void save(@RequestBody Advogado advogado){
        advogadoService.save(advogado);
    }

    @GetMapping()
    public ResponseEntity getAdvogado(){
        List<AdvogadoDTO> advogado = advogadoService.getAdvogados();
        return ResponseEntity.ok(advogado);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAdvogado(@PathVariable("id")Long id){
        AdvogadoDTO advogado= advogadoService.getAdvogado(id);
        return ResponseEntity.ok(advogado);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,@RequestBody Advogado advogado){
        AdvogadoDTO advogadoUpdate = advogadoService.update(id, advogado);
        return ResponseEntity.ok(advogadoUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        advogadoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
