package com.crossover.jns.JnsFilmes.presentation.dto.producer;

import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;

public class BankDto {

    private Long id = null;
    private Integer titular = null;
    private Integer agencia = null;
    private Long numero = null;
    private String senha = null;
    private Float saldo = null;
    private Float limite = null;
    private Boolean ativo = null;

    public BankDto() throws InvalidDtoException {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTitular() {
        return titular;
    }

    public void setTitular(Integer titular) {
        this.titular = titular;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Float getLimite() {
        return limite;
    }

    public void setLimite(Float limite) {
        this.limite = limite;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "BankDto{" +
                "id=" + id +
                "agencia=" + agencia +
                "numero=" + numero +
                "senha='" + senha +
                "saldo=" + saldo +
                "limite=" + limite +
                "ativo=" + ativo +
                "}";
    }
}
