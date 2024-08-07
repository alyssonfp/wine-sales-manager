package com.winesales.salesmanager.repository;

import com.winesales.salesmanager.domain.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testSaveAndFindById() {
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setCpf("12345678900");

        Cliente savedCliente = clienteRepository.save(cliente);

        Optional<Cliente> foundCliente = clienteRepository.findById(savedCliente.getId());
        assertTrue(foundCliente.isPresent());
        assertEquals("João Silva", foundCliente.get().getNome());
        assertEquals("12345678900", foundCliente.get().getCpf());
    }

    @Test
    public void testFindAll() {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("João Silva");
        cliente1.setCpf("12345678900");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria Souza");
        cliente2.setCpf("09876543211");

        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);

        List<Cliente> clientes = clienteRepository.findAll();
        assertEquals(2, clientes.size());
    }

    @Test
    public void testDeleteById() {
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setCpf("12345678900");

        Cliente savedCliente = clienteRepository.save(cliente);
        clienteRepository.deleteById(savedCliente.getId());

        Optional<Cliente> foundCliente = clienteRepository.findById(savedCliente.getId());
        assertFalse(foundCliente.isPresent());
    }
}
