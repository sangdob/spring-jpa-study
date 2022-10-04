package com.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Item")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Item")
    @SequenceGenerator(name = "Item", sequenceName = "Item_Seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "stockQuantity")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stockQuantity")
    private Integer StockQuantity;


}
