package com.winesales.salesmanager.dto;

import java.util.List;

public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private List<CompraDTO> compras;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public List<CompraDTO> getCompras() {
        return compras;
    }
    public void setCompras(List<CompraDTO> compras) {
        this.compras = compras;
    }
}
