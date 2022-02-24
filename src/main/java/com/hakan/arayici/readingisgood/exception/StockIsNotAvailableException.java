package com.hakan.arayici.readingisgood.exception;

public class StockIsNotAvailableException extends RuntimeException {
    public StockIsNotAvailableException(Long bookID, Integer stockCount) {
        super(String.format("Stock of book with id : %d is not enough. current stock is : %d", bookID, stockCount));
    }
}
