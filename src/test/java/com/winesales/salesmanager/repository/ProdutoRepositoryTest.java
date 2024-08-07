package com.winesales.salesmanager.repository;

import com.winesales.salesmanager.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    public void setUp() {
        produto1 = new Produto();
        produto1.setCodigo(1L);
        produto1.setTipoVinho("Tinto");
        produto1.setPreco(new BigDecimal("100.00"));
        produto1.setSafra("2018");
        produto1.setAnoCompra(2019);

        produto2 = new Produto();
        produto2.setCodigo(2L);
        produto2.setTipoVinho("Branco");
        produto2.setPreco(new BigDecimal("50.00"));
        produto2.setSafra("2019");
        produto2.setAnoCompra(2020);

        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
    }

    @Test
    public void testFindByCodigo() {
        Optional<Produto> foundProduto = produtoRepository.findByCodigo(1L);
        assertTrue(foundProduto.isPresent());
        assertEquals(produto1, foundProduto.get());
    }

    @Test
    public void testFindByCodigoNotFound() {
        Optional<Produto> foundProduto = produtoRepository.findByCodigo(3L);
        assertFalse(foundProduto.isPresent());
    }

    @Test
    public void testSaveAndFindAll() {
        Produto produto3 = new Produto();
        produto3.setCodigo(3L);
        produto3.setTipoVinho("Rosé");
        produto3.setPreco(new BigDecimal("75.00"));
        produto3.setSafra("2020");
        produto3.setAnoCompra(2021);

        produtoRepository.save(produto3);

        Iterable<Produto> produtos = produtoRepository.findAll();
        assertEquals(3, ((Collection<?>) produtos).size());
        assertTrue(((Collection<?>) produtos).contains(produto3));
    }
}
