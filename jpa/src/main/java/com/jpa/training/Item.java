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
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long price;

    private Long stockQuantity;

//    @ManyToMany
//    @JoinColumn(name = "category_id")
//    private List<Category> categories = new ArrayList<>();

}
