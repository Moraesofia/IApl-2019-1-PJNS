package com.crossover.jns.JnsFilmes.business.enums;

import com.crossover.jns.JnsFilmes.business.exceptions.UnsupportedTypeException;

public enum CategoryEnum {
    DIRECTOR("Best Director"), ACTRESS("Best Actress"), ACTOR("Best Actor"), FILM("Best Film"), SCRIPT("Best Script");

    private String text;

    CategoryEnum(String cat) {
        this.text = cat;
    }

    public static CategoryEnum fromText(String text) {
        for (CategoryEnum value : values()) {
            if (value.text.equals(text))
                return value;
        }
        throw new UnsupportedTypeException(text);
    }

    public String getText() {
        return this.text;
    }

}
