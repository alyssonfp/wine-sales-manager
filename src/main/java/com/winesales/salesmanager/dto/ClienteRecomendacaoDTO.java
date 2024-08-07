package com.winesales.salesmanager.dto;

public class ClienteRecomendacaoDTO {
    private String nome;
    private String cpf;
    private String tipoVinhoMaisComprado;
    private String safra;


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
    public String getTipoVinhoMaisComprado() {
        return tipoVinhoMaisComprado;
    }
    public void setTipoVinhoMaisComprado(String tipoVinhoMaisComprado) {
        this.tipoVinhoMaisComprado = tipoVinhoMaisComprado;
    }
    public String getSafra() {
        return safra;
    }

    public void setSafra(String safra) {
        this.safra = safra;
    }
}
