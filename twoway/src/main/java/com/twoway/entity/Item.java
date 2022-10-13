package com.twoway.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "item")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "stock_quantity")
    private int StockQuantity;


}
