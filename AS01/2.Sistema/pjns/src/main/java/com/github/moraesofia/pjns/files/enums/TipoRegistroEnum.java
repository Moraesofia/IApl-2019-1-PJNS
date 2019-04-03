package com.github.moraesofia.pjns.files.enums;

import com.github.moraesofia.pjns.files.exceptions.UnsupportedTypeException;

public enum TipoRegistroEnum {
    FILE_HEADER(0),
    LOTE_HEADER(1),
    LOTE_DETALHE(2),
    LOTE_TRAILER(3),
    FILE_TRAILER(9);

    private final int codigo;

    TipoRegistroEnum(int codigo) {
        this.codigo = codigo;
    }

    public static TipoRegistroEnum fromCode(int codigo) {
        for (TipoRegistroEnum value : values()) {
            if (value.codigo == codigo)
                return value;
        }
        throw new UnsupportedTypeException(codigo);
    }
}
