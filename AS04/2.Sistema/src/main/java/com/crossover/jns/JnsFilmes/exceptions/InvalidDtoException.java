package com.crossover.jns.JnsFilmes.exceptions;

/**
 * Exception thrown when an entity/DTO has entities that can't be found.
 */
public class InvalidDtoException extends Exception {

    private String field;

    public InvalidDtoException(String field, String reason) {
        super(reason);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
