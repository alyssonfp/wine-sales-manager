package com.winesales.salesmanager.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteRecomendacaoDTOTest {

    private ClienteRecomendacaoDTO clienteRecomendacaoDTO;

    @BeforeEach
    public void setUp() {
        clienteRecomendacaoDTO = new ClienteRecomendacaoDTO();
    }

    @Test
    public void testGetAndSetNome() {
        assertNull(clienteRecomendacaoDTO.getNome());
        clienteRecomendacaoDTO.setNome("Maria Souza");
        assertEquals("Maria Souza", clienteRecomendacaoDTO.getNome());
    }

    @Test
    public void testGetAndSetCpf() {
        assertNull(clienteRecomendacaoDTO.getCpf());
        clienteRecomendacaoDTO.setCpf("12345678900");
        assertEquals("12345678900", clienteRecomendacaoDTO.getCpf());
    }

    @Test
    public void testGetAndSetTipoVinhoMaisComprado() {
        assertNull(clienteRecomendacaoDTO.getTipoVinhoMaisComprado());
        clienteRecomendacaoDTO.setTipoVinhoMaisComprado("Tinto");
        assertEquals("Tinto", clienteRecomendacaoDTO.getTipoVinhoMaisComprado());
    }

    @Test
    public void testGetAndSetSafra() {
        assertNull(clienteRecomendacaoDTO.getSafra());
        clienteRecomendacaoDTO.setSafra("2018");
        assertEquals("2018", clienteRecomendacaoDTO.getSafra());
    }
}
