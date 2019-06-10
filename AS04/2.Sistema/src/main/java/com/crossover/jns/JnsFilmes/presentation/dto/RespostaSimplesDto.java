package com.crossover.jns.JnsFilmes.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespostaSimplesDto {

    private String timestamp;
    private int status;
    private String error;
    private Object message;
    private String path;

    public RespostaSimplesDto(int status, String path, Object message) {
        this.timestamp = (ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT));
        this.status = (status);
        this.error = null;
        this.message = (message);
        this.path = (path);
    }

    public RespostaSimplesDto(int status, String error, String path, Object message) {
        this.timestamp = (ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT));
        this.status = (status);
        this.error = (error);
        this.message = (message);
        this.path = (path);
    }

    public RespostaSimplesDto(int status, Exception exception, String path) {
        this.timestamp = (ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT));
        this.status = (status);
        this.error = (exception.toString());
        this.message = (exception.getMessage());
        this.path = (path);
    }

    public RespostaSimplesDto(HttpStatus status, String path) {
        this.timestamp = (ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT));
        this.status = (status.value());
        this.error = (status.getReasonPhrase());
        this.message = (status.getReasonPhrase());
        this.path = (path);
    }

    public RespostaSimplesDto(HttpStatus status, String path, Object message) {
        this.timestamp = (ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT));
        this.status = (status.value());
        this.error = (status.getReasonPhrase());
        this.message = (message);
        this.path = (path);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
