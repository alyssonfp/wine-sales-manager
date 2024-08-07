package com.winesales.salesmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Cliente cliente;
    private List<Compra> compras;

    @BeforeEach
    public void setUp() {
        compras = new ArrayList<>();
        Compra compra1 = new Compra();
        compra1.setId(1L);
        compras.add(compra1);

        cliente = new Cliente("João Silva", "12345678900", compras);
    }

    @Test
    public void testClienteConstructor() {
        assertNotNull(cliente);
        assertEquals("João Silva", cliente.getNome());
        assertEquals("12345678900", cliente.getCpf());
        assertEquals(compras, cliente.getCompras());
    }

    @Test
    public void testGetAndSetId() {
        cliente.setId(1L);
        assertEquals(1L, cliente.getId());
    }

    @Test
    public void testGetAndSetNome() {
        cliente.setNome("Maria Oliveira");
        assertEquals("Maria Oliveira", cliente.getNome());
    }

    @Test
    public void testGetAndSetCpf() {
        cliente.setCpf("09876543211");
        assertEquals("09876543211", cliente.getCpf());
    }

    @Test
    public void testGetAndSetCompras() {
        List<Compra> novasCompras = new ArrayList<>();
        Compra compra2 = new Compra();
        compra2.setId(2L);
        novasCompras.add(compra2);

        cliente.setCompras(novasCompras);
        assertEquals(novasCompras, cliente.getCompras());
    }

    @Test
    public void testAddCompra() {
        Compra compra3 = new Compra();
        compra3.setId(3L);
        cliente.getCompras().add(compra3);

        assertEquals(2, cliente.getCompras().size());
        assertTrue(cliente.getCompras().contains(compra3));
    }
}