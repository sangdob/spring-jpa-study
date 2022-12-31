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

    /**
     * create method refactoring
     */
    public void dbInit1() {
        Member member = createMember("userAB", "seoul", "1", "1111");
        entityManager.persist(member);

        Book book = createBook("jpa book1", 15000L, 100L);
        entityManager.persist(book);
        Book book1 = createBook("jpa2 Book2", 120000L, 10L);
        entityManager.persist(book1);

        OrderItem orderItem = OrderItem.createOrderItem(book, 10000L, 1);
        entityManager.persist(orderItem);
        OrderItem orderItem1 = OrderItem.createOrderItem(book1, 150000L, 3);
        entityManager.persist(orderItem1);

        Delivery delivery = createDelivery(member);
        entityManager.persist(delivery);

        Order order = Order.createOrder(member, delivery, orderItem, orderItem1);
        entityManager.persist(order);

        entityManager.flush();
    }

    public void dbInit2() {
        Member member = createMember("daeGu User", "daeGu", "2", "35112");
        entityManager.persist(member);

        Book book = createBook("jpa book7", 13000L, 1060L);
        entityManager.persist(book);
        Book book1 = createBook("jpa2 Book8", 120000L, 102L);
        entityManager.persist(book1);

        OrderItem orderItem = OrderItem.createOrderItem(book, 10000L, 1);
        entityManager.persist(orderItem);
        OrderItem orderItem1 = OrderItem.createOrderItem(book1, 150000L, 3);
        entityManager.persist(orderItem1);

        Delivery delivery = createDelivery(member);
        entityManager.persist(delivery);

        Order order = Order.createOrder(member, delivery, orderItem, orderItem1);
        entityManager.persist(order);

        entityManager.flush();
    }

    private static Delivery createDelivery(Member member) {
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        return delivery;
    }

    private static Book createBook(String name, long price, long stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        return book;
    }

    private static Member createMember(String name, String city, String street, String zipcode) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address(city, street, zipcode));
        return member;
    }
}
