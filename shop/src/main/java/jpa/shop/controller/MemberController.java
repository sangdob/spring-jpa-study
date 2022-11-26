package jpa.shop.controller;

import jpa.shop.domain.Address;
import jpa.shop.domain.Member;
import jpa.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create(MemberForm memberForm, BindingResult result) {
        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }
}
