package com.example.atv2Dac.service;

import com.example.atv2Dac.dao.ProcessoDAO;
import com.example.atv2Dac.dto.ProcessoDTO;
import com.example.atv2Dac.model.Processo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoDAO processoRepository;

    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    public ProcessoService() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Não foi possível criar o diretório para armazenamento dos arquivos.", ex);
        }
    }

    public List<ProcessoDTO> findAll() {
        return processoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ProcessoDTO> findById(Long id) {
        return processoRepository.findById(id).map(this::convertToDTO);
    }

    public ProcessoDTO save(ProcessoDTO processoDTO) {
        Processo processo = convertToEntity(processoDTO);
        Processo savedProcesso = processoRepository.save(processo);
        return convertToDTO(savedProcesso);
    }

    public ProcessoDTO updateProcesso(Long id, MultipartFile[] files, ProcessoDTO processoDTO) {
        // Verificar se o processo existe
        Optional<Processo> processoExistenteOpt = processoRepository.findById(id);
        if (processoExistenteOpt.isEmpty()) {
            throw new RuntimeException("Processo não encontrado com ID: " + id);
        }

        Processo processoExistente = processoExistenteOpt.get();

        // Atualizar todos os campos do processo conforme o DTO
        processoExistente.setTitulo(processoDTO.getTitulo());
        processoExistente.setDescricao(processoDTO.getDescricao());
        processoExistente.setPrazo(processoDTO.getPrazo());
        processoExistente.setRegistroReceita(processoDTO.getRegistroReceita());
        processoExistente.setTag(processoDTO.getTag());
        processoExistente.setStatus(processoDTO.getStatus());
        processoExistente.setHistorico(processoDTO.getHistorico());
        processoExistente.setCliente(processoDTO.getCliente());
        // Outros campos de atualização aqui

        // Adicionar ou atualizar arquivos
        if (files != null) {
            if (processoExistente.getFiles() == null) {
                processoExistente.setFiles(new ArrayList<>());
            }

            for (MultipartFile file : files) {
                try {
                    // Salvar o arquivo usando o método storeFile
                    String filePath = storeFile(file);

                    // Verificar se o caminho do arquivo já existe na lista de arquivos
                    boolean fileAlreadyExists = processoExistente.getFiles().stream()
                            .anyMatch(existingFile -> existingFile.getPath().equals(filePath));

                    if (!fileAlreadyExists) {
                        // Adicionar o caminho do arquivo à entidade
                        processoExistente.getFiles().add(new File(filePath));
                        System.out.println("Arquivo salvo em: " + filePath);
                    } else {
                        System.out.println("Arquivo já existente: " + filePath);
                    }
                } catch (IOException e) {
                    System.err.println("Erro ao salvar o arquivo " + file.getOriginalFilename() + ": " + e.getMessage());
                    throw new RuntimeException("Erro ao salvar o arquivo " + file.getOriginalFilename(), e);
                }
            }
        }

        // Salvar as alterações no repositório
        processoRepository.save(processoExistente);

        // Retornar o DTO atualizado
        return convertToDTO(processoExistente);
    }




    public void deleteById(Long id) {
        if (!processoRepository.existsById(id)) {
            throw new EntityNotFoundException("Processo não encontrado para o ID " + id);
        }
        processoRepository.deleteById(id);
    }

    private String storeFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path targetLocation = this.fileStorageLocation.resolve(fileName);

        // Verificar se o diretório de destino existe e criar se necessário
        if (!Files.exists(this.fileStorageLocation)) {
            Files.createDirectories(this.fileStorageLocation);
        }

        // Tente salvar o arquivo
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return targetLocation.toString();
    }


    private ProcessoDTO convertToDTO(Processo processo) {
        ProcessoDTO dto = new ProcessoDTO();
        dto.setId(processo.getId());
        dto.setTitulo(processo.getTitulo());
        dto.setDescricao(processo.getDescricao());
        dto.setPrazo(processo.getPrazo());
        dto.setRegistroReceita(processo.getRegistroReceita());
        dto.setTag(processo.getTag());
        dto.setStatus(processo.getStatus());
        dto.setHistorico(processo.getHistorico());
        dto.setCliente(processo.getCliente() != null ? processo.getCliente() : null);
        dto.setFiles(processo.getFiles());
        return dto;
    }

    public Processo convertToEntity(ProcessoDTO processoDTO) {
        Processo processo = new Processo();
        processo.setId(processoDTO.getId());
        processo.setTitulo(processoDTO.getTitulo());
        processo.setDescricao(processoDTO.getDescricao());
        processo.setPrazo(processoDTO.getPrazo());
        processo.setRegistroReceita(processoDTO.getRegistroReceita());
        processo.setTag(processoDTO.getTag());
        processo.setStatus(processoDTO.getStatus());
        processo.setHistorico(processoDTO.getHistorico());
        processo.setCliente(processoDTO.getCliente());
        processo.setFiles(processoDTO.getFiles());
        return processo;
    }
}