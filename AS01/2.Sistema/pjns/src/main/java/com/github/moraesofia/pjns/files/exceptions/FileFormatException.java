package com.github.moraesofia.pjns.files.exceptions;

public class FileFormatException extends RuntimeException {
    public FileFormatException(String message) {
        super(message);
    }

    public FileFormatException(String message, int position) {
        super(message + "(na posição " + position + ")");
    }
}
