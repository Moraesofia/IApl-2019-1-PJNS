package com.crossover.jns.JnsFilmes.config;

import com.crossover.jns.JnsFilmes.exceptions.RestApiException;
import com.crossover.jns.JnsFilmes.exceptions.WebsiteException;
import com.crossover.jns.JnsFilmes.presentation.dto.RespostaSimplesDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UnknownExceptionHandler implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = PATH)
    public String error(Model model, HttpServletRequest request) throws RestApiException {
        // Try to fetch website exception from request
        WebsiteException websiteException = null;
        String message = null;
        if (request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE) != null) {
            Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
            message = throwable.getLocalizedMessage();
            if (message == null || message.isEmpty())
                message = throwable.getMessage();
            if (throwable instanceof WebsiteException) {
                websiteException = (WebsiteException) throwable;
            }
        }

        // If there's no website exception, create a generic one based on status code
        if (websiteException == null) {
            Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
            if (status == 404)
                websiteException = WebsiteException.NotFound();
            else if (status == 403)
                websiteException = WebsiteException.Unauthorized();
            else {
                if (message == null || message.isEmpty())
                    message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString();
                if (status == 500)
                    websiteException = WebsiteException.Internal(message);
                else {
                    websiteException = WebsiteException.Unknown(message);
                }
            }
        }

        model.addAttribute("title", websiteException.getTitle());
        if (websiteException.getExtras() != null && !websiteException.getExtras().isEmpty())
            model.addAttribute("extras", websiteException.getExtras());
        return "error";
    }

    private RestApiException handleApiError(Model model, HttpServletRequest request, String forwardUrl) throws RestApiException {
        // Try to fetch api exception from request
        RestApiException apiException = null;
        String message = null;
        if (request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE) != null) {
            Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
            message = throwable.getLocalizedMessage();
            if (message == null || message.isEmpty())
                message = throwable.getMessage();
            if (throwable instanceof RestApiException) {
                apiException = (RestApiException) throwable;
            }
        }

        // If there's no website exception, create a generic one based on status code
        if (apiException == null) {
            Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
            apiException = new RestApiException(HttpStatus.valueOf(status), message);
        }
        apiException.setPath(forwardUrl);
        return apiException;
    }
}
