package jpa.shop.rest.controller;

import jpa.shop.domain.Order;
import jpa.shop.rest.dto.SimpleOrderDto;
import jpa.shop.rest.dto.SimpleOrderQueryDto;
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
public class OrderSimpleApiController {

    private final OrderService orderService;

    @GetMapping("/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> orders = orderService.findAll();
        return orders;
    }

    /**
     * N + 1 쿼리가 조회되는 현상 발생
     *
     * @return
     */
    @GetMapping("/v2/simple-orders")
    public ResponseEntity<List<SimpleOrderDto>> ordersV2() {
        List<SimpleOrderDto> collect = orderService.findAll().stream()
                .map(order -> new SimpleOrderDto(order))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(collect);
    }

    @GetMapping("/v3/simple-orders")
    public ResponseEntity<List<SimpleOrderDto>> ordersV3() {
        int offset = 0;
        int limit = 100;
        
        List<SimpleOrderDto> collect = orderService.findAllWithMemberDelivery(offset, limit).stream()
                .map(order -> new SimpleOrderDto(order))
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(collect);
    }

    @GetMapping("/v1/simple-orders-dto")
    public ResponseEntity<List<SimpleOrderQueryDto>> orderDtoV1() {
        List<SimpleOrderQueryDto> orderDtos = orderService.findOrderDtos();

        return ResponseEntity.ok()
                .body(orderDtos);
    }

}
