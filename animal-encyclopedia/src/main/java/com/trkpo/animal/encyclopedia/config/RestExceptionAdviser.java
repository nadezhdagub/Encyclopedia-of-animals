/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.config;

import com.trkpo.animal.encyclopedia.exceptions.NotAuthorizedException;
import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.exceptions.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.PropertyAccessException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestExceptionAdviser {

    /**
     *  Обработка ошибок со статусом ответа
     */
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<ErrorInfo> responseStatusExceptionHandler(HttpServletRequest req,
                                                                    Exception e) {
        return handleError(req.getRequestURL().toString(), e,
                ((ResponseStatusException) e).getStatus());
    }

    /**
     *  Обработка ошибок аутентификации и отсутствия прав доступа
     */
    @ExceptionHandler({NotAuthorizedException.class})
    public ResponseEntity<ErrorInfo> notAuthorizedHandler(HttpServletRequest req, Exception e) {
        return handleError(req.getRequestURL().toString(), e, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Обработка ошибок обращения к несуществующим объектам на сервере
     */
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorInfo> notFoundHandler(HttpServletRequest req, Exception e) {
        return handleError(req.getRequestURL().toString(), e, HttpStatus.NOT_FOUND);
    }

    /**
     * Обработка ошибок неправильного формата указания параметров URL
     */
    @ExceptionHandler({
            PropertyAccessException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ErrorInfo> jsonBadParamFormatHandler(HttpServletRequest req, Exception e) {
        return handleError(req.getRequestURL().toString(), e, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Обработка ошибок обработки Json
     */
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            UnprocessableEntityException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<ErrorInfo> jsonBadJsonFormatHandler(HttpServletRequest req, Exception e) {
        return handleError(req.getRequestURL().toString(), e, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Обработка ошибок нарушения ссылочной целостности в БД
     */
    @ExceptionHandler({
            org.hibernate.exception.ConstraintViolationException.class,
            javax.validation.ConstraintViolationException.class
    })
    public ResponseEntity<ErrorInfo> constraintViolationHandler(HttpServletRequest req, Exception e) {
        return handleError(req.getRequestURL().toString(), e, HttpStatus.CONFLICT);
    }

    /**
     * Обработка ошибок форматов хранения в БД
     */
    @ExceptionHandler({
            SQLException.class,
            DataAccessException.class,
            DataIntegrityViolationException.class
    })
    public ResponseEntity<ErrorInfo> notBadRequestHandler(HttpServletRequest req, Exception e) {
        return handleError(req.getRequestURL().toString(), e, HttpStatus.CONFLICT);
    }

    /**
     * Обработка неизвестные ошибок
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> internalServerErrorHandler(HttpServletRequest req, Exception e) {
        return handleError(req.getRequestURL().toString(), e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorInfo> handleError(String url, Exception e, HttpStatus status) {
        log.error(e.getMessage());
        log.debug("", e);
        return ResponseEntity
                .status(status)
                .body(new ErrorInfo(url, e.getClass().getName(), e.getMessage()));
    }

    @RequiredArgsConstructor
    public static class ErrorInfo {
        public final String url;
        public final String type;
        public final String message;
    }
}
