package com.github.moraesofia.pjns.com.github.moraesofia.pjns.entities.enums;

/**
 * Created by aluno on 01/04/19.
 */
public enum GeneroEnum {

    F("F"), M("M");

    private String genero;

    GeneroEnum(String gen) {
        this.genero = gen;
    }

    public String getGenero() {
        return this.genero;
    }
}
