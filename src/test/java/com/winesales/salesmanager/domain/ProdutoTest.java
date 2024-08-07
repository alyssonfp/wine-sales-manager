package com.winesales.salesmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    private Produto produto;

    @BeforeEach
    public void setUp() {
        produto = new Produto();
    }

    @Test
    public void testSetAndGetCodigo() {
        produto.setCodigo(1L);
        assertEquals(1L, produto.getCodigo());
    }

    @Test
    public void testSetAndGetTipoVinho() {
        produto.setTipoVinho("Tinto");
        assertEquals("Tinto", produto.getTipoVinho());
    }

    @Test
    public void testSetAndGetPreco() {
        BigDecimal preco = new BigDecimal("49.99");
        produto.setPreco(preco);
        assertEquals(preco, produto.getPreco());
    }

    @Test
    public void testSetAndGetSafra() {
        produto.setSafra("2018");
        assertEquals("2018", produto.getSafra());
    }

    @Test
    public void testSetAndGetAnoCompra() {
        produto.setAnoCompra(2019);
        assertEquals(2019, produto.getAnoCompra());
    }

    @Test
    public void testProdutoConstructor() {
        Produto produtoComParametros = new Produto();
        produtoComParametros.setCodigo(1L);
        produtoComParametros.setTipoVinho("Branco");
        produtoComParametros.setPreco(new BigDecimal("59.99"));
        produtoComParametros.setSafra("2020");
        produtoComParametros.setAnoCompra(2021);

        assertEquals(1L, produtoComParametros.getCodigo());
        assertEquals("Branco", produtoComParametros.getTipoVinho());
        assertEquals(new BigDecimal("59.99"), produtoComParametros.getPreco());
        assertEquals("2020", produtoComParametros.getSafra());
        assertEquals(2021, produtoComParametros.getAnoCompra());
    }
}
