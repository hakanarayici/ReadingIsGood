package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.OrderDTO;
import com.hakan.arayici.readingisgood.data.dao.BookRepository;
import com.hakan.arayici.readingisgood.data.dao.CustomerRepository;
import com.hakan.arayici.readingisgood.data.dao.OrderRepository;
import com.hakan.arayici.readingisgood.data.dao.StatisticsRepository;
import com.hakan.arayici.readingisgood.data.model.BookEntity;
import com.hakan.arayici.readingisgood.data.model.CustomerEntity;
import com.hakan.arayici.readingisgood.data.model.OrderBookEntity;
import com.hakan.arayici.readingisgood.data.model.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class OrderServiceTest {

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    BookRepository bookRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp(){
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(
                orderRepository,
                customerRepository,
                bookRepository
        );
    }

    @Test
    void createOrder() {
    }

    @Test
    void getOrderByID() {
        Optional<OrderEntity> orderOptional = Optional.of(OrderEntity.builder()
                .customerEntity(CustomerEntity.builder().build())
                .orderBookList(List.of(OrderBookEntity.builder()
                        .bookEntity(BookEntity.builder().build())
                        .build()))
                .build());

        when(orderRepository.findById(anyLong())).thenReturn(orderOptional);
        OrderDTO orderDTO = orderService.getOrderByID(1L);

        assertNotNull(orderDTO);
        verify(orderRepository,times(1)).findById(anyLong());

    }

    @Test
    void getByDate() {
        List<OrderEntity> orderEntityList = List.of(OrderEntity.builder()
                .customerEntity(CustomerEntity.builder().build())
                .orderBookList(List.of(OrderBookEntity.builder()
                        .bookEntity(BookEntity.builder().build())
                        .build()))
                .build());

        when(orderRepository.findByCreateDateBetween(any(LocalDateTime.class),any(LocalDateTime.class))).thenReturn(orderEntityList);
        List<OrderDTO> orderDTOList = orderService.getByDate(LocalDateTime.now().minusDays(1), LocalDateTime.now());

        assertNotNull(orderDTOList);
        assertEquals(1,orderDTOList.size());
        verify(orderRepository,times(1)).findByCreateDateBetween(any(LocalDateTime.class),any(LocalDateTime.class));
    }

    @Test
    void getByCustomerNo() {
        List<OrderEntity> orderEntityList = List.of(OrderEntity.builder()
                .customerEntity(CustomerEntity.builder().build())
                .orderBookList(List.of(OrderBookEntity.builder()
                        .bookEntity(BookEntity.builder().build())
                        .build()))
                .build());

        when(orderRepository.findByCustomerEntityCustomerNo(anyString(),any(Pageable.class))).thenReturn(orderEntityList);
        List<OrderDTO> orderDTOList = orderService.getByCustomerNo("321321",1,1);

        assertNotNull(orderDTOList);
        assertEquals(1,orderDTOList.size());
        verify(orderRepository,times(1)).findByCustomerEntityCustomerNo(anyString(),any(Pageable.class));

    }
}