package jpa.shop.rest.controller;

import jpa.shop.domain.Member;
import jpa.shop.rest.dto.CreateMemberRequest;
import jpa.shop.rest.dto.CreateMemberResponse;
import jpa.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiMemberController {
    private final MemberService memberService;

    @PostMapping("/v1/members")
    public ResponseEntity saveMemberV1(@RequestBody Member member) {
        Long id = memberService.join(member);

        CreateMemberResponse response = new CreateMemberResponse();
        response.setId(id);

        return ResponseEntity.ok().body(response.hashCode());
    }

    @PostMapping("/v2/members")
    public ResponseEntity saveMemberV2(@RequestBody CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);

        CreateMemberResponse response = new CreateMemberResponse();
        response.setId(id);

        return ResponseEntity.ok().body(response.hashCode());
    }

}
