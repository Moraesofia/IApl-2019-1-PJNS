package com.crossover.jns.JnsFilmes.config;

import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.RestApiException;
import com.crossover.jns.JnsFilmes.exceptions.WebsiteException;
import com.crossover.jns.JnsFilmes.presentation.dto.RespostaSimplesDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class KnownExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RestApiException.class,})
    protected ResponseEntity<Object> handleApiException(RestApiException ex, HttpServletRequest request, WebRequest webRequest) {
        String path = ex.getPath() == null ? request.getContextPath() + request.getServletPath() : ex.getPath();
        RespostaSimplesDto respostaSimplesDto = new RespostaSimplesDto(ex.getStatus(), path, ex.getMessage());
        return handleExceptionInternal(ex, respostaSimplesDto, new HttpHeaders(), ex.getStatus(), webRequest);
    }

    @ExceptionHandler(value = {WebsiteException.class})
    public ModelAndView handleWebsiteException(HttpServletRequest req, WebsiteException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", ex.getTitle());
        if (ex.getExtras() != null)
            mav.addObject("extras", ex.getExtras());
        mav.setViewName("error");
        return mav;
    }

}