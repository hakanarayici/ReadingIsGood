package com.hakan.arayici.readingisgood.data.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CUSTOMER_ORDER")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false)
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy="orderEntity",cascade=CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderBookEntity> orderBookList;

    @Column(name = "create_date")
    private LocalDateTime createDate;

}
