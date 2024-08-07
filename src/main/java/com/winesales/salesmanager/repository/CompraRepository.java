package com.winesales.salesmanager.repository;

import com.winesales.salesmanager.domain.Cliente;
import com.winesales.salesmanager.domain.Compra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query("SELECT c FROM Compra c ORDER BY c.quantidade * c.produto.preco ASC")
    List<Compra> findAllByOrderByValorTotalAsc();

    @Query("SELECT c FROM Compra c JOIN c.produto p WHERE p.anoCompra = :ano ORDER BY c.quantidade * p.preco DESC")
    List<Compra> findTopCompraByAnoOrderByValorTotalDesc(int ano);

    @Query("SELECT c.cliente FROM Compra c GROUP BY c.cliente ORDER BY SUM(c.quantidade * c.produto.preco) DESC")
    Page<Cliente> findTopClientesByCompras(Pageable pageable);
}

