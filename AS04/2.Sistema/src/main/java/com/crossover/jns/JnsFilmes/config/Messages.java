package com.crossover.jns.JnsFilmes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Helper to simplify accessing i18n messages in code.
 * * This finds messages automatically found from src/main/resources (files named messages_*.properties) *
 * This example uses hard-coded English locale.
 *
 * @author Joni Karppinen
 * @see "https://gist.github.com/jonikarppinen/0d600b0c82edce890310"
 * @since 2015-11-02
 */
@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource);
    }

    public String get(String code, HttpServletRequest req) {
        Locale locale = localeResolver.resolveLocale(req);
        try {
            return accessor.getMessage(code, locale);
        } catch (NoSuchMessageException ex) {
            return "??" + code + "_" + locale.getLanguage() + "??";
        }
    }

}