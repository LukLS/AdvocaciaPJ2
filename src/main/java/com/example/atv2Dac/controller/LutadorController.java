package com.example.atv2Dac.controller;

import com.example.atv2Dac.dto.LutadorDTO;
import com.example.atv2Dac.model.Lutador;
import com.example.atv2Dac.service.LutadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lutador")
public class LutadorController {
    @Autowired
    private LutadorService lutadorService;

    @PostMapping()
    public void save(@RequestBody Lutador lutador){
        lutadorService.save(lutador);
    }

    @GetMapping()
    public ResponseEntity getLutadores(){
       List<LutadorDTO> lutadores = lutadorService.getLutadores();
        return ResponseEntity.ok(lutadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity getLutador(@PathVariable("id")Long id){
        LutadorDTO lutador= lutadorService.getLutador(id);
        return ResponseEntity.ok(lutador);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,@RequestBody Lutador lutador){
        LutadorDTO lutadorUpdate = lutadorService.update(id, lutador);
        return ResponseEntity.ok(lutadorUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
       lutadorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
