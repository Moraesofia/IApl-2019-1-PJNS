package com.github.moraesofia.pjns.entities;

/**
 * Created by aluno on 01/04/19.
 */
public class Filme {

    private String titulo;

    private int ano;

    private String genero;

    private Pessoa diretor;

    private Pessoa atriz;

    private Pessoa ator;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Pessoa getDiretor() {
        return diretor;
    }

    public void setDiretor(Pessoa diretor) {
        this.diretor = diretor;
    }

    public Pessoa getAtriz() {
        return atriz;
    }

    public void setAtriz(Pessoa atriz) {
        this.atriz = atriz;
    }

    public Pessoa getAtor() {
        return ator;
    }

    public void setAtor(Pessoa ator) {
        this.ator = ator;
    }
}
