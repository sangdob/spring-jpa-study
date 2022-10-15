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
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinColumn(name = "parent_id")
    private Category parent;


    private String name;
}
