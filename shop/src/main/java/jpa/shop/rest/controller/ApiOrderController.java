package jpa.shop.rest.controller;

import jpa.shop.domain.Order;
import jpa.shop.rest.dto.SimpleOrderDto;
import jpa.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/v2/simple-orders")
    public ResponseEntity<List<SimpleOrderDto>> ordersV2() {
        List<SimpleOrderDto> collect = orderService.findAll().stream()
                .map(order -> new SimpleOrderDto(order))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(collect);
    }
}
