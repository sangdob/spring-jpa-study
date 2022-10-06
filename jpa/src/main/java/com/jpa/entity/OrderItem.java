package com.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "order_Item")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "orderItemId", nullable = false)
    private Long id;

    @Column(name = "orderId")
    private Long orderId;

    @Column(name="itemId")
    private Long itemId;

    @Column(name = "orderPrice")
    private Integer price;

    @Column(name = "count")
    private Integer count;
}
