package jpa.shop.rest.controller;

import jpa.shop.domain.Member;
import jpa.shop.rest.dto.*;
import jpa.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/v1/members")
    public ResponseEntity findMembersV1() {
        List<Member> members = memberService.findMembers();

        return ResponseEntity.ok().body(members);
    }

    //   find members count 개선안
    @GetMapping("/v2/members")
    public ResponseEntity findMembersV2() {
        SelectResult<List<SelectMemberResponse>> result = new SelectResult<>();

        List<SelectMemberResponse> findMembers = memberService.findMembers().stream()
                .map(t -> new SelectMemberResponse(t.getName()))
                .collect(Collectors.toList());

        result.setCount(memberService.findMembersCount());
        result.setData(findMembers);

        return ResponseEntity.ok().body(result);
    }

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
