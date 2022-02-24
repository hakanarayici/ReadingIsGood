package com.hakan.arayici.readingisgood.api.customer;

import com.hakan.arayici.readingisgood.api.BookIsGoodApiResponse;
import com.hakan.arayici.readingisgood.api.dto.BookDTO;
import com.hakan.arayici.readingisgood.api.dto.CustomerDTO;
import com.hakan.arayici.readingisgood.api.dto.OrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/customer")
public interface ICustomerApi {

    @Operation(summary = "creates customer", description = "creates customer")
    @ApiResponse(description = "creates customer")
    @PostMapping("/create")
    ResponseEntity<BookIsGoodApiResponse> createCustomer(@RequestBody @Validated CustomerDTO customerDTO);


    @Operation(summary = "gets orders of customers by given customer no", description = "gets orders of customers by given customer no")
    @ApiResponse(description = "gets orders of customers by given customer no")
    @GetMapping("/getOrdersByCustomerNo")
    ResponseEntity<List<OrderDTO>> getOrdersByCustomerNo(@RequestParam String customerNo,
                                                         @RequestParam Integer page,
                                                         @RequestParam Integer pageSize
    );
}
