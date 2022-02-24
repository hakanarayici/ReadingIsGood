package com.hakan.arayici.readingisgood.api.order;

import com.hakan.arayici.readingisgood.api.BookIsGoodApiResponse;
import com.hakan.arayici.readingisgood.api.dto.CreateOrderDTO;
import com.hakan.arayici.readingisgood.api.dto.OrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/order")
public interface IOrderApi {

    @Operation(summary = "creates order", description = "creates order")
    @ApiResponse(description = "creates order")
    @PostMapping("/create")
    ResponseEntity<BookIsGoodApiResponse> createOrder(@RequestBody @Validated CreateOrderDTO createOrderDTO);

    @Operation(summary = "gets order by given id", description = "gets order by given id")
    @ApiResponse(description = "gets order by given id")
    @GetMapping("/getByID")
    ResponseEntity<OrderDTO> getByOrderID(@RequestParam Long orderID);

    @Operation(summary = "gets order by given dates", description = "gets order by given dates")
    @ApiResponse(description = "gets order by given dates")
    @GetMapping("/getByDate")
    ResponseEntity<List<OrderDTO>> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime startDate,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate);
}
