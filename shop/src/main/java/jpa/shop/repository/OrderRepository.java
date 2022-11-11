package jpa.shop.repository;

import jpa.shop.domain.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Getter
public class OrderRepository {

    private final EntityManager entityManager;

    public void save(Order order) {
        entityManager.persist(order);

        entityManager.flush();
        entityManager.clear();
    }

    public Order findByOrder(Long id) {
        return entityManager.find(Order.class, id);
    }
}
