package com.hakan.arayici.readingisgood.exception;

public class BookIsNotFoundException extends RuntimeException {

    public BookIsNotFoundException(Long id) {
        super(String.format("Book with Id %d not found", id));
    }

}
