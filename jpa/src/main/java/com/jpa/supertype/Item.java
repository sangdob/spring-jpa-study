package com.jpa.supertype;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Entity
@Getter
@Setter
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int price;
}
