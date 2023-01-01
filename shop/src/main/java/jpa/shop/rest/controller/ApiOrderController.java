package jpa.shop.rest.controller;

import jpa.shop.domain.Order;
import jpa.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * XToOne
 * order
 * order -> member
 * order ->delivery
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiOrderController {

    private final OrderService orderService;

    @GetMapping("/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> orders = orderService.findAll();
        return orders;
    }
}
