package com.hakan.arayici.readingisgood.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class CustomerDTO {

    @Size(max = 50)
    @NotEmpty
    @NotNull
    private String customerName;

    @Size(max = 16)
    @NotEmpty
    @NotNull
    private String customerNo;
}
