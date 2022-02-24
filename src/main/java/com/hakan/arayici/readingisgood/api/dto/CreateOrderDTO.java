package com.hakan.arayici.readingisgood.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CreateOrderDTO {

    @NotNull
    private Long customerID;

    @NotEmpty
    private List<BookOrderDTO> bookOrderDTOList;
}
