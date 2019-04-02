package com.github.moraesofia.pjns.com.github.moraesofia.pjns.entities.enums;

/**
 * Created by aluno on 01/04/19.
 */
public enum CargoEnum {

    DIRETOR("Diretor"),
    ATRIZ("Atriz"),
    ATOR("Ator");

    private String cargo;

    CargoEnum(String carg) {
        this.cargo = carg;
    }

    public String getCargo() {
        return cargo;
    }


}
