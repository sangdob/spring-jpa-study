package jpa.shop.rest.controller;

import jpa.shop.domain.Order;
import jpa.shop.domain.OrderItem;
import jpa.shop.rest.dto.OrderDto;
import jpa.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("/v1/orders")
    public ResponseEntity<List<Order>> ordersV1() {
        List<Order> all = orderService.findAll();

        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();

            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream()
                    .forEach(orderItem -> orderItem.getItem().getName());
        }

        return ResponseEntity.ok()
                .body(all);
    }

    @GetMapping("/v2/orders")
    public ResponseEntity<List<OrderDto>> ordersV2() {
        List<OrderDto> orders = orderService.findAll().stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/v3/orders")
    public ResponseEntity ordersV3() {
        List<Order> allWithItems = orderService.findAllWithItems();
        
        List<OrderDto> orders = orderService.findAll().stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(allWithItems);
    }
}
