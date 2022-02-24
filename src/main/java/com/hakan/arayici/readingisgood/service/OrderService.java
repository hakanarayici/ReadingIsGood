package com.hakan.arayici.readingisgood.service;

import com.hakan.arayici.readingisgood.api.dto.BookOrderDTO;
import com.hakan.arayici.readingisgood.api.dto.CreateOrderDTO;
import com.hakan.arayici.readingisgood.api.dto.OrderBookDTO;
import com.hakan.arayici.readingisgood.api.dto.OrderDTO;
import com.hakan.arayici.readingisgood.data.dao.BookRepository;
import com.hakan.arayici.readingisgood.data.dao.CustomerRepository;
import com.hakan.arayici.readingisgood.data.dao.OrderRepository;
import com.hakan.arayici.readingisgood.data.model.BookEntity;
import com.hakan.arayici.readingisgood.data.model.CustomerEntity;
import com.hakan.arayici.readingisgood.data.model.OrderBookEntity;
import com.hakan.arayici.readingisgood.data.model.OrderEntity;
import com.hakan.arayici.readingisgood.exception.BookIsNotFoundException;
import com.hakan.arayici.readingisgood.exception.CustomerIsNotFoundException;
import com.hakan.arayici.readingisgood.exception.OrderIsNotFoundException;
import com.hakan.arayici.readingisgood.exception.StockIsNotAvailableException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService implements IOrderService{

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    @SneakyThrows
    @Override
    public Boolean createOrder(CreateOrderDTO createOrderDTO) {

        CustomerEntity customerEntity  = customerRepository.findById(createOrderDTO.getCustomerID())
                .orElseThrow(() -> new CustomerIsNotFoundException(createOrderDTO.getCustomerID()));


        OrderEntity orderEntity = OrderEntity.builder()
                .customerEntity(customerEntity)
                .createDate(LocalDateTime.now())
                .build();

        List<OrderBookEntity> orderBookEntityList = createOrderDTO.getBookOrderDTOList()
                .stream()
                .map(a -> this.createBookOrder(a,orderEntity))
                .collect(Collectors.toList());

        orderEntity.setOrderBookList(orderBookEntityList);

        orderRepository.save(orderEntity);

        return Boolean.TRUE;
    }

    private OrderBookEntity createBookOrder(BookOrderDTO bookOrderDTO, OrderEntity orderEntity) {

        BookEntity bookEntity = bookRepository.findById(bookOrderDTO.getBookID()).orElseThrow(() -> new BookIsNotFoundException(bookOrderDTO.getBookID()));

        Optional.of(bookEntity)
                .filter(a -> a.getStockCount() < bookOrderDTO.getQuantity())
                .ifPresent(a -> {
                        throw new StockIsNotAvailableException(a.getId(),a.getStockCount());
                    });

        bookEntity.setStockCount(bookEntity.getStockCount() - bookOrderDTO.getQuantity());
        bookRepository.save(bookEntity);

        return OrderBookEntity.builder()
                .bookEntity(bookEntity)
                .quantity(bookOrderDTO.getQuantity())
                .orderEntity(orderEntity)
                .build();
    }

    @SneakyThrows
    @Override
    public OrderDTO getOrderByID(Long orderID) {
        OrderEntity orderEntity = orderRepository.findById(orderID).orElseThrow(() -> new OrderIsNotFoundException(orderID));
        return mapTo(orderEntity);
    }

    @Override
    public List<OrderDTO> getByDate(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByCreateDateBetween(startDate,endDate)
                .stream()
                .map(this::mapTo)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getByCustomerNo(String customerNo, int page, int count) {

        return orderRepository.findByCustomerEntityCustomerNo(customerNo, PageRequest.of(page,count))
                .stream()
                .map(this::mapTo)
                .collect(Collectors.toList());
    }

    private OrderDTO mapTo(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .orderID(orderEntity.getId())
                .customerNo(orderEntity.getCustomerEntity().getCustomerNo())
                .orderDate(orderEntity.getCreateDate())
                .bookDTOList(orderEntity.getOrderBookList()
                        .stream()
                        .map(b -> OrderBookDTO.builder()
                                .author(b.getBookEntity().getAuthor())
                                .title(b.getBookEntity().getTitle())
                                .quantity(b.getQuantity())
                                .unitPrice(b.getBookEntity().getPrice())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
