package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.BookDTO;
import com.hakan.arayici.readingisgood.api.dto.CustomerDTO;
import com.hakan.arayici.readingisgood.data.dao.BookRepository;
import com.hakan.arayici.readingisgood.data.dao.CustomerRepository;
import com.hakan.arayici.readingisgood.data.model.BookEntity;
import com.hakan.arayici.readingisgood.data.model.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CustomerServiceTest {

    @MockBean
    CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp(){
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void createCustomer() {
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(null);

        Boolean success = customerService.createCustomer(CustomerDTO.builder()
                .customerNo("321321")
                .customerName("Hakan Arayici")
                .build());

        assertTrue(success);
        verify(customerRepository,times(1)).save(any(CustomerEntity.class));

    }
}