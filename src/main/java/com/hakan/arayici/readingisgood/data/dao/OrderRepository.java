package com.hakan.arayici.readingisgood.data.dao;

import com.hakan.arayici.readingisgood.api.dto.OrderDTO;
import com.hakan.arayici.readingisgood.data.model.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {

    List<OrderEntity> findByCustomerEntityCustomerNo(String customerNo, Pageable pageable);

    List<OrderEntity> findByCreateDateBetween(LocalDateTime from, LocalDateTime to);
}
