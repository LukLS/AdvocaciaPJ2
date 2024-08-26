package com.example.atv2Dac.controller;

import com.example.atv2Dac.dto.ProcessoDTO;
import com.example.atv2Dac.service.ProcessoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/processo")
@CrossOrigin(origins = "http://localhost:3000")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @GetMapping
    public List<ProcessoDTO> getAllProcessos() {
        return processoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessoDTO> getProcessoById(@PathVariable Long id) {
        Optional<ProcessoDTO> processo = processoService.findById(id);
        if (processo.isPresent()) {
            return ResponseEntity.ok(processo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ProcessoDTO createProcesso(@RequestBody ProcessoDTO processoDTO) {
        return processoService.save(processoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessoDTO> updateProcesso(
            @PathVariable Long id,
            @RequestParam(value = "files", required = false) MultipartFile[] files,
            @RequestPart("processoDTO") String processoDtoJson) {

        try {
            // Log do JSON recebido
            System.out.println("Recebido JSON: " + processoDtoJson);

            // Converte JSON para ProcessoDTO
            ObjectMapper objectMapper = new ObjectMapper();
            ProcessoDTO processoDTO = objectMapper.readValue(processoDtoJson, ProcessoDTO.class);

            // Log dos arquivos recebidos
            if (files != null) {
                System.out.println("Arquivos recebidos: " + files.length);
                for (MultipartFile file : files) {
                    System.out.println("Arquivo: " + file.getOriginalFilename());
                }
            }

            // Passa os dados para o serviço de atualização
            ProcessoDTO updatedProcesso = processoService.updateProcesso(id, files, processoDTO);
            return ResponseEntity.ok(updatedProcesso);
        } catch (IOException e) {
            e.printStackTrace(); // Log do stack trace
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            e.printStackTrace(); // Log do stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcesso(@PathVariable Long id) {
        if (processoService.findById(id).isPresent()) {
            processoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
