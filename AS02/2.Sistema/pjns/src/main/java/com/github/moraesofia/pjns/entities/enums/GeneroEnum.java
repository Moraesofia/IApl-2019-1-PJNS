package com.github.moraesofia.pjns.entities.enums;

import com.github.moraesofia.pjns.files.exceptions.UnsupportedTypeException;

/**
 * Created by aluno on 01/04/19.
 */
public enum GeneroEnum {

    F("F"), M("M");

    private String text;

    GeneroEnum(String gen) {
        this.text = gen;
    }

    public static GeneroEnum fromText(String text) {
        for (GeneroEnum value : values()) {
            if (value.text.equals(text))
                return value;
        }
        throw new UnsupportedTypeException(text);
    }

    public String getText() {
        return this.text;
    }
}
