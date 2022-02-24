package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.CustomerDTO;
import com.hakan.arayici.readingisgood.data.dao.CustomerRepository;
import com.hakan.arayici.readingisgood.data.model.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Boolean createCustomer(CustomerDTO customerDTO) {

        customerRepository.save(CustomerEntity.builder()
                .customerName(customerDTO.getCustomerName())
                .customerNo(customerDTO.getCustomerNo())
                .build());

        return Boolean.TRUE;
    }
}
