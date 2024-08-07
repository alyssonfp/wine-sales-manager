package com.winesales.salesmanager.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoDTOTest {

    private ProdutoDTO produtoDTO;

    @BeforeEach
    public void setUp() {
        produtoDTO = new ProdutoDTO();
    }

    @Test
    public void testSetAndGetCodigo() {
        produtoDTO.setCodigo(1L);
        assertEquals(1L, produtoDTO.getCodigo());
    }

    @Test
    public void testSetAndGetTipoVinho() {
        produtoDTO.setTipoVinho("Tinto");
        assertEquals("Tinto", produtoDTO.getTipoVinho());
    }

    @Test
    public void testSetAndGetPreco() {
        BigDecimal preco = new BigDecimal("49.99");
        produtoDTO.setPreco(preco);
        assertEquals(preco, produtoDTO.getPreco());
    }

    @Test
    public void testSetAndGetSafra() {
        produtoDTO.setSafra("2018");
        assertEquals("2018", produtoDTO.getSafra());
    }

    @Test
    public void testSetAndGetAnoCompra() {
        produtoDTO.setAnoCompra(2019);
        assertEquals(2019, produtoDTO.getAnoCompra());
    }
}
