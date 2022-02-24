package com.hakan.arayici.readingisgood.exception;

public class OrderIsNotFoundException extends RuntimeException {

    public OrderIsNotFoundException(Long id) {
        super(String.format("Order with Id %d not found", id));
    }

}
