package com.crossover.jns.JnsFilmes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;


/**
 * Exception thrown by the Website controllers
 */
public class WebsiteException extends Exception {

    private final String title;
    private final String extras;

    public static WebsiteException NotFound() {
        return new WebsiteException("not_found", null);
    }

    public static WebsiteException Unauthorized() {
        return new WebsiteException("unauthorized", null);
    }

    public static WebsiteException Internal(Exception ex) {
        return new WebsiteException("internal", ex.getLocalizedMessage());
    }

    public static WebsiteException Internal(String message) {
        return new WebsiteException("internal", message);
    }

    public static WebsiteException Unknown(String message) {
        return new WebsiteException("unknown", message);
    }

    private WebsiteException(String title, String extras) {
        super(title);
        this.title = title;
        this.extras = extras;
    }

    public String getTitle() {
        return title;
    }

    public String getExtras() {
        return extras;
    }
}
