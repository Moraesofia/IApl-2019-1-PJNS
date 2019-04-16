package com.github.moraesofia.pjns.files.enums;

import com.github.moraesofia.pjns.files.exceptions.UnsupportedTypeException;

public enum TipoRegistroDetalheEnum {
    RESPOSTA_INSERCAO_DADOS("AB"),
    RESPOSTA_OBTENCAO_DADOS("AC"),
    DADOS_PREMIACAO("AD"),
    DADOS_PREMIO("AE"),
    DADOS_PESSOA("AF"),
    DADOS_FILME("AG");

    private final String codigo;

    TipoRegistroDetalheEnum(String codigo) {
        this.codigo = codigo;
    }

    public static TipoRegistroDetalheEnum fromCode(String codigo) {
        for (TipoRegistroDetalheEnum value : values()) {
            if (value.codigo.equals(codigo))
                return value;
        }
        throw new UnsupportedTypeException(codigo);
    }
}
