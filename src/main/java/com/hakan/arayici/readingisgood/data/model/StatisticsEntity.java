package com.hakan.arayici.readingisgood.data.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StatisticsEntity {

    private String customerNo;

    @Id
    private Integer month;
    private Integer totalBookCount;
    private Integer orderCount;
    private BigDecimal totalPrice;
}
