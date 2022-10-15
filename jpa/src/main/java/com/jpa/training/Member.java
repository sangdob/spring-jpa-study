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
public class Member
{

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String city;

    private String street;

    private String zipcode;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Order> orders = new ArrayList<>();

}
