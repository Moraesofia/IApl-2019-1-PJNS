package com.crossover.jns.JnsFilmes.exceptions;

public class ProducerApiException extends Exception {
    public ProducerApiException() {
    }

    public ProducerApiException(String message) {
        super(message);
    }

    public ProducerApiException(Throwable cause) {
        super(cause);
    }
}
