package jpa.shop.rest.dto;

import jpa.shop.domain.Address;
import jpa.shop.domain.Order;
import jpa.shop.domain.status.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName(); // LAZY 형태로 초기화 실행
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress(); // LAZY 형태로 초기화 실행
    }
}
