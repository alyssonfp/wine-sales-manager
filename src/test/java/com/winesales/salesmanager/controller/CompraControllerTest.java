package com.winesales.salesmanager.controller;

import com.winesales.salesmanager.dto.ClienteComprasDTO;
import com.winesales.salesmanager.dto.ClienteDTO;
import com.winesales.salesmanager.dto.ClienteRecomendacaoDTO;
import com.winesales.salesmanager.dto.CompraDTO;
import com.winesales.salesmanager.service.CompraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompraController.class)
public class CompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompraService compraService;

    private ClienteComprasDTO clienteComprasDTO;
    private ClienteDTO clienteDTO;
    private ClienteRecomendacaoDTO clienteRecomendacaoDTO;
    private CompraDTO compra1;
    private CompraDTO compra2;

    @BeforeEach
    public void setUp() {
        compra1 = new CompraDTO();
        compra1.setProdutoId(1L);
        compra1.setProdutoNome("Tinto");
        compra1.setQuantidade(2);
        compra1.setProdutoPreco(new BigDecimal("100.00"));
        compra1.setValorTotalCompra(new BigDecimal("200.00"));

        compra2 = new CompraDTO();
        compra2.setProdutoId(2L);
        compra2.setProdutoNome("Branco");
        compra2.setQuantidade(3);
        compra2.setProdutoPreco(new BigDecimal("50.00"));
        compra2.setValorTotalCompra(new BigDecimal("150.00"));

        clienteComprasDTO = new ClienteComprasDTO();
        clienteComprasDTO.setNome("João Silva");
        clienteComprasDTO.setCpf("12345678900");
        clienteComprasDTO.setCompras(Arrays.asList(compra1, compra2));
        clienteComprasDTO.setValorTotal(new BigDecimal("350.00"));

        clienteDTO = new ClienteDTO();
        clienteDTO.setId(1L);
        clienteDTO.setNome("João Silva");
        clienteDTO.setCpf("12345678900");
        clienteDTO.setCompras(Arrays.asList(compra1, compra2));

        clienteRecomendacaoDTO = new ClienteRecomendacaoDTO();
        clienteRecomendacaoDTO.setNome("João Silva");
        clienteRecomendacaoDTO.setCpf("12345678900");
        clienteRecomendacaoDTO.setTipoVinhoMaisComprado("Tinto");
        clienteRecomendacaoDTO.setSafra("2018");
    }

    @Test
    public void testGetCompras() throws Exception {
        when(compraService.findAllSortedByValor()).thenReturn(Collections.singletonList(clienteComprasDTO));

        mockMvc.perform(get("/api/compras")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome").value("João Silva"))
                .andExpect(jsonPath("$[0].valorTotal").value(350.00));
    }

    @Test
    public void testGetMaiorCompraPorAno() throws Exception {
        when(compraService.findMaiorCompraPorAno(2019)).thenReturn(clienteComprasDTO);

        mockMvc.perform(get("/api/maior-compra/2019")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João Silva"))
                .andExpect(jsonPath("$.valorTotal").value(350.00));
    }

    @Test
    public void testGetMaiorCompraPorAnoNotFound() throws Exception {
        when(compraService.findMaiorCompraPorAno(2019)).thenReturn(null);

        mockMvc.perform(get("/api/maior-compra/2019")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetClientesFieis() throws Exception {
        when(compraService.findTop3ClientesFieis()).thenReturn(Collections.singletonList(clienteDTO));

        mockMvc.perform(get("/api/clientes-fieis")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome").value("João Silva"))
                .andExpect(jsonPath("$[0].cpf").value("12345678900"));
    }

    @Test
    public void testGetRecomendacaoPorClienteETipo() throws Exception {
        when(compraService.findRecomendacaoPorClienteETipo()).thenReturn(Collections.singletonList(clienteRecomendacaoDTO));

        mockMvc.perform(get("/api/recomendacao/cliente/tipo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nome").value("João Silva"))
                .andExpect(jsonPath("$[0].tipoVinhoMaisComprado").value("Tinto"))
                .andExpect(jsonPath("$[0].safra").value("2018"));
    }
}
