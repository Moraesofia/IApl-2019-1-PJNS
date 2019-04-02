package com.github.moraesofia.pjns.com.github.moraesofia.pjns.entities.enums;

/**
 * Created by aluno on 01/04/19.
 */
public enum CategoriaEnum {


    DIRETOR("Melhor Diretor"),
    ATRIZ("Melhor Atriz"),
    ATOR("Melhor Ator"),
    FILME("Melhor Filme"),
    ROTEIRO("Melhor Roteiro");

    private String categoria;

    CategoriaEnum(String cat) {
        this.categoria = cat;
    }

    public String getCategoria() {
        return this.categoria;
    }

}
