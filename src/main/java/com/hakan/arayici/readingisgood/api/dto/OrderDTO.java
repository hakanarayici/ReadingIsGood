package com.hakan.arayici.readingisgood.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDTO {
    private Long orderID;
    private LocalDateTime orderDate;
    private String customerNo;
    private List<OrderBookDTO> bookDTOList;
}
