package com.hakan.arayici.readingisgood.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class BookIsGoodApiResponse implements Serializable {
    private Boolean success;
    private String errorMessage;
}
