package com.github.moraesofia.pjns.entities;

import com.github.moraesofia.pjns.entities.enums.CategoriaEnum;


/**
 * Created by aluno on 01/04/19.
 */
public class Premio {

    private int id;

    private CategoriaEnum categoria;

    private Integer idVencedor;

    private int idFilme;

    private int idPremiacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public Integer getIdVencedor() {
        return idVencedor;
    }

    public void setIdVencedor(Integer idVencedor) {
        this.idVencedor = idVencedor;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public int getIdPremiacao() {
        return idPremiacao;
    }

    public void setIdPremiacao(int idPremiacao) {
        this.idPremiacao = idPremiacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Premio premio = (Premio) o;

        return id == premio.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
