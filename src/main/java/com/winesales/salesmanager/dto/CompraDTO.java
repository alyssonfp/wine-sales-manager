package com.winesales.salesmanager.dto;


import java.math.BigDecimal;

public class CompraDTO {
    private Long produtoId;
    private String produtoNome;
    private BigDecimal produtoPreco;
    private int quantidade;
    private BigDecimal valorTotalCompra;


    public Long getProdutoId() {
        return produtoId;
    }
    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }
    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public BigDecimal getProdutoPreco() {
        return produtoPreco;
    }
    public void setProdutoPreco(BigDecimal produtoPreco) {
        this.produtoPreco = produtoPreco;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public BigDecimal getValorTotalCompra() {
        return valorTotalCompra;
    }
    public void setValorTotalCompra(BigDecimal valorTotal) {
        this.valorTotalCompra = valorTotal;
    }
}


