package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.CreateOrderDTO;
import com.hakan.arayici.readingisgood.api.dto.OrderDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {
    Boolean createOrder(CreateOrderDTO orderDTO);

    OrderDTO getOrderByID(Long orderID);

    List<OrderDTO> getByDate(LocalDateTime startDate, LocalDateTime endDate);

    List<OrderDTO> getByCustomerNo(String customerNo,int page, int count);
}
