package com.github.moraesofia.pjns.entities;

/**
 * Created by aluno on 01/04/19.
 */
public class Filme {

    private int id;

    private String titulo;

    private int ano;

    private String genero;

    private Integer idDiretor;

    private Integer idAtriz;

    private Integer idAtor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Integer getIdDiretor() {
        return idDiretor;
    }

    public void setIdDiretor(Integer idDiretor) {
        this.idDiretor = idDiretor;
    }

    public Integer getIdAtriz() {
        return idAtriz;
    }

    public void setIdAtriz(Integer idAriz) {
        this.idAtriz = idAriz;
    }

    public Integer getIdAtor() {
        return idAtor;
    }

    public void setIdAtor(Integer idAtor) {
        this.idAtor = idAtor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filme filme = (Filme) o;

        return id == filme.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
