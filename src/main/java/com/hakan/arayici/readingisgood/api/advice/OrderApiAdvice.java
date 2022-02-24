package com.hakan.arayici.readingisgood.api.advice;

import com.hakan.arayici.readingisgood.api.BookIsGoodApiResponse;
import com.hakan.arayici.readingisgood.exception.BookIsNotFoundException;
import com.hakan.arayici.readingisgood.exception.OrderIsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrderApiAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderIsNotFoundException.class)
    public ResponseEntity<Object> handleRecipeNotFoundException(
            OrderIsNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(new BookIsGoodApiResponse(false,ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
