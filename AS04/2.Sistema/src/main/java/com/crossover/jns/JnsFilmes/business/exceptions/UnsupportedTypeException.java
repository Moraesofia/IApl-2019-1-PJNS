package com.crossover.jns.JnsFilmes.business.exceptions;

public class UnsupportedTypeException extends RuntimeException {

    private static final long serialVersionUID = 1966662476977265614L;

    public UnsupportedTypeException() {
    }

    public UnsupportedTypeException(Object type) {
        super("Unsupported Type: " + type.toString());
    }
}
