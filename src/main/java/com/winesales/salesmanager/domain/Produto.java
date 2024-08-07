package com.winesales.salesmanager.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Produto {

    @Id
    @JsonProperty("codigo")
    private Long codigo;

    @JsonProperty("tipo_vinho")
    private String tipoVinho;

    @JsonProperty("preco")
    private BigDecimal preco;

    @JsonProperty("safra")
    private String safra;

    @JsonProperty("ano_compra")
    private int anoCompra;

    public Produto() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return anoCompra == produto.anoCompra &&
                Objects.equals(codigo, produto.codigo) &&
                Objects.equals(tipoVinho, produto.tipoVinho) &&
                Objects.equals(preco, produto.preco) &&
                Objects.equals(safra, produto.safra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, tipoVinho, preco, safra, anoCompra);
    }
}