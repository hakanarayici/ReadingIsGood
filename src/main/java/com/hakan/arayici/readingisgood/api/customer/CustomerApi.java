package com.hakan.arayici.readingisgood.api.customer;

import com.hakan.arayici.readingisgood.api.BookIsGoodApiResponse;
import com.hakan.arayici.readingisgood.api.dto.BookDTO;
import com.hakan.arayici.readingisgood.api.dto.CustomerDTO;
import com.hakan.arayici.readingisgood.api.dto.OrderDTO;
import com.hakan.arayici.readingisgood.service.ICustomerService;
import com.hakan.arayici.readingisgood.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CustomerApi implements ICustomerApi {

    private final ICustomerService customerService;
    private final IOrderService orderService;

    @Override
    public ResponseEntity<BookIsGoodApiResponse> createCustomer(CustomerDTO customerDTO) {
        customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(new BookIsGoodApiResponse(true,""), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerNo(String customerNo,
                                                                Integer page,
                                                                Integer pageSize) {
        return new ResponseEntity<>(orderService.getByCustomerNo(customerNo,page,pageSize),HttpStatus.OK);
    }

}
