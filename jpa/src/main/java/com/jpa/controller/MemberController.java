package com.jpa.controller;

import com.jpa.entity.Member;
import com.jpa.service.MemberService;
import com.jpa.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    public MemberService service;

    @GetMapping("jpa/test")
    public void getMember() {
        List<Member> members= service.findMember();

        List<String> collect = members.stream().map(Member::getMemberId).collect(Collectors.toList());
        for (String s : collect) {
            log.info("member = {}", s);
        }
    }
}
