package com.hakan.arayici.readingisgood.data.model;


import lombok.*;

import javax.persistence.*;
import java.awt.print.Book;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ORDER_BOOK")
public class OrderBookEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity bookEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity orderEntity;

    @Column(name = "quantity")
    private Integer quantity;
}
