package com.winesales.salesmanager.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteComprasDTOTest {

    private ClienteComprasDTO clienteComprasDTO;
    private CompraDTO compra1;
    private CompraDTO compra2;

    @BeforeEach
    public void setUp() {
        clienteComprasDTO = new ClienteComprasDTO();
        compra1 = new CompraDTO();
        compra2 = new CompraDTO();
    }

    @Test
    public void testGetAndSetNome() {
        assertNull(clienteComprasDTO.getNome());
        clienteComprasDTO.setNome("Carlos Almeida");
        assertEquals("Carlos Almeida", clienteComprasDTO.getNome());
    }

    @Test
    public void testGetAndSetCpf() {
        assertNull(clienteComprasDTO.getCpf());
        clienteComprasDTO.setCpf("12345678900");
        assertEquals("12345678900", clienteComprasDTO.getCpf());
    }

    @Test
    public void testGetAndSetCompras() {
        assertNull(clienteComprasDTO.getCompras());
        List<CompraDTO> compras = Arrays.asList(compra1, compra2);
        clienteComprasDTO.setCompras(compras);
        assertEquals(compras, clienteComprasDTO.getCompras());
        assertEquals(2, clienteComprasDTO.getCompras().size());
    }

    @Test
    public void testGetAndSetValorTotal() {
        assertNull(clienteComprasDTO.getValorTotal());
        BigDecimal valorTotal = new BigDecimal("150.75");
        clienteComprasDTO.setValorTotal(valorTotal);
        assertEquals(valorTotal, clienteComprasDTO.getValorTotal());
    }
}
