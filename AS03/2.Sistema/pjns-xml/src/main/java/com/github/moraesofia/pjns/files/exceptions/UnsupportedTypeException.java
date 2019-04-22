package com.github.moraesofia.pjns.files.exceptions;

public class UnsupportedTypeException extends RuntimeException {
    public UnsupportedTypeException() {
    }

    public UnsupportedTypeException(Object tipo) {
        super("Tipo não suportado: " + tipo.toString());
    }
}
