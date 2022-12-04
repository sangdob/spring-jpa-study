package jpa.shop.controller;

import jpa.shop.domain.Member;
import jpa.shop.domain.item.Item;
import jpa.shop.service.ItemService;
import jpa.shop.service.MemberService;
import jpa.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final MemberService memberService;

    @Autowired
    private final ItemService itemService;

    @GetMapping
    public String createForm(Model model) {
        model.addAttribute("members", memberService.findMembers());
        model.addAttribute("items", itemService.findItems());
        return "order/orderForm";
    }
}
