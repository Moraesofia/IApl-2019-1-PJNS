package com.github.moraesofia.pjns.files.exceptions;

public class UnsupportedLayoutException extends RuntimeException {
    public UnsupportedLayoutException(int versao) {
        super("Versão do layout não suportada: " + versao);
    }
}
