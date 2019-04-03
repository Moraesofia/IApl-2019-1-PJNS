package com.github.moraesofia.pjns.entities.enums;

import com.github.moraesofia.pjns.files.exceptions.UnsupportedTypeException;

/**
 * Created by aluno on 01/04/19.
 */
public enum CategoriaEnum {
    DIRETOR("Melhor Diretor"),
    ATRIZ("Melhor Atriz"),
    ATOR("Melhor Ator"),
    FILME("Melhor Filme"),
    ROTEIRO("Melhor Roteiro");

    private String text;

    CategoriaEnum(String cat) {
        this.text = cat;
    }

    public static CategoriaEnum fromText(String text) {
        for (CategoriaEnum value : values()) {
            if (value.text.equals(text))
                return value;
        }
        throw new UnsupportedTypeException(text);
    }

    public String getText() {
        return this.text;
    }

}
