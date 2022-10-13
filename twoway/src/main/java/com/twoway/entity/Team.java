package com.twoway.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "team")
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

   /* public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }*/
}
