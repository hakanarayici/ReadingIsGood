package com.hakan.arayici.readingisgood.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
public class BookStockUpdateDTO {

    @NotNull
    private Long id;

    @Min(1)
    @NotNull
    private Integer stockCount;
}
