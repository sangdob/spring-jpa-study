package com.twoway.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "member")
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
