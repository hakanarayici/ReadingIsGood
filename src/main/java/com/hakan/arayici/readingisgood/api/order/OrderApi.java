package com.hakan.arayici.readingisgood.api.order;

import com.hakan.arayici.readingisgood.api.BookIsGoodApiResponse;
import com.hakan.arayici.readingisgood.api.dto.CreateOrderDTO;
import com.hakan.arayici.readingisgood.api.dto.OrderDTO;
import com.hakan.arayici.readingisgood.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderApi implements IOrderApi{

    private final IOrderService orderService;

    @Override
    public ResponseEntity<BookIsGoodApiResponse> createOrder(CreateOrderDTO createOrderDTO) {
        orderService.createOrder(createOrderDTO);
        return new ResponseEntity<>(new BookIsGoodApiResponse(true,""), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDTO> getByOrderID(Long orderID) {
        return new ResponseEntity<>(orderService.getOrderByID(orderID), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return new ResponseEntity<>(orderService.getByDate(startDate,endDate), HttpStatus.OK);
    }

}
