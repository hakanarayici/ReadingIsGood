package com.hakan.arayici.readingisgood.data.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String customerName;

    @Column(name = "customer_no")
    private String customerNo;

    @OneToMany(mappedBy="customerEntity",cascade=CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderEntity> orderList;

}
