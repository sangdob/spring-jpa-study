package jpa.shop.rest.controller;

import jpa.shop.domain.Member;
import jpa.shop.rest.dto.CreateMemberRequest;
import jpa.shop.rest.dto.CreateMemberResponse;
import jpa.shop.rest.dto.UpdateMemberRequest;
import jpa.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/v2/members/{id}")
    public ResponseEntity updateMemberV2(@PathVariable("id") Long id,
                                         @RequestBody UpdateMemberRequest request) {
        memberService.update(id, request.getName());
        Member findMember = memberService.findByMember(id);
        
        return ResponseEntity.ok().body(findMember);
    }
}
