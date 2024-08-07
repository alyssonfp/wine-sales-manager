package com.winesales.salesmanager.service;

import com.winesales.salesmanager.domain.Cliente;
import com.winesales.salesmanager.domain.Compra;
import com.winesales.salesmanager.domain.Produto;
import com.winesales.salesmanager.dto.ClienteComprasDTO;
import com.winesales.salesmanager.dto.ClienteDTO;
import com.winesales.salesmanager.dto.ClienteRecomendacaoDTO;
import com.winesales.salesmanager.repository.ClienteRepository;
import com.winesales.salesmanager.repository.CompraRepository;
import com.winesales.salesmanager.repository.ProdutoRepository;
import com.winesales.salesmanager.service.impl.CompraServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompraServiceImplTest {

    private CompraServiceImpl compraService;
    private CompraRepository compraRepository;
    private ProdutoRepository produtoRepository;
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        compraRepository = Mockito.mock(CompraRepository.class);
        produtoRepository = Mockito.mock(ProdutoRepository.class);
        clienteRepository = Mockito.mock(ClienteRepository.class);

        compraService = new CompraServiceImpl(compraRepository, produtoRepository, clienteRepository);
    }

    @Test
    public void testFindAllSortedByValor() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setCpf("12345678900");

        Produto produto1 = new Produto();
        produto1.setCodigo(1L);
        produto1.setPreco(new BigDecimal("100.00"));

        Produto produto2 = new Produto();
        produto2.setCodigo(2L);
        produto2.setPreco(new BigDecimal("200.00"));

        Compra compra1 = new Compra(cliente, produto1, 2); // valor total = 200
        Compra compra2 = new Compra(cliente, produto2, 1); // valor total = 200

        when(compraRepository.findAllByOrderByValorTotalAsc()).thenReturn(Arrays.asList(compra1, compra2));

        List<ClienteComprasDTO> result = compraService.findAllSortedByValor();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("João", result.get(0).getNome());
        assertEquals(2, result.get(0).getCompras().size());
    }

    @Test
    public void testFindMaiorCompraPorAno() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setCpf("12345678900");

        Produto produto = new Produto();
        produto.setCodigo(1L);
        produto.setPreco(new BigDecimal("100.00"));

        Compra compra = new Compra(cliente, produto, 2); // valor total = 200

        when(compraRepository.findTopCompraByAnoOrderByValorTotalDesc(2019)).thenReturn(Collections.singletonList(compra));

        ClienteComprasDTO result = compraService.findMaiorCompraPorAno(2019);

        assertNotNull(result);
        assertEquals("João", result.getNome());
        assertEquals("12345678900", result.getCpf());
        assertEquals(1, result.getCompras().size());
        assertEquals(new BigDecimal("200.00"), result.getValorTotal());
    }

    @Test
    public void testFindTop3ClientesFieis() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("João");
        cliente1.setCpf("12345678900");
        cliente1.setCompras(Collections.emptyList()); // Inicializando a lista de compras

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Maria");
        cliente2.setCpf("98765432100");
        cliente2.setCompras(Collections.emptyList()); // Inicializando a lista de compras

        Cliente cliente3 = new Cliente();
        cliente3.setId(3L);
        cliente3.setNome("Pedro");
        cliente3.setCpf("11122233344");
        cliente3.setCompras(Collections.emptyList()); // Inicializando a lista de compras

        when(compraRepository.findTopClientesByCompras(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(cliente1, cliente2, cliente3)));

        List<ClienteDTO> result = compraService.findTop3ClientesFieis();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("João", result.get(0).getNome());
        assertEquals("Maria", result.get(1).getNome());
        assertEquals("Pedro", result.get(2).getNome());
    }

    @Test
    public void testFindRecomendacaoPorClienteETipo() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setCpf("12345678900");

        Produto produto = new Produto();
        produto.setCodigo(1L);
        produto.setTipoVinho("Tinto");
        produto.setSafra("2017");

        Compra compra = new Compra(cliente, produto, 5);

        when(compraRepository.findAll()).thenReturn(Collections.singletonList(compra));

        List<ClienteRecomendacaoDTO> result = compraService.findRecomendacaoPorClienteETipo();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("João", result.get(0).getNome());
        assertEquals("Tinto", result.get(0).getTipoVinhoMaisComprado());
        assertEquals("2017", result.get(0).getSafra());
    }
}
