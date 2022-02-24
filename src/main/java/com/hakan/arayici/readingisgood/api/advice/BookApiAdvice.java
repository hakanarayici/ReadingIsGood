package com.hakan.arayici.readingisgood.api.advice;

import com.hakan.arayici.readingisgood.api.BookIsGoodApiResponse;
import com.hakan.arayici.readingisgood.exception.BookIsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookApiAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookIsNotFoundException.class)
    public ResponseEntity<Object> handleRecipeNotFoundException(
            BookIsNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(new BookIsGoodApiResponse(false,ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
