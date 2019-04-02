package com.github.moraesofia.pjns.com.github.moraesofia.pjns.entities;

import com.github.moraesofia.pjns.com.github.moraesofia.pjns.entities.enums.CategoriaEnum;

/**
 * Created by aluno on 01/04/19.
 */
public class Premiacao {

    private String nome;

    private int ano;

    private Premio premio;

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

    public Premio getPremio() {
        return premio;
    }

    public void setPremio(Premio premio) {
        this.premio = premio;
    }

}
