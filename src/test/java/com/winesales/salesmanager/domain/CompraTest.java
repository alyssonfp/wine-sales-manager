package com.winesales.salesmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompraTest {

    private Cliente cliente;
    private Produto produto;
    private Compra compra;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("João Silva", "12345678900", null);
        produto = new Produto();
        produto.setCodigo(1L);
        produto.setTipoVinho("Tinto");
        produto.setPreco(new java.math.BigDecimal("29.99"));
        produto.setSafra("2018");
        produto.setAnoCompra(2019);

        compra = new Compra(cliente, produto, 5);
    }

    @Test
    public void testCompraConstructor() {
        assertEquals(cliente, compra.getCliente());
        assertEquals(produto, compra.getProduto());
        assertEquals(5, compra.getQuantidade());
    }

    @Test
    public void testSetCliente() {
        Cliente novoCliente = new Cliente("Maria Souza", "98765432100", null);
        compra.setCliente(novoCliente);
        assertEquals(novoCliente, compra.getCliente());
    }

    @Test
    public void testSetProduto() {
        Produto novoProduto = new Produto();
        novoProduto.setCodigo(2L);
        novoProduto.setTipoVinho("Branco");
        novoProduto.setPreco(new java.math.BigDecimal("49.99"));
        novoProduto.setSafra("2019");
        novoProduto.setAnoCompra(2020);

        compra.setProduto(novoProduto);
        assertEquals(novoProduto, compra.getProduto());
    }

    @Test
    public void testSetQuantidade() {
        compra.setQuantidade(10);
        assertEquals(10, compra.getQuantidade());
    }

    @Test
    public void testCodigoProdutoTransientField() {
        compra.setCodigoProduto(1L);
        assertEquals(1L, compra.getCodigoProduto());
    }
}
