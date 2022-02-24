package com.hakan.arayici.readingisgood.exception;

public class CustomerIsNotFoundException extends RuntimeException {
    public CustomerIsNotFoundException(Long id) {
        super(String.format("Customer with Id %d not found", id));
    }

}
