package com.es.apiPedidos.error;

import com.es.apiPedidos.error.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage handleBadRequest(HttpServletRequest request, Exception e) {
        return new ErrorMessage(e.getMessage(), request.getRequestURI());
    }


    @ExceptionHandler({NotFoundException.class, NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage handleNotFound(HttpServletRequest request, Exception e) {
        return new ErrorMessage(e.getMessage(), request.getRequestURI());
    }


    @ExceptionHandler({UnauthorizedException.class, AuthenticationException.class, HttpClientErrorException.Forbidden.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMessage handleUnauthorized(HttpServletRequest request, Exception e) {
        return new ErrorMessage(e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorMessage handleForbidden(HttpServletRequest request, Exception e) {
        return new ErrorMessage(e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessage handleDuplicate(HttpServletRequest request, Exception e) {
        return new ErrorMessage(e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage handleInternalServer(HttpServletRequest request, Exception e) {
        return new ErrorMessage(e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(GenericInternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage handleGeneric(HttpServletRequest request, Exception e) {
        return new ErrorMessage(e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage handleNoContent(HttpServletRequest request, Exception e) {
        return new ErrorMessage(e.getMessage(), request.getRequestURI());
    }
}
