package com.github.moraesofia.pjns.entities.enums;

import com.github.moraesofia.pjns.files.exceptions.UnsupportedTypeException;

/**
 * Created by aluno on 01/04/19.
 */
public enum CargoEnum {

    DIRETOR("Diretor"),
    ATRIZ("Atriz"),
    ATOR("Ator");

    private String text;

    CargoEnum(String carg) {
        this.text = carg;
    }

    public static CargoEnum fromText(String text) {
        for (CargoEnum value : values()) {
            if (value.text.equals(text))
                return value;
        }
        throw new UnsupportedTypeException(text);
    }

    public String getText() {
        return text;
    }
}
