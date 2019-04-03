package com.github.moraesofia.pjns.files.enums;

import com.github.moraesofia.pjns.files.exceptions.UnsupportedTypeException;

public enum TipoLoteEnum {
    INSERCAO_OU_OBTENCAO_DE_DADOS(1);

    private final int codigo;

    TipoLoteEnum(int codigo) {
        this.codigo = codigo;
    }

    public static TipoLoteEnum fromCode(int codigo) {
        for (TipoLoteEnum value : values()) {
            if (value.codigo == codigo)
                return value;
        }
        throw new UnsupportedTypeException(codigo);
    }
}
