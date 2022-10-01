package com.jpa.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "member")
public class Member {

    @Id
    @Column(name = "MemberId")
    private String memberId;

    @Column(name = "money")
    private BigInteger money;
}
