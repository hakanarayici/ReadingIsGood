package com.hakan.arayici.readingisgood.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class BookDTO {

    private Long id;

    @NotNull
    private String author;

    @NotNull
    private String title;

    @Min(1)
    @NotNull
    private Integer stockCount;
}
