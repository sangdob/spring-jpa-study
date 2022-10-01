package com.jpa.service;

import com.jpa.entity.Member;
import com.jpa.repository.MemberRepository;
import com.jpa.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository repository;

    public List<Member> findMember() {
        List<Member> members = repository.findAll();

        return members;
    }
}
