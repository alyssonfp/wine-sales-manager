package com.winesales.salesmanager.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDTOTest {

    private ClienteDTO clienteDTO;
    private CompraDTO compra1;
    private CompraDTO compra2;

    @BeforeEach
    public void setUp() {
        clienteDTO = new ClienteDTO();
        compra1 = new CompraDTO();
        compra2 = new CompraDTO();
    }

    @Test
    public void testGetId() {
        assertNull(clienteDTO.getId());
    }

    @Test
    public void testSetAndGetNome() {
        clienteDTO.setNome("João Silva");
        assertEquals("João Silva", clienteDTO.getNome());
    }

    @Test
    public void testGetCpf() {
        assertNull(clienteDTO.getCpf());
    }

    @Test
    public void testSetAndGetCompras() {
        List<CompraDTO> compras = Arrays.asList(compra1, compra2);
        clienteDTO.setCompras(compras);
        assertEquals(compras, clienteDTO.getCompras());
        assertEquals(2, clienteDTO.getCompras().size());
    }
}