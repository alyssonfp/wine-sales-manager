package com.winesales.salesmanager.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompraDTOTest {

    @Test
    public void testGetAndSetId() {
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setProdutoId(1L);
        assertEquals(1L, compraDTO.getProdutoId());
    }

    @Test
    public void testGetAndSetProdutoId() {
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setProdutoId(2L);
        assertEquals(2L, compraDTO.getProdutoId());
    }

    @Test
    public void testGetAndSetQuantidade() {
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setQuantidade(5);
        assertEquals(5, compraDTO.getQuantidade());
    }

    @Test
    public void testSettersAndGetters() {
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setProdutoId(2L);
        compraDTO.setQuantidade(5);

        assertEquals(2L, compraDTO.getProdutoId());
        assertEquals(5, compraDTO.getQuantidade());
    }
}
