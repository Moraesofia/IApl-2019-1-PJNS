package com.github.moraesofia.pjns.entities;

import com.github.moraesofia.pjns.entities.enums.CategoriaEnum;


/**
 * Created by aluno on 01/04/19.
 */
public class Premio {

    private CategoriaEnum categoria;

    private Pessoa vencedor;

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public Pessoa getVencedor() {
        return vencedor;
    }

    public void setVencedor(Pessoa vencedor) {
        this.vencedor = vencedor;
    }
}
