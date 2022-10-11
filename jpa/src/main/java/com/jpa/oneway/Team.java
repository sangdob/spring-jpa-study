package com.jpa.oneway;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "team")
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;
}
