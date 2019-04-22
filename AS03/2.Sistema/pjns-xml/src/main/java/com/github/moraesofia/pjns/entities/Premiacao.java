package com.github.moraesofia.pjns.entities;

/**
 * Created by aluno on 01/04/19.
 */
public class Premiacao {

    private int id;

    private String nome;

    private int ano;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Premiacao premiacao = (Premiacao) o;

        return id == premiacao.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
