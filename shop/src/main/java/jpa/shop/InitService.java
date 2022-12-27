package jpa.shop;

import jpa.shop.domain.*;
import jpa.shop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@Transactional
@RequiredArgsConstructor
public class InitService {

    private final EntityManager entityManager;

    public void dbInit1() {
        Member member = new Member();
        member.setName("userAB");
        member.setAddress(new Address("seoul", "1", "1111"));
        entityManager.persist(member);

        Book book = new Book();
        book.setName("jpa book1");
        book.setPrice(15000L);
        book.setStockQuantity(100L);
        entityManager.persist(book);

        Book book1 = new Book();
        book1.setName("jpa2 Book2");
        book1.setPrice(120000L);
        book1.setStockQuantity(10L);
        entityManager.persist(book1);

        OrderItem orderItem = OrderItem.createOrderItem(book, 10000L, 1);
        entityManager.persist(orderItem);

        OrderItem orderItem1 = OrderItem.createOrderItem(book1, 150000L, 3);
        entityManager.persist(orderItem1);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        entityManager.persist(delivery);

        Order order = Order.createOrder(member, delivery, orderItem, orderItem1);
        entityManager.persist(order);
        
        entityManager.flush();
    }
}
