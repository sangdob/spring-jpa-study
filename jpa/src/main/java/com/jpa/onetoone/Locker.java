package com.jpa.onetoone;

import javax.persistence.*;

@Entity(name = "locker")
public class Locker {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
