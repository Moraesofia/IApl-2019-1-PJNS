package com.crossover.jns.JnsFilmes.config;

import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.RestApiException;
import com.crossover.jns.JnsFilmes.exceptions.WebsiteException;
import com.crossover.jns.JnsFilmes.presentation.dto.RespostaSimplesDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class KnownExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {WebsiteException.class})
    public ModelAndView handleWebsiteException(HttpServletRequest req, WebsiteException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", ex.getTitle());
        if (ex.getExtras() != null)
            mav.addObject("extras", ex.getExtras());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = {RestApiException.class,})
    protected ResponseEntity<Object> handleApiException(RestApiException ex, HttpServletRequest request, WebRequest webRequest) {
        String path = ex.getPath() == null ? request.getContextPath() + request.getServletPath() : ex.getPath();
        RespostaSimplesDto respostaSimplesDto = new RespostaSimplesDto(ex.getStatus(), path, ex.getMessage());
        return handleExceptionInternal(ex, respostaSimplesDto, new HttpHeaders(), ex.getStatus(), webRequest);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return handleException(ex, headers, status, webRequest);
    }

    private ResponseEntity<Object> handleException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        String path = webRequest.getContextPath();
        RespostaSimplesDto respostaSimplesDto = new RespostaSimplesDto(status, path, ex.getMessage());
        return handleExceptionInternal(ex, respostaSimplesDto, new HttpHeaders(), status, webRequest);
    }

}