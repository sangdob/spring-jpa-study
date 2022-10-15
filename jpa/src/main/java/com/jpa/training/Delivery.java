package com.jpa.training;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "orders")
    private Order orders = new Order();

    private String city;

    private String street;

    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;


}
