package com.jpa.training;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "orders")
    private Order order;

    private Long orderPrice;

    private Long count;
}
