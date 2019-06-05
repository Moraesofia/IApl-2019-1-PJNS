package com.crossover.jns.JnsFilmes.business.enums;

import com.crossover.jns.JnsFilmes.business.exceptions.UnsupportedTypeException;

public enum GenreEnum {

    F("F"), M("M");

    private String text;

    GenreEnum(String gen) {
        this.text = gen;
    }

    public static GenreEnum fromText(String text) {
        for (GenreEnum value : values()) {
            if (value.text.equals(text))
                return value;
        }
        throw new UnsupportedTypeException(text);
    }

    public String getText() {
        return this.text;
    }
}
