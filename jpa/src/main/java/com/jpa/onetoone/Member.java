package com.jpa.onetoone;

import javax.persistence.*;

@Entity(name = "member")
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String userName;


    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker member;
}
