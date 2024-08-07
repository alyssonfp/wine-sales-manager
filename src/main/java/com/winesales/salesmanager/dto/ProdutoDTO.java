package com.winesales.salesmanager.dto;

import java.math.BigDecimal;

public class ProdutoDTO {
    private Long codigo;
    private String tipoVinho;
    private BigDecimal preco;
    private String safra;
    private int anoCompra;


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getTipoVinho() {
        return tipoVinho;
    }

    public void setTipoVinho(String tipoVinho) {
        this.tipoVinho = tipoVinho;
    }
    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    public String getSafra() {
        return safra;
    }
    public void setSafra(String safra) {
        this.safra = safra;
    }
    public int getAnoCompra() {
        return anoCompra;
    }
    public void setAnoCompra(int anoCompra) {
        this.anoCompra = anoCompra;
    }
}
