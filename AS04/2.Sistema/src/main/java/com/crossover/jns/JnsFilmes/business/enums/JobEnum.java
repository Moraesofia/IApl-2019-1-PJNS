package com.crossover.jns.JnsFilmes.business.enums;

import com.crossover.jns.JnsFilmes.business.exceptions.UnsupportedTypeException;

public enum JobEnum {

    DIRECTOR("Director"), ACTOR("Actor"), ACTRESS("Actress"), WRITER("Writer");

    private String text;

    JobEnum(String carg) {
        this.text = carg;
    }

    public static JobEnum fromText(String text) {
        for (JobEnum value : values()) {
            if (value.text.equals(text))
                return value;
        }
        throw new UnsupportedTypeException(text);
    }

    public String getText() {
        return text;
    }
}
