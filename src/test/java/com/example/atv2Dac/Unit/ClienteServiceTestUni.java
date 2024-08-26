package com.example.atv2Dac.Unit;

import com.example.atv2Dac.dao.ClienteDAO;
import com.example.atv2Dac.dto.ClienteDTO;
import com.example.atv2Dac.model.Cliente;
import com.example.atv2Dac.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTestUni {

    @Mock
    private ClienteDAO clienteDAO;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente 1");
        cliente.setCnpjCPF("12345678901");
        cliente.setEmail("cliente1@example.com");
        cliente.setEstado("SP");
        cliente.setCep(Long.valueOf("12345678"));
        cliente.setBairro("Centro");
        cliente.setRua("Rua 1");
        cliente.setNumero(Long.valueOf("123"));

        clienteDTO = ClienteDTO.create(cliente);
    }

    @Test
    public void testGetClientes() {
        when(clienteDAO.findAll()).thenReturn(Arrays.asList(cliente));

        List<ClienteDTO> result = clienteService.getClientes();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNome()).isEqualTo("Cliente 1");
        verify(clienteDAO, times(1)).findAll();
    }

    @Test
    public void testGetCliente() {
        when(clienteDAO.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteDTO result = clienteService.getCliente(1L);

        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Cliente 1");
        verify(clienteDAO, times(1)).findById(1L);
    }

    @Test
    public void testGetClienteNotFound() {
        when(clienteDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            clienteService.getCliente(1L);
        });
        verify(clienteDAO, times(1)).findById(1L);
    }

    @Test
    public void testSaveCliente() {
        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Novo Cliente");
        novoCliente.setCnpjCPF("09876543210");
        novoCliente.setEmail("novocliente@example.com");
        novoCliente.setEstado("RJ");
        novoCliente.setCep(Long.valueOf("98765432"));
        novoCliente.setBairro("Bairro Novo");
        novoCliente.setRua("Rua Nova");
        novoCliente.setNumero(Long.valueOf("456"));

        when(clienteDAO.save(any(Cliente.class))).thenReturn(novoCliente);

        ClienteDTO result = clienteService.save(novoCliente);

        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Novo Cliente");
        verify(clienteDAO, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testSaveClienteComId() {
        Cliente clienteComId = new Cliente();
        clienteComId.setId(2L);
        clienteComId.setNome("Cliente Com Id");

        assertThrows(IllegalArgumentException.class, () -> {
            clienteService.save(clienteComId);
        });
    }

    @Test
    public void testUpdateCliente() {
        when(clienteDAO.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteDAO.save(any(Cliente.class))).thenReturn(cliente);

        cliente.setNome("Cliente Atualizado");

        ClienteDTO result = clienteService.update(1L, cliente);

        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo("Cliente Atualizado");
        verify(clienteDAO, times(1)).findById(1L);
        verify(clienteDAO, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testUpdateClienteNotFound() {
        when(clienteDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            clienteService.update(1L, cliente);
        });
        verify(clienteDAO, times(1)).findById(1L);
    }

    @Test
    public void testDeleteCliente() {
        when(clienteDAO.findById(1L)).thenReturn(Optional.of(cliente));

        clienteService.delete(1L);

        verify(clienteDAO, times(1)).findById(1L);
        verify(clienteDAO, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteClienteNotFound() {
        when(clienteDAO.findById(1L)).thenReturn(Optional.empty());

        clienteService.delete(1L);

        verify(clienteDAO, times(1)).findById(1L);
        verify(clienteDAO, times(1)).deleteById(1L);
    }
}
