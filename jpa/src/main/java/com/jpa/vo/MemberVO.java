package com.jpa.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class MemberVO {
    public String memberId;
    public BigInteger money;
}
