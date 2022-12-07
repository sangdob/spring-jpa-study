package jpa.shop.service;

import jpa.shop.domain.Delivery;
import jpa.shop.domain.Member;
import jpa.shop.domain.Order;
import jpa.shop.domain.OrderItem;
import jpa.shop.domain.item.Item;
import jpa.shop.repository.ItemRepository;
import jpa.shop.repository.MemberRepository;
import jpa.shop.repository.OrderRepository;
import jpa.shop.repository.OrderSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Getter
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findByMember(memberId);
        Item item = itemRepository.findByItem(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        
        // 주문저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findByOrder(orderId);
        // 주문 취소
        order.cancel();
    }

    /**
     * 주문 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll();
        return null;
    }
}
