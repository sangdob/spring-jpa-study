package jpa.shop.rest.dto;

import jpa.shop.domain.Address;
import jpa.shop.domain.Order;
import jpa.shop.domain.OrderItem;
import jpa.shop.domain.status.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data

public class OrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Address address;
    private List<OrderItem> orderItems;

    public OrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        status = order.getStatus();
        address = order.getDelivery().getAddress();
        order.getOrderItems().stream()
                .map(orderItem -> orderItem.getItem().getName());
        orderItems = order.getOrderItems();
    }
}
