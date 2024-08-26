package com.example.atv2Dac.Inte.service;

import com.example.atv2Dac.dao.ClienteDAO;
import com.example.atv2Dac.dto.ClienteDTO;
import com.example.atv2Dac.model.Cliente;
import com.example.atv2Dac.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ClienteServiceTest {

        @Autowired
        private ClienteService clienteService;

        @Autowired
        private ClienteDAO clienteDAO;

        private Cliente cliente;

        @BeforeEach
        public void setUp() {
            cliente = new Cliente();
            cliente.setNome("Teste Cliente");
            cliente.setCnpjCPF("12345678901");
            cliente.setEmail("teste@cliente.com");
            cliente.setEstado("SP");
            cliente.setCep(Long.valueOf("01000000"));
            cliente.setBairro("Centro");
            cliente.setRua("Rua Teste");
            cliente.setNumero(Long.valueOf("123"));
        }

        @Test
        public void testGetClientes() {
            clienteDAO.save(cliente);
            List<ClienteDTO> clientes = clienteService.getClientes();
            assertThat(clientes).isNotEmpty();
        }

        @Test
        public void testGetCliente() {
            Cliente savedCliente = clienteDAO.save(cliente);
            ClienteDTO clienteDTO = clienteService.getCliente(savedCliente.getId());
            assertThat(clienteDTO).isNotNull();
        }

        @Test
        public void testSaveCliente() {
            ClienteDTO savedCliente = clienteService.save(cliente);
            assertThat(savedCliente).isNotNull();
            assertThat(savedCliente.getId()).isNotNull();
        }

        @Test
        public void testUpdateCliente() {
            Cliente savedCliente = clienteDAO.save(cliente);
            savedCliente.setNome("Teste Cliente Updated");
            ClienteDTO updatedCliente = clienteService.update(savedCliente.getId(), savedCliente);
            assertThat(updatedCliente.getNome()).isEqualTo("Teste Cliente Updated");
        }

        @Test
        public void testDeleteCliente() {
            Cliente savedCliente = clienteDAO.save(cliente);
            clienteService.delete(savedCliente.getId());
            Optional<Cliente> deletedCliente = clienteDAO.findById(savedCliente.getId());
            assertThat(deletedCliente).isEmpty();
        }

        @Test
        public void testGetClienteNotFound() {
            assertThrows(IllegalArgumentException.class, () -> {
                clienteService.getCliente(999L);
            });
        }

        @Test
        public void testUpdateClienteNotFound() {
            Cliente clienteToUpdate = new Cliente();
            clienteToUpdate.setNome("Teste Cliente Updated");
            clienteToUpdate.setCnpjCPF("12345678901");
            clienteToUpdate.setEmail("updated@cliente.com");
            clienteToUpdate.setEstado("SP");
            clienteToUpdate.setCep(Long.valueOf("01000000"));
            clienteToUpdate.setBairro("Centro");
            clienteToUpdate.setRua("Rua Atualizada");
            clienteToUpdate.setNumero(Long.valueOf("123"));

            assertThrows(RuntimeException.class, () -> {
                clienteService.update(999L, clienteToUpdate);
            });
        }

        @Test
        public void testSaveClienteWithId() {
            cliente.setId(1L);
            assertThrows(IllegalArgumentException.class, () -> {
                clienteService.save(cliente);
            });
        }

        @Test
        public void testDeleteClienteNotFound() {
            assertThrows(RuntimeException.class, () -> {
                clienteService.delete(999L);
            });
        }
}
