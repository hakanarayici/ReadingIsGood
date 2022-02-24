package com.hakan.arayici.readingisgood.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookOrderDTO {
    private Integer quantity;
    private Long bookID;
}
