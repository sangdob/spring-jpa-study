package jpa.shop.repository;

import jpa.shop.domain.Order;
import jpa.shop.rest.dto.SimpleOrderQueryDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Getter
public class OrderRepository {

    private final EntityManager entityManager;

    public void save(Order order) {
        entityManager.persist(order);

    }

    public Order findByOrder(Long id) {
        return entityManager.find(Order.class, id);
    }

    public List<Order> findAll() {
        return entityManager.createQuery("select O " +
                "from Order O " +
                "join O.member m").getResultList();
    }

    /**
     * 동적쿼리 해결 방법
     * Querydsl 로 해결이 가능하다...
     *
     * @param orderSearch
     * @return
     */
    public List<Order> finaAll(OrderSearch orderSearch) {
        return entityManager.createQuery("select O from Order O join O.member m" +
                        " where O.status =: status" +
                        " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();
    }

    /**
     * JPA Criteria
     *
     * @param orderSearch
     * @return
     */
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(m.get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = entityManager.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }

    public List<Order> findAllWithMemberDelivery(int offset, int limit) {
        return entityManager.createQuery(
                        "select o from Order o" +
                                " join fetch o.member m" +
                                " join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<SimpleOrderQueryDto> findOrderDtos() {
        return entityManager.createQuery("select new jpa.shop.rest.dto.SimpleOrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", SimpleOrderQueryDto.class)
                .getResultList();
    }

    /**
     * jpa distinct 데이터베이스의 distinct와 다르게 데이터를 압축시켜준다
     * jpa distinct 는 어플리케이션 자체에서 중복을 걸러주는 형식
     * 단점 !!  페이징이 불가능하다
     * 페이징을 실행시 데이터를 가지고 온 상태에서 페이징처리하므로 메모리누수가 심하다...
     *
     * @return
     */
    public List<Order> findAllWithItems() {
        return entityManager.createQuery(
                        "select distinct o from Order o " +
                                " join fetch o.member m" +
                                " join fetch o.delivery d" +
                                " join fetch o.orderItems oi" +
                                " join fetch oi.item i", Order.class)
//                .setFirstResult(0)
//                .setMaxResults(100)
                .getResultList();
    }
}
