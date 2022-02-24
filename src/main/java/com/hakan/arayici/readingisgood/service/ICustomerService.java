package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.CustomerDTO;

public interface ICustomerService {
    Boolean createCustomer(CustomerDTO customerDTO);
}
