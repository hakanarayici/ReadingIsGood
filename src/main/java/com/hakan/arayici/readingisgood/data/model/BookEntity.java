package com.hakan.arayici.readingisgood.data.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "STOCK_COUNT")
    private Integer stockCount;

    @Column(name = "PRICE")
    private BigDecimal price;


}
