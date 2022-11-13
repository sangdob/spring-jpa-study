package jpa.shop.service;

import jpa.shop.domain.Address;
import jpa.shop.domain.Member;
import jpa.shop.domain.Order;
import jpa.shop.domain.item.Book;
import jpa.shop.domain.item.Item;
import jpa.shop.domain.status.OrderStatus;
import jpa.shop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager entityManager;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        Member member = createMember("회원1", new Address("seoul", "river", "123-111"));
        entityManager.persist(member);

        Book book = createBook("JPA", 10000L, 10L);
        entityManager.persist(book);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findByOrder(orderId);

        assertThat(OrderStatus.ORDER).isEqualTo(getOrder.getStatus()).as("상품 주문시 상태는 ORDER");
        assertThat(1).isEqualTo(getOrder.getOrderItems().size()).as("주문한 상품 종류 수가 정확해야한다.");
        assertThat(10000 * orderCount).isEqualTo(getOrder.getTotalPrice()).as("주문한 상품 종류 수가 정확해야한다.");
        assertThat(8L).isEqualTo(book.getStockQuantity()).as("주문 수량만큼 재고가 줄어야한다.");
    }

    @Test
    public void 주문취소() throws Exception {
        Member member = createMember("cancel", new Address("1", "1,", ",1"));
        Book item = createBook("jpa2", 10000L, 10L);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findByOrder(orderId);

        assertThat(OrderStatus.CANCEL).isEqualTo(getOrder.getStatus());
        assertThat(10L).isEqualTo(item.getStockQuantity());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception{
        Member member = createMember("주문재고수량", new Address("how", "where", "111"));
        Item item = createBook("jpa", 10000L, 10L);

        int orderCnt = 11;

        orderService.order(member.getId(), item.getId(), orderCnt);

        fail("재고 수량 부족 예외");
    }
    private Book createBook(String name, long price, long stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        entityManager.persist(book);
        return book;
    }

    private Member createMember(String name, Address address) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(address);
        entityManager.persist(member);
        return member;
    }


}