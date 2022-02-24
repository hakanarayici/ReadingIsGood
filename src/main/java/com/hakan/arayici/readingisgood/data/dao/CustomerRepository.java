package com.hakan.arayici.readingisgood.data.dao;

import com.hakan.arayici.readingisgood.data.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>  {
}
