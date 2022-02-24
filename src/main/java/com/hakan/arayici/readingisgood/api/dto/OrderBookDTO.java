package com.hakan.arayici.readingisgood.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderBookDTO {
    private String title;
    private String author;
    private BigDecimal unitPrice;
    private Integer quantity;
}
