package com.crossover.jns.JnsFilmes.business.enums;

import com.crossover.jns.JnsFilmes.business.exceptions.UnsupportedTypeException;

public enum GenderEnum {

    F("F"), M("M");

    private String text;

    GenderEnum(String gen) {
        this.text = gen;
    }

    public static GenderEnum fromText(String text) {
        for (GenderEnum value : values()) {
            if (value.text.equals(text))
                return value;
        }
        throw new UnsupportedTypeException(text);
    }

    public String getText() {
        return this.text;
    }
}
