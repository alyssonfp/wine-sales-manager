package com.winesales.salesmanager.repository;

import com.winesales.salesmanager.domain.Cliente;
import com.winesales.salesmanager.domain.Compra;
import com.winesales.salesmanager.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CompraRepositoryTest {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private Cliente cliente1;
    private Produto produto1, produto2;
    private Compra compra1, compra2, compra3;

    @BeforeEach
    public void setUp() {
        cliente1 = new Cliente("João Silva", "12345678900", null);
        clienteRepository.save(cliente1);

        produto1 = new Produto();
        produto1.setCodigo(1L);
        produto1.setTipoVinho("Tinto");
        produto1.setPreco(new BigDecimal("100.00"));
        produto1.setSafra("2018");
        produto1.setAnoCompra(2019);
        produtoRepository.save(produto1);

        produto2 = new Produto();
        produto2.setCodigo(2L);
        produto2.setTipoVinho("Branco");
        produto2.setPreco(new BigDecimal("50.00"));
        produto2.setSafra("2019");
        produto2.setAnoCompra(2020);
        produtoRepository.save(produto2);

        compra1 = new Compra(cliente1, produto1, 2);
        compra2 = new Compra(cliente1, produto2, 3);
        compra3 = new Compra(cliente1, produto1, 1);

        compraRepository.save(compra1);
        compraRepository.save(compra2);
        compraRepository.save(compra3);
    }

    @Test
    public void testFindAllByOrderByValorTotalAsc() {
        List<Compra> compras = compraRepository.findAllByOrderByValorTotalAsc();
        assertEquals(3, compras.size());
        assertEquals(compra3, compras.get(0));
        assertEquals(compra2, compras.get(1));
        assertEquals(compra1, compras.get(2));
    }

    @Test
    public void testFindTopCompraByAnoOrderByValorTotalDesc() {
        List<Compra> compras = compraRepository.findTopCompraByAnoOrderByValorTotalDesc(2019);
        assertEquals(2, compras.size());
        assertEquals(compra1, compras.get(0));
        assertEquals(compra3, compras.get(1));
    }

    @Test
    public void testFindTopClientesByCompras() {
        Pageable pageable = PageRequest.of(0, 3);
        List<Cliente> clientesFieis = compraRepository.findTopClientesByCompras(pageable).getContent();
        assertEquals(1, clientesFieis.size());
        assertEquals(cliente1, clientesFieis.get(0));
    }
}
