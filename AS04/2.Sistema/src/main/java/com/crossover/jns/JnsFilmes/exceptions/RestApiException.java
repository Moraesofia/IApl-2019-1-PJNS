package com.crossover.jns.JnsFilmes.exceptions;

import org.springframework.http.HttpStatus;


/**
 * Exception thrown by our REST API controllers
 */
public class RestApiException extends Exception {

    private HttpStatus status;
    private Object details;
    private String path;

    public RestApiException(HttpStatus status, String message, Object details, String path) {
        super(message);
        this.status = status;
        this.details = details;
        this.path = path;
    }

    public RestApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.details = null;
        this.path = null;
    }

    public RestApiException(HttpStatus status) {
        super(status.getReasonPhrase());
        this.status = status;
        this.details = null;
        this.path = null;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Object getDetails() {
        return details;
    }

    public String getPath() {
        return path;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
