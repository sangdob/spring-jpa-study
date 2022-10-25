package com.jpa.jpql;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
public class SqlCoalesceTest {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaStudy");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            String query = "select" +
                    "coalesce(m.name, 'no name')" +
                    "from Member m";

            List<String> findAllMember = entityManager.createQuery(query, String.class).getResultList();

            for ( String m : findAllMember) {
                log.info("member = {}", m.toString());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.getStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
